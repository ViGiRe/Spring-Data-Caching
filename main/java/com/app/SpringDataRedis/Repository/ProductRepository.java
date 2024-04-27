package com.app.SpringDataRedis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.SpringDataRedis.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
