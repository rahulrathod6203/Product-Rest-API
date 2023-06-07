package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.entity.Product;

public interface ProductService {

	public List<Product> getAll();

	public Optional<Product> getById(int id);

	public Product addproduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProductById(int id);

}
