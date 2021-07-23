package rookies.demo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ProductDto {
    @JsonProperty(access = Access.READ_ONLY)
    Long id;
    String name;
    String category;
    String unit;
    Long createBy;
    String description;
    String imgUrl;
    double price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date updatedDate;
    public ProductDto(){}
    public ProductDto(Long id, String name, String category, Long createdBy, String imgUrl
            , String description, double price, Date createdDate, Date updatedDate){
        this.id = id;
        this.name = name;
        this.category = category;
        this.createBy = createdBy;
        this.imgUrl = imgUrl;
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
    public String getImgUrl(){
        return this.imgUrl;
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
    public void setImgUrl(String url){
        this.imgUrl = url;
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
