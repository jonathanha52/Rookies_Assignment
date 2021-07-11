package rookies.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    RoleName roleName;

    protected Roles(){}
    public Roles(int id, RoleName roleName){
        this.id = id;
        this.roleName = roleName;
    }
    public int getId(){
        return this.id;
    }
    public RoleName getRoleName(){
        return this.roleName;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setRoleName(RoleName roleName){
        this.roleName = roleName;
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
        strBuilder.append("name = "+this.roleName+"]");
        return strBuilder.toString();
    }

}
