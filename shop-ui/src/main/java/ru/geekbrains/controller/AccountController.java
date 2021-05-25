package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.events.OnForgotPasswordEvent;
import ru.geekbrains.events.OnRegistrationCompleteEvent;
import ru.geekbrains.exceptions.EmailExistsException;
import ru.geekbrains.persist.model.PasswordResetToken;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.model.VerificationToken;
import ru.geekbrains.replication.UserRepr;
import ru.geekbrains.service.IUserService;
import ru.geekbrains.utils.ReplicationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Map;


@Controller
@RequestMapping("/account")
public class AccountController {


    private static final Logger logger = LoggerFactory.getLogger(AccountController.class.getName());

    private final IUserService userService;

    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public AccountController(IUserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/forgotPassword")
    public String forgotPage() {
        return "forgotPassword";
    }

    @GetMapping("/signup")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserRepr());
        return "registrationPage";
    }

    @PostMapping(value = "/reg")
    public String registerUser(
            @Valid @ModelAttribute("user") UserRepr user,
            final BindingResult result,
            final HttpServletRequest request,
            final RedirectAttributes redirect
            ) {
        logger.info("Request Registration New User");
        if (result.hasErrors()) {
            return "registrationPage";
        }
        User registered;
        try {
            user.setEnabled(false);
            logger.info("New User is {}", user);
            registered = userService.registerNewUser(ReplicationUtils.convertReprToEntity(user));
            logger.info("Saved to DB:  {}", registered.toString());

            logger.info("Registration event");
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, getCurURL(request)));
            logger.info("Registration event complete");

        } catch (EmailExistsException e) {
            logger.info("Email already exists: {}", user.getEmail());
            result.rejectValue("messageError", "Email already exists");
            return "registrationPage";
        }
        logger.info("Registration user: {} success", registered);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new user");
        return "redirect:/account/login";
    }

    @PostMapping(value = "/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") final String token,
                                      final RedirectAttributes redirectAttributes) {
        logger.info("Confirmation request with incoming token value: {}", token);
        final VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            logger.info("Token not found in DB");
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid account confirmation token.");
            return "redirect:/account/login";
        }
        logger.info("Token found");
        final User user = verificationToken.getUser();
        logger.info("Got user {} with token {}", user, token);
        final Calendar cal = Calendar.getInstance();

        if (verificationToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0) {
            logger.info("Token {} expired", token);
            redirectAttributes.addFlashAttribute("errorMessage", "Your registration token has expired. Please register again.");
            return "redirect:/account/login";
        }

        user.setEnabled(true);
        logger.info("User is active : {}", user.getEnabled());
        userService.saveRegisteredUser(user);
        logger.info("Saving to DB");
        redirectAttributes.addFlashAttribute("message", "Your account verified successfully");
        return "redirect:/account/login";

    }


    private String getCurURL(final HttpServletRequest request) {

        return "http://"
                + request.getServerName()
                + ":" + request.getServerPort()
                + request.getContextPath();
    }

    //FORGOT PASSWORD

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail, final RedirectAttributes redirectAttributes) {
        logger.info("User request reset password by email {}", userEmail);
        User user= userService.findUserByEmail(userEmail).orElseThrow(NotFoundException::new);
        logger.info("User is {}", user.toString());


        userService.createPasswordResetTokenForUser(user);
        logger.info("Token saved to DB");
        logger.info("URL : {}", getCurURL(request));
        logger.info("ForgotPassword event");
        eventPublisher.publishEvent(new OnForgotPasswordEvent(user, getCurURL(request)));
        logger.info("event is published");


        redirectAttributes.addFlashAttribute("message", "You should receive an Password Reset Email shortly");
        return new ModelAndView("redirect:/account/login");
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ModelAndView showChangePasswordPage(@RequestParam("id") final long id, @RequestParam("token") final String token, final RedirectAttributes redirectAttributes) {
        final PasswordResetToken passToken = userService.getPasswordResetToken(token);
        if (passToken == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid password reset token");
            return new ModelAndView("redirect:/account/login");
        }
        final User user = passToken.getUser();
        if (user.getId() != id) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid password reset token");
            return new ModelAndView("redirect:/account/login");
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
                .getTime()
                - cal.getTime()
                .getTime()) == 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Your password reset token has expired");
            return new ModelAndView("redirect:/account/login");
        }

        final ModelAndView view = new ModelAndView("resetPassword");
        view.addObject("token", token);
        return view;
    }

    @RequestMapping(value = "/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView savePassword(@RequestParam("password") final String password, @RequestParam("passwordConfirmation") final String passwordConfirmation, @RequestParam("token") final String token, final RedirectAttributes redirectAttributes) {

        if (!password.equals(passwordConfirmation)) {
            return new ModelAndView("resetPassword", Map.of("errorMessage", "Passwords do not match"));
        }

        final PasswordResetToken p = userService.getPasswordResetToken(token);

        if (p == null) {
            redirectAttributes.addFlashAttribute("message", "Invalid token");
        }

        final User user = p.getUser();

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Unknown user");
        } else {
            userService.changeUserPassword(user, password);
            redirectAttributes.addFlashAttribute("message", "Password reset successfully");
        }
        return new ModelAndView("redirect:/account/login");
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }


    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

}
