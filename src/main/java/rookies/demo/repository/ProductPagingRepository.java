package rookies.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import rookies.demo.model.Category;
import rookies.demo.model.Product;

@Repository
public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Long>{
    public List<Product> findAllByPrice(double price, Pageable pageable);
    public List<Product> findAllByProductNameContaining(String name, Pageable pageable);
    public List<Product> findAllByCategory(Category category, Pageable pageable);
}
