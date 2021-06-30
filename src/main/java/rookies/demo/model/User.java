package rookies.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="User")
public class User {

    @Id
    String userName;

    @Column(name="firstname") 
    String firstName;

    @Column(name="lastname")
    String lastName;

    @Column(name="password")
    String password;

    protected User(){}

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
    @Override
    public String toString(){
        return this.firstName + this.lastName;
    }
}
