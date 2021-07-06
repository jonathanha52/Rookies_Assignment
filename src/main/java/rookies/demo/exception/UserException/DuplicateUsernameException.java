package rookies.demo.exception.UserException;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String username){
        super("Username" + username + "has already existed!");
    }
}
