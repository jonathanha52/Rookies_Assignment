package rookies.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserDto {
    @JsonProperty(access = Access.READ_ONLY)
    long id;
    String username;
    String firstName;
    String lastName;
    String email;

    public UserDto(){}
    public UserDto(long id, String username, String firstName, String lastName, String email){
        this.id = id; 
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public Long getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.username + " ");
        builder.append(this.email + " ");
        builder.append(this.firstName + " ");
        builder.append(this.lastName + "/n");
        return builder.toString();
    }
}
