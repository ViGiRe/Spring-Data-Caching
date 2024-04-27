package com.app.SpringDataRedis.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.SpringDataRedis.Repository.ProductRepository;
import com.app.SpringDataRedis.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public Product saveData(Product product) {
		return productRepository.save(product);
	}


	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}


	public Product getProductById(Long id) {
		
		return productRepository.findById(id).get();
	}


	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
		
	}


	public Product updateProduct(Product product, Long id) {
		Product p = productRepository.findById(id).get();
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		return productRepository.save(p);
		
	}


}
