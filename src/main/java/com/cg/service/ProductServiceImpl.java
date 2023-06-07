package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Product;
import com.cg.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepo repo;

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<Product> getById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
		}

	@Override
	public Product addproduct(Product product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	@Override
	public void deleteProductById(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
