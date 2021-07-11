package rookies.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unit")
public class Unit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(name = "unit_name")
    String name;

    protected Unit(){}
    public Unit(int id, String name){
        this.id = id;
        this.name = name;
    }
    //GETTER
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    //SETTER
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Unit = [");
        builder.append("Id="+ this.id);
        builder.append(" name="+this.name +"]\n");
        return builder.toString();
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
}
