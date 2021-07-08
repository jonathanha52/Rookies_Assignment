package rookies.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rookies.demo.model.Users;

@Repository("users")
public interface UsersRepository extends JpaRepository<Users, Integer>{
}
