package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {
    List<User> findUserByUsernameLike(String username);

    Optional<User> findAllByUsernameContains(String username);

    Optional<User> findUserByUsername(String username);

    @Query("select u from User u where u.username like :username")
    List<User> someQuery(@Param("username") String username);
}
