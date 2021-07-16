package rookies.demo.exception.CategoryException;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String e){
        super("Category "+e+"not found!");
    }
}
