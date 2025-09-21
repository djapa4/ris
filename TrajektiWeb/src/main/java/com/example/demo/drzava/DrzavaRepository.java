package com.example.demo.drzava;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Drzava;

public interface DrzavaRepository extends JpaRepository<Drzava, Integer> {

	public Long countById(Integer id);
	
}
