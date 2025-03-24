package com.cartSmart.product.service;

import com.cartSmart.product.dto.ProductDTO;
import com.cartSmart.product.exception.ProductNotFoundException;
import com.cartSmart.product.model.Product;
import com.cartSmart.product.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

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
        logger.info("Attempting to update product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Product with ID {} not found", id);
                    return new ProductNotFoundException("Product with ID " + id + " not found");
                });

        logger.info("Product with ID {} found. Updating details.", id);

        product.setId(id);
        product.setName(changingProduct.getName());
        product.setDescription(changingProduct.getDescription());
        product.setForSale(changingProduct.isForSale());
        productRepository.save(product);

        logger.info("Product with ID {} successfully updated.", id);

        ProductDTO updateProductDTO = modelMapper.map(product, ProductDTO.class);
        return ResponseEntity.ok(updateProductDTO);
    }

}
