package rookies.demo.service;

import java.util.List;

import rookies.demo.model.Category;

public interface ICategoryService {
    public List<Category> findAllCategory();
    public void updateCategory(int id, Category category);
    public void deleteCategory(int id, Category category);
    public void insertCategory(Category category);
}
