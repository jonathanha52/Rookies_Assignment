package rookies.demo.exception.CategoryException;

public class CategoryNameNotFoundException extends RuntimeException{
    public CategoryNameNotFoundException(String e){
        super("Category with name " + e + " not found!");
    }
}
