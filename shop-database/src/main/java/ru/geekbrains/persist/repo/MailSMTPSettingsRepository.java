package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.MailSMTPSettings;

@Repository
public interface MailSMTPSettingsRepository extends JpaRepository<MailSMTPSettings, Long> {


}
