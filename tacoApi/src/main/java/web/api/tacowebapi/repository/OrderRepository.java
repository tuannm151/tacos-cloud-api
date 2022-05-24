package web.api.tacowebapi.repository;

import org.springframework.data.repository.CrudRepository;
import web.api.tacowebapi.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
