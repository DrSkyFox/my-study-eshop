package ru.geekbrains.persist.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
