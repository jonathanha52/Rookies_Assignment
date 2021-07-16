package rookies.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rookies.demo.dto.RatingDto;
import rookies.demo.model.Rating;
import rookies.demo.service.IRatingService;

@Service
public class RatingService implements IRatingService{

    @Override
    public List<Rating> findRatingOfProduct(long productId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertRating(RatingDto ratingDto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteRating(RatingDto ratingDto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateRating(RatingDto ratingDto) {
        // TODO Auto-generated method stub
        
    }
    
}
