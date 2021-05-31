package ru.geekbrains.persist.repositories.mailling;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.mailing.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

}
