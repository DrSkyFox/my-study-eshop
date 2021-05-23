package ru.geekbrains.service;

import ru.geekbrains.exceptions.EmailExistsException;
import ru.geekbrains.persist.model.PasswordResetToken;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.model.VerificationToken;

import java.util.Optional;


public interface IUserService {

    User registerNewUser(User user) throws EmailExistsException;

    User updateExistingUser(User user) throws EmailExistsException;

    Optional<User> findUserByEmail(final String email);

    String createVerificationTokenForUser(User user);

    VerificationToken getVerificationToken(String token);

    void saveRegisteredUser(User user);

    String createPasswordResetTokenForUser(User user);

    PasswordResetToken getPasswordResetToken(String token);

    void changeUserPassword(User user, String password);

    Iterable<User> findAll();

    User save(User user);

    void deleteById(Long id);


}
