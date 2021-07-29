package rookies.demo.exception.RatingException;

public class DuplicateRatingException extends RuntimeException{
    public DuplicateRatingException(){
        super("Product has already been rated by this user! Try update method instead");
    }
}
