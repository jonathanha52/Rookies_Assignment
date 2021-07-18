package rookies.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rookies.demo.model.RoleName;
import rookies.demo.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer>{
    public Optional<Roles> findByName(RoleName name);
}
