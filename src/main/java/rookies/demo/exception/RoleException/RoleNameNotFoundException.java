package rookies.demo.exception.RoleException;

public class RoleNameNotFoundException extends RuntimeException{
    public RoleNameNotFoundException(String name){
        super("Category with name "+name+"not found!");
    }
}
