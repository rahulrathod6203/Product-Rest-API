package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Product;
import com.cg.exception.ProductNotFoundException;
import com.cg.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductServiceImpl service;

	@GetMapping("/product/all")
	public List<Product> getAllProducts() {
		return service.getAll();
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {

		Optional<Product> optional = service.getById(id);

		if (optional.isPresent()) {
			return new ResponseEntity<>(optional, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product Not Found");
		}

	}

	@PostMapping("/product/add")
	public ResponseEntity<Product> add(@RequestBody Product product) {
		return new ResponseEntity<>(service.addproduct(product), HttpStatus.CREATED);
	}

	// Update Product...

	@PutMapping("/product/update/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable("id") int id) {
		Optional<Product> optional = service.getById(id);
		if (optional.isPresent()) {

			Product updateProduct = optional.get();
			updateProduct.setName(product.getName());
			updateProduct.setDescription(product.getDescription());
			updateProduct.setPrice(product.getPrice());
			updateProduct.setStarRating(product.getStarRating());
			service.updateProduct(updateProduct);
			return new ResponseEntity<Product>(updateProduct, HttpStatus.ACCEPTED);
		} else {
			throw new ProductNotFoundException("Product Not Found");

		}
	}

	@DeleteMapping("/product/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		Optional<Product> optional = service.getById(id);
		if (optional.isPresent()) {
			service.deleteProductById(id);
			// System.out.println("Product Deleted..");
		} else {
			throw new ProductNotFoundException("Product Not Found");
		}
	}

}
