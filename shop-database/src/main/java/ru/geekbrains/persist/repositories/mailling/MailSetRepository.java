package ru.geekbrains.persist.repositories.mailling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.mailing.Mailset;

@Repository
public interface MailSetRepository extends JpaRepository<Mailset, Long> {


}
