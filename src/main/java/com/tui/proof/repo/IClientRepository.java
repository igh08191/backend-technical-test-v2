package com.tui.proof.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tui.proof.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {
	List<Client> findByFirstNameContaining(final String firstName);
}
