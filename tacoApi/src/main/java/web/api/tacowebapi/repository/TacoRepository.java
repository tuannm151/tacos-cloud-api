package web.api.tacowebapi.repository;

import org.springframework.data.repository.CrudRepository;
import web.api.tacowebapi.model.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
