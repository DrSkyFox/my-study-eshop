package ru.geekbrains.persist.repo;

import liquibase.pro.packaged.S;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persist.model.Subscribe;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Query("select s from Subscribe s where s.active = true ")
    List<Subscribe> findAllByActive();

    @Query("select s from Subscribe s")
    List<Subscribe> findAll();

    @Query("select s from Subscribe s where s.active = false ")
    List<Subscribe> findAllByActiveFalse();



}
