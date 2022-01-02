package com.tui.proof.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  private String street;
  private String postcode;
  private String city;
  private String country;
}
