package rookies.demo.service;

import java.util.List;
import rookies.demo.model.Product;
import rookies.demo.model.Category;

public interface IProductService {

    public Product findById(Long id);
    public List<Product> findProductByPage(int page, int itemPerPage);
    public List<Product> findAll();
    //public List<Product> findProductByName(String name);
    public List<Product> findByCategory(String name);
    public Product insertProduct(Product product);
    public void deleteProduct(Product product);
    public void updateProduct(Product product);
}
