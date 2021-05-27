package ru.geekbrains.persist.repositories.mailling;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.mailing.Subscribe;

public final class SubscribeSpecification {

    public static Specification<Subscribe> byId(long id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

}
