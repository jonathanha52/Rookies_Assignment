package rookies.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import rookies.demo.model.Product;
import rookies.demo.model.Category;

@Repository("productRepo")
public interface ProductRepository extends JpaRepository<Product, Long>{
    public List<Product> findByProductNameContaining(String name);
    public List<Product> findByCategory(Category category);
}
