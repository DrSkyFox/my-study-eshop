package ru.geekbrains.persist.repositories.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.accounts.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
