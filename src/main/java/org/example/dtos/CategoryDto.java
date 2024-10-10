package org.example.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.models.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private String name;

    private String description;

   // private List<Product> productList = new ArrayList<>();
}
