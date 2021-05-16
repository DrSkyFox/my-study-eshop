package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.MailSMTPSettings;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailSMTPSettingsRepository extends JpaRepository<MailSMTPSettings, Long> {

    @Query("select m from MailSMTPSettings m")
    List<MailSMTPSettings> findMailSMTPSettings();

}
