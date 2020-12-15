package com.cts.products.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
public class ProductsBO {
	String productid;
	String productName;
	String productCharacteristics;
	String brand;
	String color;
	Integer price;
	String segment;
}
