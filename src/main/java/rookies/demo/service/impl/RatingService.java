package rookies.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rookies.demo.dto.RatingDto;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.exception.RatingException.DuplicateRatingException;
import rookies.demo.exception.RatingException.RatingNotFoundException;
import rookies.demo.model.Product;
import rookies.demo.model.Rating;
import rookies.demo.repository.ProductRepository;
import rookies.demo.repository.RatingRepository;
import rookies.demo.repository.UsersRepository;
import rookies.demo.service.IRatingService;

import rookies.demo.model.Users;
@Service
public class RatingService implements IRatingService{
    UsersRepository usersRepository;
    ProductRepository productRepository;
    RatingRepository ratingRepository;

    @Autowired
    public RatingService(UsersRepository usersRepository, ProductRepository productRepository, RatingRepository ratingRepository){
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
        this.ratingRepository = ratingRepository;
    }
    @Override
    public List<Rating> findRatingOfProduct(long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new IdNotFoundException(productId));
        return this.ratingRepository.findByProduct(product);
    }

   @Override
    public void insertRating(Rating rating) {
        Optional<Rating> test = this.ratingRepository.findByUserAndProduct(rating.getUser(), rating.getProduct());
        if(!test.isPresent())
            this.ratingRepository.save(rating);
        else
            throw new DuplicateRatingException();
        
    }

    @Override
    public void deleteRating(Long userId, Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new IdNotFoundException(productId));
        Users user = this.usersRepository.findById(userId).orElseThrow(() -> new IdNotFoundException(userId));
        Rating rating = this.ratingRepository.findByUserAndProduct(user, product).orElseThrow(() -> new RatingNotFoundException());
        this.ratingRepository.delete(rating);
    }

    @Override
    @Transactional
    public void updateRating(Long userId, Long productId, RatingDto ratingDto) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new IdNotFoundException(productId));
        Users user = this.usersRepository.findById(userId).orElseThrow(() -> new IdNotFoundException(userId));
        Rating rating = this.ratingRepository.findByUserAndProduct(user, product).orElseThrow(() -> new RatingNotFoundException());
        rating.setComment(ratingDto.getComment());
        rating.setRating(ratingDto.getRating());
    }
    @Override
    public Rating DtoToEntity(RatingDto ratingDto) {
        Rating result = new Rating();
        Product product = this.productRepository.findById(ratingDto.getProductId()).orElseThrow(() -> new IdNotFoundException(ratingDto.getProductId()));
        Users user = this.usersRepository.findById(ratingDto.getUserId()).orElseThrow(() -> new IdNotFoundException(ratingDto.getUserId()));
        result.setComment(ratingDto.getComment());
        result.setRating(ratingDto.getRating());
        result.setUser(user);
        result.setProduct(product);

        return result;
    }
    @Override
    public RatingDto EntityToDto(Rating rating) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setComment(rating.getComment());
        ratingDto.setId(rating.getId());
        ratingDto.setRating(rating.getRating());
        ratingDto.setUserId(rating.getUser().getUserID());
        ratingDto.setProductId(rating.getProduct().getId());
        return ratingDto;
    }
    
}
