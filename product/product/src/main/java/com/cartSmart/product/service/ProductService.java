package com.cartSmart.product.service;

import com.cartSmart.product.dto.ProductDTO;
import com.cartSmart.product.model.Product;
import com.cartSmart.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ProductDTO updateProduct(String id, ProductDTO changingProduct) {
        Product product = productRepository.findById(Integer.parseInt(id)).orElse(null);
        if (product != null) {
            product.setId(Integer.parseInt(id));
            product.setName(changingProduct.getName());
            product.setDescription(changingProduct.getDescription());
            product.setForSale(changingProduct.isForSale());
            productRepository.save(product);
        }
        return modelMapper.map(product, ProductDTO.class);
    }

}
