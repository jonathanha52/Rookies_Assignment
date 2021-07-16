package rookies.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


public class ProductDto {
    @JsonProperty(access = Access.READ_ONLY)
    Long id;
    String name;
    String category;
    String unit;
    Long createBy;
    String description;
    double price;
    Date createdDate;
    Date updatedDate;
    public ProductDto(){}
    public ProductDto(Long id, String name, String category, Long createdBy
            , String description, double price, Date createdDate, Date updatedDate){
        this.id = id;
        this.name = name;
        this.category = category;
        this.createBy = createdBy;
        this.description = description;
        this.price = price;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    //GETTER
    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getUnit(){
        return this.unit;
    }
    public String getCategory(){
        return this.category;
    }
    public long getCreateBy(){
        return this.createBy;
    }
    public String getDescription(){
        return this.description;
    }
    public double getPrice(){
        return this.price;
    }
    public Date getCreatedDate(){
        return this.createdDate;
    }
    public Date getUpdatedDate(){
        return this.updatedDate;
    }
    //SETTER
    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUnit(String unit){
        this.unit = unit;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setCreateBy(long id){
        this.createBy = id;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }
    public void setUpdatedDate(Date updatedDate){
        this.updatedDate = updatedDate;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("ProductDto = [");
        builder.append("Id = "+this.id + "\n");
        builder.append("Name = "+ this.name + "\n");
        return builder.toString();
    }
}
