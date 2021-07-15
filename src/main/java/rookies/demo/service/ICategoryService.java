package rookies.demo.service;

import java.util.List;

import rookies.demo.model.Category;
import rookies.demo.dto.CategoryDto;;

public interface ICategoryService {
    public List<Category> findAllCategory();
    public void updateCategory(int id, Category category);
    public void deleteCategory(int id, Category category);
    public void insertCategory(Category category);
    public Category DtoToEntity(CategoryDto categoryDto);
    public CategoryDto EntityToEdto(Category category);
}
