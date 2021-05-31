package ru.geekbrains.persist.repositories.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.accounts.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
