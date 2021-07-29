package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.dto.CategoryDto;
import rookies.demo.model.Category;
import rookies.demo.service.impl.CategoryService;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/category")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/public")
    public List<Category> findAllCategory(){
        return this.categoryService.findAllCategory();    
    }

    @PostMapping("/admin")
    public void insertCategory(@RequestBody CategoryDto categoryDto){
        Category category = this.categoryService.DtoToEntity(categoryDto);
        this.categoryService.insertCategory(category);
    }

    @PutMapping("/admin/{id}")
    public void updateCategory(@PathVariable("id") int id, @RequestBody CategoryDto categoryDto){
        Category category = this.categoryService.DtoToEntity(categoryDto);
        this.categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        this.categoryService.deleteCategory(id);
    }
}
