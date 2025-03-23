package com.cartSmart.product.controller;

import com.cartSmart.product.dto.ProductDTO;
import com.cartSmart.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

}
