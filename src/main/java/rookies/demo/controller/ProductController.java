package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.service.impl.ProductService;

import rookies.demo.model.Product;
import rookies.demo.dto.ProductDto;

@RequestMapping("/api/v1/product")
@RestController
public class ProductController {
    int ITEM_PER_PAGE = 60;
    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("productService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> mainPage(){
        return this.productService.findProductByPage(0, ITEM_PER_PAGE);
    }
    @GetMapping("/page={page}")
    public List<Product> findByPage(@PathVariable("page") int page){
        return this.productService.findProductByPage(page, ITEM_PER_PAGE);
    }
    @GetMapping("/name={name}&page={page}")
    public List<Product> findByName(@PathVariable("name") String name, @PathVariable("page") int page){
        return this.productService.findProductByName(name, page, ITEM_PER_PAGE);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") long id){
        return this.productService.findById(id);
    }
    
    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto){
        Product product = productService.DtoToEntity(productDto);
        this.productService.insertProduct(product);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto){
        Product product = productService.DtoToEntity(productDto);
        this.productService.deleteProduct(id, product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto){
        Product product = productService.DtoToEntity(productDto);
        this.productService.updateProduct(id, product);
    }


}
