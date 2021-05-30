package ru.geekbrains.persist.repositories.accounts;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.accounts.User;

import javax.persistence.criteria.JoinType;

public final class UserSpecification {

    public static Specification<User> byId (long id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

    public static Specification<User> byRoles (long rolesId) {
        return (root, query, builder) -> builder.equal(root.get("roles").get("id"), rolesId);
    }

    public static Specification<User> fetchRoles() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("roles", JoinType.LEFT);
            criteriaQuery.distinct(true);
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        };
    }

}
