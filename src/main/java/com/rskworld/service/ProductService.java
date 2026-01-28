package com.rskworld.service;

/**
 * Product Service
 * 
 * Enterprise-grade REST API with Spring Boot framework
 * Features JWT authentication, database integration, caching, and comprehensive API documentation
 * 
 * @author RSK World
 * @author Molla Samser (Founder)
 * @author Rima Khatun (Designer & Tester)
 * @website https://rskworld.in
 * @contact help@rskworld.in, support@rskworld.in
 * @phone +91 93305 39277
 * @address Nutanhat, Mongolkote, Purba Burdwan, West Bengal, India, 713147
 * @year 2026
 * 
 * This project is part of RSK World's free programming resources and source code collection.
 * Content used for educational purposes only. View Disclaimer: https://rskworld.in/disclaimer.php
 */

import com.rskworld.entity.Product;
import com.rskworld.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products", key = "#id")
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Cacheable(value = "products", key = "#sku")
    public Optional<Product> getProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    @Cacheable(value = "products", key = "'all'")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "'active'")
    public List<Product> getActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    @Cacheable(value = "products", key = "'active_page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<Product> getActiveProductsPage(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable);
    }

    @Cacheable(value = "products", key = "'category_' + #category")
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Cacheable(value = "products", key = "'brand_' + #brand")
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Cacheable(value = "products", key = "'search_' + #keyword")
    public List<Product> searchProducts(String keyword) {
        return productRepository.findActiveProductsByKeyword(keyword);
    }

    @Cacheable(value = "products", key = "'price_range_' + #minPrice + '_' + #maxPrice")
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findActiveProductsByPriceRange(minPrice, maxPrice);
    }

    @Cacheable(value = "products", key = "'low_stock_' + #threshold")
    public List<Product> getLowStockProducts(Integer threshold) {
        return productRepository.findLowStockProducts(threshold);
    }

    @Cacheable(value = "products", key = "'latest'")
    public List<Product> getLatestProducts() {
        return productRepository.findLatestActiveProducts();
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product createProduct(Product product) {
        if (productRepository.existsBySku(product.getSku())) {
            throw new RuntimeException("Product with SKU " + product.getSku() + " already exists!");
        }
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        if (!product.getSku().equals(productDetails.getSku()) && 
            productRepository.existsBySku(productDetails.getSku())) {
            throw new RuntimeException("Product with SKU " + productDetails.getSku() + " already exists!");
        }

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setSku(productDetails.getSku());
        product.setCategory(productDetails.getCategory());
        product.setBrand(productDetails.getBrand());
        product.setImageUrl(productDetails.getImageUrl());
        product.setActive(productDetails.getActive());

        return productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setActive(false);
        productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void permanentlyDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        productRepository.delete(product);
    }

    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
}
