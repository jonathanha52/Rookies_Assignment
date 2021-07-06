package rookies.demo.service;

import java.util.List;
import rookies.demo.model.Product;

public interface IProductService {
    public List<Product> findAllProduct();
    public List<Product> findProductByName(String name);
}
