package rookies.demo.service;

import java.util.List;
import rookies.demo.model.Rating;
import rookies.demo.dto.RatingDto;

public interface IRatingService {
    public List<Rating> findRatingOfProduct(long productId);
    public void insertRating(RatingDto ratingDto);
    public void deleteRating(RatingDto ratingDto);
    public void updateRating(RatingDto ratingDto);
}
