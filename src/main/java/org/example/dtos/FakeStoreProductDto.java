package org.example.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.models.Category;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Category category;
    private int price;
    private FakeStoreRatingDto fakeStoreRatingDto;
}
