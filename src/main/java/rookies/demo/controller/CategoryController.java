package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.model.Category;
import rookies.demo.service.impl.CategoryService;

@RequestMapping("api/v1/category")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> findAllCategory(){
        return this.categoryService.findAllCategory();    
    }

    @PostMapping
    public void insertCategory(@RequestBody Category category){
        this.categoryService.insertCategory(category);
    }

    @PutMapping("/id={id}")
    public void updateCategory(@PathVariable("id") int id, @RequestBody Category category){
        this.categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/id={id}")
    public void deleteProduct(@PathVariable("id") int id, @RequestBody Category category){
        this.categoryService.deleteCategory(id, category);
    }
}
