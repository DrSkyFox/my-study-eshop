package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persist.model.PasswordResetToken;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.model.VerificationToken;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;


public interface UserService {

    User registerNewUserAccount(UserRepr userRepr);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    Optional<User> getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();


}
