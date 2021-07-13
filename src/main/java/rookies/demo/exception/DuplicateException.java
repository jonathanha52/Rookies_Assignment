package rookies.demo.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String name){
        super("Duplicate item of class "+name);
    }
}
