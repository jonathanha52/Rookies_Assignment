package rookies.demo.service;

import java.util.List;

import rookies.demo.model.Product;
import rookies.demo.dto.ProductDto;

public interface IProductService {

    public List<Product> findAll();
    public List<Product> findAllByName(String name);
    public List<Product> findAllByCategory(String category);
    public List<Product> findProductByPage(int page, int itemPerPage);
    public List<Product> findPagingByName(String name, int page, int itemPerPage);
    public List<Product> findPagingByCategory(String category, int page, int itemPerPage);
    public Product findById(Long id);
    public void insertProduct(Product product);
    public void deleteProduct(long id);
    public void updateProduct(long id, Product product);
    public Product DtoToEntity(ProductDto productDto);
    public ProductDto EntityToDto(Product product);
}
