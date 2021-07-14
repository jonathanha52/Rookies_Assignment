package rookies.demo.model;

import java.util.Objects;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    Long id;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_description")
    String productDescription;

    @Column(name = "price")
    double price;

    @Column(name = "unit")
    String unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    Category category;

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    Date createdDate;

    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    Date updatedDate;

    public Product(){}
    public Product(long id, String productName, String productDescription, Double price, String unit, Category category, Date createdDate, Date updatedDate){
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.unit = unit;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public Product(String productName, String productDescription, Double price, String unit, Category category, Date createddDate, Date updatedDate){
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.unit = unit;
        this.category = category;
        this.createdDate = createddDate;
        this.updatedDate = updatedDate;
    }
    //GETTER
    public long getId(){
        return this.id;
    }
    public String getProductName(){
        return this.productName;
    }
    public String getProductDescription(){
        return this.productDescription;
    }
    public double getPrice(){
        return this.price;
    }
    public String getUnit(){
        return this.unit;
    }
    public Category getCategory(){
        return this.category;
    }
    public Date getCreatedDate(){
        return this.createdDate;
    }
    public Date getUpdatedDate(){
        return this.createdDate;
    }
    //SETTER
    public void setId(long id){
        this.id = id;;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setUnit(String unit){
        this.unit = unit;
    }
    public void setCategory(Category category){
        this.category = category;
    }
    public void setCreatedDate(Date date){
        this.createdDate = date;
    }
    public void setUpdateddDate(Date date){
        this.createdDate = date;
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
        Product other = (Product)obj;
        boolean isEqual = (this.id == other.id) &&
            this.productName.equals(other.productName) &&
            this.productDescription.equals(other.productDescription) &&
            (this.price == other.price) &&
            this.unit.equals(other.unit) &&
            this.category.equals(other.getCategory());
        return isEqual;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Product = [");
        builder.append("Id " + this.id + ", ");
        builder.append("name = "+this.productName+", ");
        builder.append("description = "+this.productDescription+", ");
        builder.append("price = "+this.price+", ");
        builder.append("unit = "+this.unit + ", ");
        builder.append("category = "+this.category.getName() + "]\n");
        return builder.toString();
    }
}
