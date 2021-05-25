package ru.geekbrains.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.Mailset;

import java.util.List;

@Repository
public interface MailSetRepository extends JpaRepository<Mailset, Long> {


}
