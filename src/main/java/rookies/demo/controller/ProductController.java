package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

@RequestMapping("/")
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
    @GetMapping("/page={count}")
    public List<Product> findByPage(@PathVariable int page){
        return this.productService.findProductByPage(page, ITEM_PER_PAGE);
    }
    @GetMapping("/search={name}")
    public List<Product> findByName(@PathVariable("name") String name){
        return this.productService.findByProductName(name);
    }

    @GetMapping("/id={id}")
    public Product findById(@PathVariable("id") long id){
        return this.productService.findById(id);
    }
    
    @PostMapping("admin/product")
    @Secured("ADMIN")
    public void addProduct(@RequestBody Product product){
        this.productService.insertProduct(product);
    }
    
    @DeleteMapping("admin/product/id={id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable("id") long id, @RequestBody Product product){
        this.productService.deleteProduct(product);
    }

    @PutMapping("admin/product/id={id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        this.productService.updateProduct(product);
    }


}
