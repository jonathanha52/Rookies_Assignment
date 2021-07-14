package rookies.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDto {
    Long id;
    String name;
    String category;
    String unit;
    Long createBy;
    String description;
    double price;
    Date createdDate;
    Date updatedDate;
}
