package org.example.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.models.Category;

@Getter
@Setter
public class ProductDto {
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private Category category;
}
