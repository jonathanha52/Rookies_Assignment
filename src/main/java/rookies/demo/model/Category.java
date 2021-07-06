package rookies.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    Integer id;

    @Column(name = "category_name")
    String name;

    protected Category(){}
    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Category = [");
        builder.append(String.format("ID = %d, ", this.id));
        builder.append("name = "); builder.append(this.name);
        builder.append("]\n");
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
        if(this.getClass() != obj.getClass())
            return false;
        Category other = (Category)obj;
        return this.id == other.id;
    }
}
