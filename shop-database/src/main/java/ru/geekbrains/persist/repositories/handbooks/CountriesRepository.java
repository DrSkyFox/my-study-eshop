package ru.geekbrains.persist.repositories.handbooks;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.handbooks.Country;

public interface CountriesRepository extends JpaRepository<Country, Long> {

}
