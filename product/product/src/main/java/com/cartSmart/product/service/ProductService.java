package com.cartSmart.product.service;

import com.cartSmart.product.dto.ProductDTO;
import com.cartSmart.product.exception.ProductNotFoundException;
import com.cartSmart.product.model.Product;
import com.cartSmart.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
        return productDTO;
    }

    public ResponseEntity<ProductDTO> updateProduct(Integer id, ProductDTO changingProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    return new ProductNotFoundException("Product with ID " + id + " not found");
                });

        product.setId(id);
        product.setName(changingProduct.getName());
        product.setDescription(changingProduct.getDescription());
        product.setForSale(changingProduct.isForSale());
        productRepository.save(product);

        ProductDTO updateProductDTO = modelMapper.map(product, ProductDTO.class);
        return ResponseEntity.ok(updateProductDTO);
    }

    public ResponseEntity<String> deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    return new ProductNotFoundException("Product with ID " + id + " not found");
                });

        productRepository.delete(product);

        return ResponseEntity.ok("Product with ID " + id + " successfully deleted.");
    }

}
