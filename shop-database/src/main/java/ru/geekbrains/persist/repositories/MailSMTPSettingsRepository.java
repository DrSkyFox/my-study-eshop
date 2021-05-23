package ru.geekbrains.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.MailSMTPSettings;

import java.util.List;

@Repository
public interface MailSMTPSettingsRepository extends JpaRepository<MailSMTPSettings, Long> {

    @Query("select m from MailSMTPSettings m")
    List<MailSMTPSettings> findMailSMTPSettings();

}
