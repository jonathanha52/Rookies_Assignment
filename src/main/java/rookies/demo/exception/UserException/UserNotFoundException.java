package rookies.demo.exception.UserException;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String e){
        super("User with username "+" "+"not found!");
    }
}
