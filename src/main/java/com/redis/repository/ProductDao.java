package com.redis.repository;

import java.util.List;
import java.util.Map;

import com.redis.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {


    @Autowired
    private RedisTemplate<String, Product> template;


    private HashOperations hashOperations;

    public ProductDao(RedisTemplate<String, Product> template) {
        this.template= template;
        this.hashOperations=template.opsForHash();
    }

    public Product save(Product product){
        hashOperations.put("PRODUCT", product.getId(),product);
        return product;
    }
    public Map<String,Product> findAll(){
        return hashOperations.entries("PRODUCT");
    }
    public Product findProductById(int id){
        return (Product) hashOperations.get("PRODUCT",id);
    }
    public String deleteProduct(int id){
        hashOperations.delete("PRODUCT",id);
        return "Product removed: "+id; 
    }
    
}
