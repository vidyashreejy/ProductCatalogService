package org.example.controllers;
import org.example.dtos.FakeStoreProductDto;
import org.example.dtos.ProductDto;
import org.example.models.Category;
import org.example.models.Product;
import org.example.services.FakeStoreProductService;
import org.example.services.IFakeStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IFakeStoreProductService iFakeStoreProductService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> results = new ArrayList<>();
        List<Product> products = iFakeStoreProductService.getAllProducts();
        for(Product product : products) {
            results.add(getProductDto(product));
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
    //public Product getProduct(@PathVariable("id") Long productId){
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid Product Id");
            }
            Product product = iFakeStoreProductService.getProductById(id);
            ProductDto body = getProductDto(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Called by", "Vidya");
            return new ResponseEntity<>(body, headers, HttpStatus.OK);
       } catch(IllegalArgumentException e){
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
        Product newProduct = iFakeStoreProductService.replaceProduct(id, product);
        return ResponseEntity.ok(getProductDto(newProduct));
    }

    private static ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        //product.setId(productId);
        productDto.setName("Iphone");
        productDto.setDescription("fastest iphone ever");
        productDto.setPrice(product.getPrice());
        productDto.setCategory(productDto.getCategory());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        Category category = new Category();
        category.setName(productDto.getCategory().getName());
        product.setCategory(category);
        return product;
    }

    @PostMapping
    public ProductDto createProduct(ProductDto productDto){
        return null;
    }
}
