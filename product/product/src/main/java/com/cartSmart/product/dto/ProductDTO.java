package com.cartSmart.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private boolean forSale;
}
