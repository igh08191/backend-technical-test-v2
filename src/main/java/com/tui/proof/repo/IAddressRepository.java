	package com.tui.proof.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tui.proof.model.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer> {

}
