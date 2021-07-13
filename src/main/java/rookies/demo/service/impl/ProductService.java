package rookies.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rookies.demo.model.Category;
import rookies.demo.model.Product;
import rookies.demo.repository.CategoryRepository;
import rookies.demo.repository.ProductRepository;
import rookies.demo.service.IProductService;

import rookies.demo.exception.IdNotFoundException;
import rookies.demo.exception.CategoryException.CategoryNotFoundException;

@Service("productService")
public class ProductService implements IProductService{

    ProductRepository productRepository; 
    CategoryRepository categoryRepository;

    @Autowired
    public ProductService(@Qualifier("productRepo")ProductRepository productRepository, @Qualifier("categoryRepo")CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    public List<Product> findByProductName(String name){
        return productRepository.findByProductNameContaining(name);
    }
    @Override
    public Product findById(Long id){
        return this.productRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }
    @Override
    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        Iterable<Product> fromDb = this.productRepository.findAll();
        fromDb.forEach(result::add); 

        return result;
    }

    @Override
    public List<Product> findByCategory(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotFoundException(name));
        return this.productRepository.findByCategory(category);
    }

    @Override
    public Product insertProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        this.productRepository.findById(product.getId()).orElseThrow(() -> new IdNotFoundException(product.getId()));
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
    @Override
    public List<Product> findProductByPage(int page, int itemPerPage) {
        int from = (page-1)*itemPerPage;
        int to = page*itemPerPage;
        Pageable pageable = PageRequest.of(from, to);
        List<Product> result = new ArrayList<>();
        Iterable<Product> fromDb = this.productRepository.findAll(pageable);
        fromDb.forEach(result::add); 

        return result;
    }
    
}
