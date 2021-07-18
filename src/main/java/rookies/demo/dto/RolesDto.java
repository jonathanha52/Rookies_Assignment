package rookies.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RolesDto {
    Integer id;
    String name;

    public RolesDto(int id, String name){
        this.id = id;
        this.name = name;
    }
}
