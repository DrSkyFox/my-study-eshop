package ru.geekbrains.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
