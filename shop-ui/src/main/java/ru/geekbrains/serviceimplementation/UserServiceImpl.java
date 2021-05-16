package ru.geekbrains.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.exceptions.EmailExistsException;
import ru.geekbrains.persist.repositories.PasswordResetTokenRepository;
import ru.geekbrains.persist.repositories.UserRepository;
import ru.geekbrains.persist.repositories.VerificationTokenRepository;
import ru.geekbrains.persist.model.PasswordResetToken;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.model.VerificationToken;
import ru.geekbrains.service.IUserService;

import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final PasswordResetTokenRepository passwordTokenRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, VerificationTokenRepository verificationTokenRepository, PasswordResetTokenRepository passwordTokenRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerNewUser(User user) throws EmailExistsException {
        logger.info("RegisterNewUser: {}", user);
        if (repository.findByEmail(user.getEmail()).isEmpty()) {
            logger.info("Email already exists");
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }
        logger.info("User status false");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        logger.info("User save to DB {}", user);
        return repository.save(user);
    }

    @Override
    public User updateExistingUser(User user) throws EmailExistsException {
        logger.info("UpdateExists User: {}", user.toString());
        User checkUser = repository.findByEmail(user.getEmail()).orElse(null);
        logger.info("check exists user with email {} result: {}", user.getEmail(), checkUser.toString());
        if (checkUser != null && !user.getId().equals(checkUser.getId())) {
            logger.info("Email {} not available", user.getEmail());
            throw new EmailExistsException("Email not available.");
        }
        logger.info("Save Update");
        return repository.save(user);
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        logger.info("VerificationToken saving");
        final VerificationToken myToken = new VerificationToken(token, user);
        try {
            verificationTokenRepository.save(myToken);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        logger.info("VerificationToken saved");
    }

    @Override
    public VerificationToken getVerificationToken(final String token) {
        logger.info("Get VerificationToken by token {}", token);
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void saveRegisteredUser(final User user)
    {
        logger.info("SaveRegistered User {}", user);
        user.setEnabled(true);
        logger.info("Change status to active");
        repository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(final String email) {
        logger.info("Get User by email {}", email);
        return repository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        logger.info("Creating reset token in DB. Token is  {}", token);
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        logger.info("Saving token to DB");
        passwordTokenRepository.save(myToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token)
    {
        logger.info("Get PasswordResetToken by token {}", token);
        return passwordTokenRepository.findByToken(token);
    }

    @Override
    public void changeUserPassword(User user, String password) {
        logger.info("Change password on {} for user {}", password, user);
        user.setPassword(passwordEncoder.encode(password));
        logger.info("User password after encoding: {}", user.getPassword());
        repository.save(user);
        logger.info("User password saved to db");
    }

    @Override
    public User save(User user) {
        logger.info("User save {}", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("User password after encoding: {}", user.getPassword());
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete User by Id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }
}
