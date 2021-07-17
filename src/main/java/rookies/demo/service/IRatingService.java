package rookies.demo.service;

import java.util.List;
import rookies.demo.model.Rating;
import rookies.demo.dto.RatingDto;

public interface IRatingService {
    public List<Rating> findRatingOfProduct(long productId);
    public void insertRating(Rating rating);
    public void deleteRating(Long userId, Long productId);
    public void updateRating(Long userId, Long productId,RatingDto ratingDto);
    public Rating DtoToEntity(RatingDto ratingDto);
    public RatingDto EntityToDto(Rating rating);
}
