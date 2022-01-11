package com.tui.proof.model;


import java.time.OffsetDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.tui.proof.validator.OneOf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="orders")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String number;
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "id_address")
	@NotNull
	private Address deliveryAddress;
	@OneOf({5,10,15})
	private Integer pilotes;
	private Double orderTotal;
	@CreationTimestamp
	@Column(updatable = false)
	private OffsetDateTime created;
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_client", updatable = false)
	private Client client;

	public Order(Address address, Integer pilotes)
	{
		this.deliveryAddress=address;
		this.pilotes=pilotes;
	}
	
//	@PrePersist
//	protected void onCreate() {
//		creationDate = LocalDateTime.now();
//	}

}
