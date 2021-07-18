package rookies.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rookies.demo.model.Category;
import rookies.demo.model.Product;
import rookies.demo.repository.CategoryRepository;
import rookies.demo.repository.ProductPagingRepository;
import rookies.demo.repository.ProductRepository;
import rookies.demo.service.IProductService;
import rookies.demo.dto.ProductDto;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.exception.CategoryException.CategoryNameNotFoundException;

@Service("productService")
public class ProductService implements IProductService{

    ProductPagingRepository productPagingRepository;
    ProductRepository productRepository; 
    CategoryRepository categoryReposity;

    @Autowired
    public ProductService(ProductPagingRepository productPagingRepository, ProductRepository productRepository, CategoryRepository categoryReposity){
        this.productPagingRepository = productPagingRepository;
        this.productRepository = productRepository;
        this.categoryReposity = categoryReposity;
    }
    @Override
    public List<Product> findAllByName(String name){
        return productRepository.findByProductNameContaining(name);
    }

    @Override
    public List<Product> findAllByCategory(String categoryName){
        Category category = categoryReposity.findByName(categoryName).orElseThrow(() -> new CategoryNameNotFoundException(categoryName));
        return this.productRepository.findByCategory(category);
    }
    @Override
    public Product findById(Long id){
        return this.productRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    @Override
    public void insertProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id, Product product) {
        this.productRepository.findById(product.getId()).orElseThrow(() -> new IdNotFoundException(product.getId()));
        this.productRepository.delete(product);
    }

    @Override
    @Transactional
    public void updateProduct(long id, Product product) {
        Product result = this.productRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        result.setCategory(product.getCategory());
        result.setPrice(product.getPrice());
        result.setProductDescription(product.getProductDescription());
        result.setProductName(product.getProductName());
        result.setUnit(product.getUnit());
    }
    @Override
    public List<Product> findProductByPage(int page, int itemPerPage) {
        int[] paging = calculatePage(page, itemPerPage);
        Pageable pageable = PageRequest.of(paging[0], paging[1]);
        Iterable<Product> fromDb = this.productRepository.findAll(pageable);
        List<Product> result = new ArrayList<>();
        fromDb.forEach(result::add);

        return result;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findPagingByName(String name, int page, int itemPerPage) {
        int[] paging = this.calculatePage(page, itemPerPage);
        Pageable pageable = PageRequest.of(paging[0], paging[1]);
        List<Product> result = this.productPagingRepository.findAllByProductNameContaining(name, pageable);
        return result;
    }

    @Override
    public List<Product> findPagingByCategory(String category, int page, int itemPerPage) {
        Category resultCategory = this.categoryReposity.findByName(category).orElseThrow(() -> new CategoryNameNotFoundException(category));
        int[] paging = this.calculatePage(page, itemPerPage);
        Pageable pageable = PageRequest.of(paging[0], paging[1]);
        List<Product> result = this.productPagingRepository.findAllByCategory(resultCategory, pageable);
        return result;
    }

    
    private int[] calculatePage(int page, int itemPerPage){
        int[] result = new int[2];
        result[0] = (page-1)*itemPerPage;
        result[1] = page*itemPerPage - 1;
        return result;
    }

    @Override
    public Product DtoToEntity(ProductDto productDto) {
        Product product = new Product();
        Category category = categoryReposity.findByName(productDto.getCategory()).orElseThrow(() -> new CategoryNameNotFoundException(productDto.getCategory()));
        product.setProductName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        product.setProductDescription(productDto.getDescription());
        product.setUnit(productDto.getUnit());
        product.setCreatedDate(productDto.getCreatedDate());
        product.setUpdateddDate(productDto.getUpdatedDate());
        return product;
    }

    @Override
    public ProductDto EntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setCategory(product.getCategory().getName());
        productDto.setDescription(product.getProductDescription());
        productDto.setId(product.getId());
        productDto.setUnit(product.getUnit());
        productDto.setCreatedDate(product.getCreatedDate());
        productDto.setUpdatedDate(product.getUpdatedDate());
        productDto.setName(product.getProductName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
    
}
