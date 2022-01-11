package com.tui.proof.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long idAddress;
  @Size(max=150, message="Address must have 150 characters maximum")
  @NotNull
  private String street;
  @Size(max=5, message="Address must have 5 characters maximum")
  private String postcode;
  private String city;
  private String country;
  
  public Address(String street, String postcode, String city, String country){
	  this.street=street;
	  this.postcode=postcode;
	  this.city=city;
	  this.country=country;
  }
}
