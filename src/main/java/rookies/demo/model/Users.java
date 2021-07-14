package rookies.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GenerationType;

@Entity
@Table(name="users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    Long userID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id", referencedColumnName = "role_id")
    Roles role;

    @Column(name="password")
    String password;
    
    @Column(name="username", unique = true)
    String username;

    @Column(name="email", unique = true)
    String email;

    @Column(name="first_name")
    String firstName = "";

    @Column(name="last_name")
    String lastName = "";

    protected Users(){}

    public Users(long id, Roles role, String username, String email, String firstName, String lastName, String password){
        this.userID = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //GETTER
    public Long getUserID(){
        return this.userID;
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
    public String getPassword(){
        return this.password;
    }
    public Roles getRole(){
        return this.role;
    }
    //SETTER
    public void setUserID(long id){
        this.userID = id;
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
    public void setPassword(String password){
        this.password = password;
    }
    public void setRole(Roles role){
        this.role = role;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("User=[");
        builder.append("Id=" + this.userID + " ");
        
        return builder.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.userID);
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Users other = (Users)obj;
        return this.userID == other.userID;
    }


}
