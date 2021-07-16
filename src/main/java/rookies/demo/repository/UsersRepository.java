package rookies.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rookies.demo.model.Users;

@Repository("users")
public interface UsersRepository extends JpaRepository<Users, Integer>{
    public Optional<Users> findByUsername(String username);
    public boolean existsByEmail(String email);
    public boolean existsByUsername(String username);
}
