package rookies.demo.service;

import java.util.List;
import rookies.demo.model.Product;
import rookies.demo.model.Category;

public interface IProductService {
    public List<Product> findAllProduct();
    //public List<Product> findProductByName(String name);
    public List<Product> findByCategory(Category category);
    public Product insertProduct(Product product);
    public void deleteProduct(Product product);
    public void updateProduct(Product product);
}
