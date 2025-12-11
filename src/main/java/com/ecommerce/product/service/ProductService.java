package com.ecommerce.product.service;

import com.ecommerce.product.dto.InventoryRequest;
import com.ecommerce.product.dto.InventoryResponse;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RestClient restClient;

    //synchronous methods
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        mapRequestToEntity(request, product);
        restClient.post()
                .uri("/inventory/update")
                .body(new InventoryRequest(product.getId(), product.getStockQuantity()))
                .retrieve()
                .body(InventoryResponse.class);
        return mapEntityToResponse(productRepository.save(product));
    }

        public ProductResponse createProductWithFeign(ProductRequest request) {
        Product product = new Product();
        mapRequestToEntity(request, product);
        restClient.post()
                .uri("/inventory/update")
                .body(new InventoryRequest(product.getId(), product.getStockQuantity()))
                .retrieve()
                .body(InventoryResponse.class);
        return mapEntityToResponse(productRepository.save(product));
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        mapRequestToEntity(request, product);
        return mapEntityToResponse(productRepository.save(product));
    }

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
            .map(this::mapEntityToResponse)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
            .map(this::mapEntityToResponse)
            .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
            .map(this::mapEntityToResponse)
            .collect(Collectors.toList());
    }

    private void mapRequestToEntity(ProductRequest request, Product product) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setCategoryId(request.getCategoryId());
    }

    private ProductResponse mapEntityToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setImageUrl(product.getImageUrl());
        response.setCategoryId(product.getCategoryId());
        return response;
    }
}