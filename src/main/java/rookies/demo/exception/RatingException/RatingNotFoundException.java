package rookies.demo.exception.RatingException;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(){
        super("Rating not found");
    }
}
