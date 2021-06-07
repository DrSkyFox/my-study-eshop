package ru.geekbrains.persist.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.order.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {


}
