package ru.geekbrains.persist.repositories.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.goods.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
