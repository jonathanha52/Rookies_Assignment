package rookies.demo.exception.CategoryException;

public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(String e){
        super("Category with name "+e+" is already existing!");
    }
    
}
