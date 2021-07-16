package rookies.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "rating")
public class Rating {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_name")
    Product product;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    Users user;

    @Column(name = "rating")
    int rating;

    @Column(name = "comment")
    String comment;

    protected Rating(){}
    public Rating(long id, Product product, Users user, int rating, String comment){
        this.id = id;
        this.product = product;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }
    //GETTER
    public long getId(){
        return this.id;
    }
    public Product getProduct(){
        return this.product;
    }
    public Users getUser(){
        return this.user;
    }
    public int getRating(){
        return this.rating;
    }
    public String getComment(){
        return this.comment;
    }
    //SETTER
    public void setId(long id){
        this.id = id;
    }
    public void setProduct(Product product){
        this.product = product;
    }
    public void setUser(Users user){
        this.user = user;
    }
    public void setRating(int rating){
        this.rating = rating;
    }
    public void getComment(String comment){
        this.comment = comment;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id);
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Rating other = (Rating)obj;
        return this.id == other.id;
    }
    @Override 
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Rating = [");
        builder.append("Id = "+this.id+", ");
        builder.append("product = "+this.product.getProductName()+", ");
        builder.append("user = "+this.user.getUsername()+", ");
        builder.append("rating = "+this.rating+", ");
        builder.append("comment = "+this.comment + "]\n");
        return builder.toString();
    }
}
