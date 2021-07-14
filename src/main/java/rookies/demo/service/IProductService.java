package rookies.demo.service;

import java.util.List;
import rookies.demo.model.Product;
import rookies.demo.dto.ProductDto;

public interface IProductService {

    public List<Product> findAll();
    public List<Product> findProductByPage(int page, int itemPerPage);
    public List<Product> findProductByName(String name, int page, int itemPerPage);
    public List<Product> findByCategory(String category, int page, int itemPerPage);
    public Product findById(Long id);
    public void insertProduct(Product product);
    public void deleteProduct(long id, Product product);
    public void updateProduct(long id, Product product);
    public Product DtoToEntity(ProductDto productDto);
    public ProductDto EntityToDto(Product product);
}
