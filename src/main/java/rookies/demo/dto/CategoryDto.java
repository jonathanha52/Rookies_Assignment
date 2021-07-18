package rookies.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class CategoryDto {
    @JsonProperty(access = Access.READ_ONLY)
    Integer id;
    String name;
    String description;

    public CategoryDto(){}
    public CategoryDto(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
    //GETTER
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    //SETTER
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
