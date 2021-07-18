package rookies.demo.config;

import rookies.demo.model.*;

import java.util.Date;

import rookies.demo.dto.*;
public class TestConfiguration {
    public static final long VALID_ID = 1L;
    public static final long INVALID_ID = -1L;
    public static final String GENERIC_STRING = "test";
    public static final Date TEST_DATE = new Date();

    public static final Roles TEST_ROLE = new Roles(1, RoleName.ADMIN);
    public static final Category TEST_CATEGORY = new Category(1, GENERIC_STRING, GENERIC_STRING);
    public static final Product TEST_PRODUCT = new Product(
        VALID_ID,
        GENERIC_STRING,
        GENERIC_STRING,
        0.1,
        GENERIC_STRING,
        TEST_CATEGORY,
        TEST_DATE,
        TEST_DATE
    );
    public static final Users TEST_USER = new Users(
        VALID_ID,
        TEST_ROLE,
        GENERIC_STRING,
        GENERIC_STRING,
        GENERIC_STRING,
        GENERIC_STRING,
        GENERIC_STRING
    );
    public static final Rating TEST_RATING = new Rating(
        VALID_ID,
        TEST_PRODUCT,
        TEST_USER,
        3,
        GENERIC_STRING
    );

    public static final ProductDto TEST_PRODUCT_DTO = new ProductDto(
        VALID_ID,
        GENERIC_STRING,
        GENERIC_STRING,
        VALID_ID,
        GENERIC_STRING,
        0.1,
        TEST_DATE, 
        TEST_DATE
    );
    public static final CategoryDto TEST_CATEGORY_DTO = new CategoryDto(
        1,
        GENERIC_STRING,
        GENERIC_STRING
    );
    public static final RatingDto TEST_RATING_DTO = new RatingDto(
        VALID_ID,
        3,
        VALID_ID,
        VALID_ID,
        GENERIC_STRING
    );
    public static final RolesDto TEST_ROLE_DTO = new RolesDto(1, "ADMIN");
}
