package com.tui.proof.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection="orders")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Transient
    public static final String SEQUENCE_NAME = "orders_sequence";
	@Id
	private String number="";
	private Address deliveryAddress;
	private Integer pilotes;
	private Double orderTotal;
	
	public Order(Address address, Integer pilotes)
	{
		this.deliveryAddress=address;
		this.pilotes=pilotes;
	}

}
