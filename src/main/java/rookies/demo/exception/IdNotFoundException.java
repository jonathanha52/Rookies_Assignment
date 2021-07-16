package rookies.demo.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id){
        super("Id " + id + " not found!"); 
    }
    public IdNotFoundException(Integer id){
        super("Id " + id + " not found!");
    }
}
