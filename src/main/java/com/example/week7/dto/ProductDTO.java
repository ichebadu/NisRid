package com.example.week7.dto;

import com.example.week7.model.Category;
import jakarta.persistence.Entity;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class ProductDTO {

    private Long id;
    private Category category;
    private Long categoryId;
    private String name;
    private String description;
    private String price;
    private String quantity;
    private String imageName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
