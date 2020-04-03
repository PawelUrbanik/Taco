package pl.springinaction.tacocloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springinaction.tacocloud.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
