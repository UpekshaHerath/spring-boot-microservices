package com.cartSmart.product.controller;

import com.cartSmart.product.dto.ProductDTO;
import com.cartSmart.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO changingProduct) {
        return productService.updateProduct(id, changingProduct);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

}
