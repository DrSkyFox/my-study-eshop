package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.Subscribe;

public final class SubscribeSpecification {

    public static Specification<Subscribe> byId(long id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

}
