package rookies.demo.dto;

import lombok.Data;

@Data
public class ProductImgDto{
    long id;
    long productId;
    String imgUrl;
}