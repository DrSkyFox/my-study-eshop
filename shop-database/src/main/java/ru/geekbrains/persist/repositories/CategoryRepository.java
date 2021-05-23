package ru.geekbrains.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
