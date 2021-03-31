package ru.geekbrains.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.Product;

public final class ProductSpecification {
    public static Specification<Product> productLike(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> minCost(Double minCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.ge(root.get("cost"), minCost);
    }

    public static Specification<Product> maxCost(Double maxCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.le(root.get("cost"), maxCost);
    }

}
