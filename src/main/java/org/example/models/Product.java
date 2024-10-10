package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private Category category;
    private boolean isPrime;
}
