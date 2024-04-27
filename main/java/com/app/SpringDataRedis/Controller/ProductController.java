package com.app.SpringDataRedis.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.SpringDataRedis.Service.ProductService;
import com.app.SpringDataRedis.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping()
	public Product saveData(@RequestBody Product product) {
		
		return productService.saveData(product);
	}
	
	@GetMapping()
	public List<Product> getAllProducts(){
		return productService.getAllProducts();		
	}
	
	@GetMapping("/{id}")
	@Cacheable(key = "#id",value = "Product",unless = "#result.price > 1000")
	public Product findProductById(@PathVariable("id") Long id) {
		System.out.println("CALLED FROM DB");
		return productService.getProductById(id);	
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(key = "#id",value = "Product") //cache ecict will delete the data from db as well as cache
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return "Data Deleted";
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product product,@PathVariable("id") Long id) {
		
		Product p = productService.updateProduct(product,id);
		return p;
		
	}
}
