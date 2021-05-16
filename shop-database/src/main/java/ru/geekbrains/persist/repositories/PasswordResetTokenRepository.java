package ru.geekbrains.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
