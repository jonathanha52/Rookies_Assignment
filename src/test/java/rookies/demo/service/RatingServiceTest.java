package rookies.demo.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import rookies.demo.dto.RatingDto;
import rookies.demo.model.Product;
import rookies.demo.model.Rating;
import rookies.demo.model.Users;
import rookies.demo.repository.ProductRepository;
import rookies.demo.repository.RatingRepository;
import rookies.demo.repository.UsersRepository;
import rookies.demo.service.impl.RatingService;

import static rookies.demo.config.TestConfiguration.*;

@SpringBootTest
public class RatingServiceTest {
    private final long VALID_ID = 1L;
    private final Product TEST_PRODUCT = new Product();
    private final Users TEST_USERS = new Users();
    private final Rating RATING = new Rating(
        VALID_ID,
        TEST_PRODUCT,
        TEST_USERS,
        3,
        "Test rating"
    );
    private final RatingDto RATING_DTO = new RatingDto(
        VALID_ID,
        3,
        VALID_ID,
        VALID_ID,
        "Test rating"
    );
    public final List<Rating> TEST_RATING_LIST = new ArrayList<>(Arrays.asList(RATING));
    @Mock
    RatingRepository ratingRepository;
    @Mock
    UsersRepository usersRepository;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    RatingService ratingService;

    @Test
    public void testFindRatingOfProduct(){
        when(ratingRepository.findByProduct(TEST_PRODUCT)).thenReturn(TEST_RATING_LIST);
        when(productRepository.findById(1L)).thenReturn(Optional.of(TEST_PRODUCT));
        List<Rating> test = ratingService.findRatingOfProduct(1L);
        assertEquals(TEST_RATING_LIST.size(), test.size());
    }
    @Test
    public void testDtoToEntity(){
        RatingDto ratingDto = ratingService.EntityToDto(TEST_RATING);
        assertEquals(ratingDto.getId(), TEST_CATEGORY_DTO.getId());
    }

    @Test
    public void testEntityToDto(){
        Rating rating = ratingService.DtoToEntity(TEST_RATING_DTO);
        assertEquals(rating.getId(), TEST_RATING.getId());
    }

}
