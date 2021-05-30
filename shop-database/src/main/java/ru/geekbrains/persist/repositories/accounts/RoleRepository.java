package ru.geekbrains.persist.repositories.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.accounts.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
