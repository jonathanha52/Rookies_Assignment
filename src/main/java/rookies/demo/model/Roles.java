package rookies.demo.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import javax.persistence.GenerationType;

import java.util.Objects;

@Entity
@Table(name="roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name")
    RoleName name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    List<Users> users;

    public Roles(){}
    public Roles(int id, RoleName name){
        this.id = id;
        this.name = name;
    }
    public int getId(){
        return this.id;
    }
    public RoleName getRoleName(){
        return this.name;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setRoleName(RoleName name){
        this.name = name;
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
        Roles other = (Roles)obj;
        return this.id == other.id;
    }
    @Override
    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Role[");
        strBuilder.append("id = " + this.id + ", ");
        strBuilder.append("name = "+this.name+"]");
        return strBuilder.toString();
    }

}
