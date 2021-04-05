package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persist.model.Product;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findProductByNameLike(String productName);


    @Query("select p from Product p " +
            "where (p.name like :productname or :productname is null) and " +
            "      (p.cost >= :minCost or :minCost is null) and " +
            "      (p.cost <= :maxCost or :maxCost is null)")
    List<Product> findWithFilter(@Param("productname") String productFilter,
                                 @Param("minCost") Double minCost,
                                 @Param("maxCost") Double maxCost);
}
