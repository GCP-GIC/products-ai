package com.cts.products.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.products.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
	@Query("SELECT g FROM Products g where g.brand = :brand")
	public List<Products> findBybrand(@Param("brand") String brand);
}