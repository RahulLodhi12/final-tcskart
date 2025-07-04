package com.tcs.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.product.entity.Product;
import com.tcs.product.entity.ProductImage;
import com.tcs.product.exception.ImageFormatException;
import com.tcs.product.security.JwtUtil;
import com.tcs.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


//@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
//	@Autowired
//	JwtUtil jwtUtil;
	
	@Operation(summary = "Get All Products")
	@GetMapping("/products") 
	public Page<Product> getAllProducts(@RequestParam Integer page, @RequestParam Integer size){
		return productService.getAllProducts(page,size);
	}
	
	@Operation(summary = "Get Product by Name")
	@GetMapping("/products/{name}") 
	public List<Product> getAllProductByName(@PathVariable String name){
		return productService.getAllProductsByName(name);
	}
	
	@Operation(summary = "Get All Products by Category")
	@GetMapping("/products/category/{category}") 
	public List<Product> getProductByCategories(@PathVariable String category) {
		return productService.getProductByCategories(category);
	}
	
	@Operation(summary = "Get Product By Id")
	@GetMapping("/products/id/{id}")
	public Optional<Product> getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	
	@Operation(summary = "Get Product Image By Id")
	@GetMapping("/products/productImage/{id}")
	public List<ProductImage> getProductImageById(@PathVariable Long id){
		return productService.getProductImageById(id);
	}
	
	@Operation(summary = "Add Product")
	@PostMapping("/admin/addProduct")
	public Product addNewProducts(@RequestBody Product product) {
		return productService.addNewProduct(product);
	}
	
	@Operation(summary = "Delete Product By Id")
	@DeleteMapping("/admin/products/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
	
	@Operation(summary = "Update Product By Id")
	@PutMapping("/admin/products/{id}")
	public String updateProduct(@PathVariable Long id, @RequestBody Product product, @RequestParam String imageUrl, @RequestParam Integer imgId) throws ImageFormatException {
		return productService.updateProduct(id, product, imageUrl, imgId);
	}
	
	@Operation(summary = "Upload Product Image By Id")
	@PostMapping("/admin/products/{id}/image")
	public void uploadProductImages(@PathVariable Long id, @RequestParam String url) throws ImageFormatException {
		productService.uploadProductImages(id,url);
	}
	
//	@GetMapping("/myproduct")
//    public ResponseEntity<?> getMyCart(@RequestHeader("Authorization") String authHeader) {
//
//        String token = authHeader.substring(7); 
//        String email = jwtUtil.extractEmail(token);
//
//        return ResponseEntity.ok("Fetching cart for: " + email);
//    }
	
	
}
