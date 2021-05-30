package ru.geekbrains.persist.repositories.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.goods.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
