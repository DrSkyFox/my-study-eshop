package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            "select distinct u " +
            "from User u " +
            "left join fetch u.roles "
            )
    List<User> findAllWithRoles();

    @Query( "select u from User u")
    List<User> findAll();


    Optional<User> findUserByLogin(String name);



}
