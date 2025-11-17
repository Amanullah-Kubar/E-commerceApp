package com.ecommerceapp.E_commerceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    private String description;
    private String category;
    private String brand;
    private double price;
    private int quantity;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private Date releaseDate;
    private boolean availability;


    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;


}
