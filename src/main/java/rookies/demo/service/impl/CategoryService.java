package rookies.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rookies.demo.dto.CategoryDto;
import rookies.demo.exception.DuplicateException;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.exception.CategoryException.DuplicateNameException;
import rookies.demo.model.Category;
import rookies.demo.repository.CategoryRepository;
import rookies.demo.service.ICategoryService;

@Service("categoryService")
public class CategoryService implements ICategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(@Qualifier("categoryRepo") CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategory() {
        return this.categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void updateCategory(int id, Category category) {
        Category result = this.categoryRepository.findById(id).orElseThrow(
            () -> new IdNotFoundException(id));
        Optional<Category> test = this.categoryRepository.findByName(category.getName());
        if(test.isPresent()){
            if(test.get().getId() != id)
                throw new DuplicateNameException(category.getName());
        }
        else{
            result.setName(category.getName());
        result.setDescrption(category.getDescription());
        }
    }

    @Override
    public void deleteCategory(int id) {
        Category category =  this.categoryRepository.findById(id).orElseThrow(
            () -> new IdNotFoundException(id));
        this.categoryRepository.delete(category);
        
    }

    @Override
    public void insertCategory(Category category) {
        Optional<Category> result = this.categoryRepository.findByName(category.getName());
        if(!result.isPresent())
            this.categoryRepository.save(category);
        else
            throw new DuplicateException("Category");
    }

    @Override
    public Category DtoToEntity(CategoryDto categoryDto) {
        Category entity = new Category();
        entity.setName(categoryDto.getName());
        entity.setDescrption(categoryDto.getDescription());
        return entity;
    }

    @Override
    public CategoryDto EntityToEdto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setId(category.getId());
        return dto;
    }
    
}
