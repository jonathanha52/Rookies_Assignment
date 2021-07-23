package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.dto.RatingDto;
import rookies.demo.model.Rating;
import rookies.demo.service.impl.RatingService;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/ratings")
@RestController
public class RatingController {

    private final RatingService ratingService;
    
    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    @GetMapping("/productId={productId}")
    public List<Rating> getRatingOfProduct(@PathVariable("productId") long id){
        return this.ratingService.findRatingOfProduct(id);
    }

    @PostMapping()
    public void insertRating(@RequestBody RatingDto ratingDto){
        Rating rating = this.ratingService.DtoToEntity(ratingDto);
        this.ratingService.insertRating(rating);
    }

    @PutMapping("/productId={productId}&userId={userId}")
    public void updateRating(@PathVariable("productId") long productId, @PathVariable("userId") long userId, @RequestBody RatingDto ratingDto){
        this.ratingService.updateRating(userId, productId, ratingDto);
    }

    @DeleteMapping("/productId={productId}&userId={userId}")
    public void deleteMapping(@PathVariable("productId") long productId, @PathVariable("userId") long userId){
        this.ratingService.deleteRating(userId, productId);
    }
}
