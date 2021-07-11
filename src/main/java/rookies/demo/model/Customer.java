package rookies.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    Long id;

    @Column(name ="username")
    String userName;

    @Column(name="first_name") 
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="password")
    String password;

    protected Customer(){}
    public Customer(Long id, String username, String firstName, String lastName, String password){
        this.id = id;
        this.userName = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    //GETTER
    public Long getId(){
        return this.id;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getPassword(){
        return this.password;
    }
    //SETTER
    public void setId(Long id){
        this.id = id;
    }
    public void setUserName(String username){
        this.userName = username;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public String toString(){
        return this.userName + " " + this.firstName + " " + this.lastName;
    }
}
