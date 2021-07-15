package rookies.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rookies.demo.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{
    public Optional<Roles> findByName(String name);
}
