package rookies.demo.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id){
        super("Id " + id + " not found!"); 
    }
}
