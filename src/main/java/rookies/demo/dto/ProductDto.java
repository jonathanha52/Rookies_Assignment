package rookies.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDto {
    Integer id;
    String name;
    int categoryId;
    int unitId;
    Long createBy;
    String description;
    double price;
    Date createdDate;
    Date updatedDate;
}
