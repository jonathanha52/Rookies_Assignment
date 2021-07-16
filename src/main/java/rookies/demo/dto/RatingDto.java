package rookies.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class RatingDto {
    @JsonProperty(access = Access.READ_ONLY)
    Long id;
    int rating;
    Long userId;
    Long productId;
    String comment;

    public RatingDto(){}
    public RatingDto(long id, int rating, long userId, long productId, String comment){
        this.id = id;
        this.rating = rating;
        this.userId = userId;
        this.productId = productId;
        this.comment = comment;
    }
    public long getId(){
        return this.id;
    }
    public int getRating(){
        return this.rating;
    }
    public String getComment(){
        return this.comment;
    }
    public long getUserId(){
        return this.userId;
    }
    public long getProductId(){
        return this.productId;
    }

    public void getId(long id){
        this.id = id;
    }
    public void getRating(int rating){
        this.rating = rating;
    }
    public void getComment(String comment){
        this.comment = comment;
    }
    public void getUserId(long userId){
        this.userId = userId;
    }
    public void getProductId(long productId){
        this.productId = productId;
    }

}
