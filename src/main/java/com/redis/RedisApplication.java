package com.redis;

import java.util.List;
import java.util.Map;

import com.redis.entity.Product;
import com.redis.repository.ProductDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/rest/api")
public class RedisApplication {
	@Autowired
	private ProductDao dao;
	@PostMapping("/save")
	public Product save(@RequestBody Product product){
		return dao.save(product);
	}
	@GetMapping("/products")
	public Map<String,Product> getAllProducts(){
		return dao.findAll();
	}

	@GetMapping("/products/{id}")
	public Product findProduct(@PathVariable int id){
		return dao.findProductById(id);
	}

	@DeleteMapping("/products/{id}")
	public String removeProduct(@PathVariable int id){
		return dao.deleteProduct(id);
	}
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}
