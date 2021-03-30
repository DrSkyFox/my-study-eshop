package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
