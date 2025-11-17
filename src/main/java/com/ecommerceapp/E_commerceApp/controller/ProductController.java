package com.ecommerceapp.E_commerceApp.controller;

import com.ecommerceapp.E_commerceApp.model.Product;
import com.ecommerceapp.E_commerceApp.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "/api")
public class ProductController {

    @Autowired
    private ProductServices services;

    @GetMapping("/")
    public String greeting() {
        return "Prooduct List";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(services.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = services.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Product product1 = services.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int productId) {
        Product product = services.getProductById(productId);
        byte[] image = product.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(image);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        try {
            services.deleteProductById(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable int productId,
                                           @RequestPart("product") Product product,
                                           @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            services.updateProduct(productId, imageFile, product);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductsByName(@RequestParam String query) {
        System.out.println("SEARCH ENDPOINT HIT | Query: " + query);
        List<Product> products = services.searchProductByQuery(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}


