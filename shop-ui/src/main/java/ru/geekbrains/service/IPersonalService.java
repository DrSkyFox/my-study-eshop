package ru.geekbrains.service;

import ru.geekbrains.persist.model.accounts.AddressUser;
import ru.geekbrains.persist.model.accounts.User;
import ru.geekbrains.persist.model.accounts.UserInfo;
import ru.geekbrains.replication.UserRepr;

import java.util.List;
import java.util.Optional;


public interface IPersonalService {

    User getMyInfo();

    void savePersonalData(UserRepr userRepr);

    void saveAddress(AddressUser address);

    Optional<UserInfo> getPersonalData(User user);

    List<AddressUser> getPersonalListAddress();

}
