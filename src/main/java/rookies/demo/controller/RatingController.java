package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.dto.ProductDto;
import rookies.demo.model.Rating;
import rookies.demo.service.impl.RatingService;

@RequestMapping("api/v1/rating")
@RestController
public class RatingController {

    private final RatingService ratingService;
    
    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    @GetMapping("/product={productId}")
    public List<Rating> getRatingOfProduct(@PathVariable("productId") long id){
        //TODO: Implement 
        return null;
    }

    @PostMapping("/product={productId}&user={userId}")
    public void insertRating(@PathVariable("productId") long productId, @PathVariable("userId") long userId, @RequestBody ProductDto productDto){
        //TODO: Implement
    }

    @PutMapping("/product={productId}&user={userId}")
    public void updateRating(@PathVariable("productId") long productId, @PathVariable("userId") long userId, @RequestBody ProductDto productDto){
        //TODO: Implement
    }

    @DeleteMapping
    public void deleteMapping(@PathVariable("productId") long productId, @PathVariable("userId") long userId, @RequestBody ProductDto productDto){
        //TODO: Implement
    }
}
