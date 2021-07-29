package rookies.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rookies.demo.model.Rating;
import rookies.demo.model.Users;
import rookies.demo.model.Product;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    public Optional<Rating> findByUserAndProduct(Users user, Product product);
    public List<Rating> findByProduct(Product product);
}
