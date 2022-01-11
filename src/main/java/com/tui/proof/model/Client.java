package com.tui.proof.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idClient;
  @Size(min=3, max=20, message="First name must have between 3 and 20 characters")
  private String firstName;
  @Size(max=150, message="Last name must have 150 characters maximum")
  private String lastName;
  @Size(min=9, max=13, message="Telephone must have between 9 (653053265) and 13 characters (0034653053265)")
  private String telephone;
}
