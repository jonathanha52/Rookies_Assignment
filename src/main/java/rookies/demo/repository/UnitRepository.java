package rookies.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rookies.demo.model.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer>{
    public Optional<Unit> findById(int id);
}
