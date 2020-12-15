package com.cts.products.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.products.client.RecommendationsClient;
import com.cts.products.entity.Products;
import com.cts.products.model.Brand;
import com.cts.products.model.ProductsBO;
import com.cts.products.repo.ProductsRepository;

@RequestMapping("/v1")
@RestController
public class ProductsController {

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	private RecommendationsClient recommendationsClient;

	@GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public String sayHello() {
		return "Hello world!";
	};

	@PostMapping(path = "/saveProducts", consumes = "application/json")
	public String saveProducts(@RequestBody List<ProductsBO> productsBO) {
		List<Products> products = productsBO.stream().map(productBo -> {
			Products product = new Products();
			product.setBrand(productBo.getBrand());
			product.setColor(productBo.getColor());
			product.setPrice(productBo.getPrice());
			product.setProductCharacteristics(productBo.getProductCharacteristics());
			product.setProductid(productBo.getProductid());
			product.setProductName(productBo.getProductName());
			product.setSegment(productBo.getSegment());
			return product;
		}).collect(Collectors.toList());
		productsRepository.saveAll(products);
		return "Products stored in products ai!";
	}

	@GetMapping(value = "/retreiveByBrand", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsBO> retreiveByBrand(@RequestParam String brand) throws Exception {
		List<Products> products = productsRepository.findBybrand(brand);
		if (products == null)
			throw new Exception("Products not found for brand");
		List<ProductsBO> productsBos = products.stream().map(product -> {
			ProductsBO productBo = new ProductsBO();
			productBo.setBrand(product.getBrand());
			productBo.setColor(product.getColor());
			productBo.setPrice(product.getPrice());
			productBo.setProductCharacteristics(product.getProductCharacteristics());
			productBo.setProductid(product.getProductid());
			productBo.setProductName(product.getProductName());
			productBo.setSegment(product.getSegment());
			return productBo;
		}).collect(Collectors.toList());
		return productsBos;
	}

	@PostMapping(path = "/sendtorecommendationsai", consumes = "application/json")
	public ResponseEntity<String> publishProductsToRecommendations(@RequestBody Brand brand) throws Exception {
		List<Products> products = productsRepository.findBybrand(brand.getBrandName());
		if (products == null)
			throw new Exception("Products not found for the brand: " + brand.getBrandName());
		List<ProductsBO> productsBos = products.stream().map(product -> {
			ProductsBO productBo = new ProductsBO();
			productBo.setBrand(product.getBrand());
			productBo.setColor(product.getColor());
			productBo.setPrice(product.getPrice());
			productBo.setProductCharacteristics(product.getProductCharacteristics());
			productBo.setProductid(product.getProductid());
			productBo.setProductName(product.getProductName());
			productBo.setSegment(product.getSegment());
			return productBo;
		}).collect(Collectors.toList());
		return recommendationsClient.pushProductsToRecommendationsAI(productsBos);
	}
}
