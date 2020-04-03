package pl.springinaction.tacocloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.springinaction.tacocloud.model.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
