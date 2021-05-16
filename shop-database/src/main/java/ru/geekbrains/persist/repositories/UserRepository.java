package ru.geekbrains.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String name);

    Optional<User> findByEmail(String email);

    void deleteById(Long id);

    List<User> findAll();

}
