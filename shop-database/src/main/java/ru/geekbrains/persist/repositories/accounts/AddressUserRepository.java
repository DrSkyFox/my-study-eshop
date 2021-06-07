package ru.geekbrains.persist.repositories.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persist.model.accounts.AddressUser;

import java.util.List;

public interface AddressUserRepository extends JpaRepository<AddressUser, Long> {

    @Query("select au from AddressUser au where au.isActive = true ")
    List<AddressUser> findAllActive();

    @Query("select au from AddressUser au where au.isActive = false ")
    List<AddressUser> findAllUnActive();

    List<AddressUser> findAll();

    void deleteById(Long id);



}
