package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.KindProduct;

public interface KindProductRepository extends JpaRepository<KindProduct,  Long> {

}
