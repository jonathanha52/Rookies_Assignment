package rookies.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rookies.demo.model.Category;

public interface CategoryReposity extends JpaRepository<Category, Integer>{
    public Optional<Category> findById(int id);
}
