package rookies.demo.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import rookies.demo.exception.DuplicateException;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.model.Category;
import rookies.demo.repository.CategoryRepository;
import rookies.demo.service.impl.CategoryService;

@SpringBootTest
public class CategoryServiceTest {
    private final int VALID_ID = 1;
    private final int INVALID_ID = -1;
    private final String CATEGORY_NAME = "test category";
    private final String CATEGORY_DESCRIPTION = "this is a test category";
    private final Category VALID_CATEGORY = new Category(
        VALID_ID,
        CATEGORY_NAME,
        CATEGORY_DESCRIPTION
    );
    private final List<Category> ALL_CATEGORY = new ArrayList<>(Arrays.asList(VALID_CATEGORY));
    
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testFindAllCategory(){
        when(categoryRepository.findAll()).thenReturn(ALL_CATEGORY);
        List<Category> result = categoryService.findAllCategory();
        assertEquals(ALL_CATEGORY.size(), result.size());
    }

    @Test
    public void testInsertCategory_duplicateCategory(){
        when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(Optional.of(VALID_CATEGORY));
        assertThrows(DuplicateException.class, () ->{
            categoryService.insertCategory(VALID_CATEGORY);
        });
    }

    @Test
    public void testUpdateCategory_CategoryNotFound(){
        when(categoryRepository.findById(INVALID_ID)).thenThrow(new IdNotFoundException(INVALID_ID));
        assertThrows(IdNotFoundException.class, () ->{
            categoryService.updateCategory(INVALID_ID, VALID_CATEGORY);
        });
    }

    @Test
    public void testDeleteCategory_CategoryNotFound(){
        when(categoryRepository.findById(INVALID_ID)).thenThrow(new IdNotFoundException(INVALID_ID));
        assertThrows(IdNotFoundException.class, () ->{
            categoryService.deleteCategory(INVALID_ID);
        });
    }
}
