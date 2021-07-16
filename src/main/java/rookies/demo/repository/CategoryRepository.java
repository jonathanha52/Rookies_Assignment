package rookies.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rookies.demo.model.Category;

@Repository("categoryRepo")
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    public Optional<Category> findById(int id);
    public Optional<Category> findByName(String name);
}
