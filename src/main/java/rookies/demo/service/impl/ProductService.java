package rookies.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rookies.demo.model.Category;
import rookies.demo.model.Product;
import rookies.demo.repository.ProductRepository;
import rookies.demo.service.IProductService;

import rookies.demo.exception.IdNotFoundException;

@Service("productService")
public class ProductService implements IProductService{

    ProductRepository productRepository; 

    @Autowired
    public ProductService(@Qualifier("productRepo")ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product findById(Long id){
        return this.productRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }
    @Override
    public List<Product> findAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return this.productRepository.findByCategory(category);
    }

    @Override
    public Product insertProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        Long id = product.getId();
        Product result = this.productRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        result.setCategory(product.getCategory());
        result.setPrice(product.getPrice());
        result.setProductDescription(product.getProductDescription());
        result.setProductName(product.getProductName());
        result.setUnit(product.getUnit());
    }
    
}
