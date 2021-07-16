package rookies.demo.exception.RoleException;

public class RoleNameNotFoundException extends RuntimeException{
    public RoleNameNotFoundException(String name){
        super("Role with name "+name+" not found!");
    }
}
