package com.cts.products.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class Products {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String productid;
	String productName;
	String productCharacteristics;
	String brand;
	String color;
	Integer price;
	String segment;
}
