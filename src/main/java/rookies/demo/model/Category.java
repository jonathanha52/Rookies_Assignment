package rookies.demo.model;

import java.util.Objects;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;


@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    Integer id;

    @Column(name = "category_name")
    @NaturalId
    String name;

    @Column(name = "description")
    String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "id")
    List<Product> products;

    public Category(){}
    public Category(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
    //GETTER
    public int getId(){
        return this.id;
    }
    public String getName() {
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
    public void setDescrption(String description){
        this.description = description;
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
        boolean isEqual = this.id == other.id &&
            this.name.equals(other.name) &&
            this.description.equals(other.description);
        return isEqual;
    }
}
