package rookies.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit", referencedColumnName = "unit_id")
    Unit unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    Category category;

    protected Product(){}
    public Product(long id, String productName, String productDescription, Double price, Unit unit, Category category){
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.unit = unit;
        this.category = category;
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
    public Unit getUnit(){
        return this.unit;
    }
    public Category getCategory(){
        return this.category;
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
    public void setUnit(Unit unit){
        this.unit = unit;
    }
    public void setCategory(Category category){
        this.category = category;
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
        return this.id == other.id;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Product = [");
        builder.append("Id " + this.id + ", ");
        builder.append("name = "+this.productName+", ");
        builder.append("description = "+this.productDescription+", ");
        builder.append("price = "+this.price+", ");
        builder.append("unit = "+this.unit.getName() + ", ");
        builder.append("category = "+this.category.getName() + "]\n");
        return builder.toString();
    }
}
