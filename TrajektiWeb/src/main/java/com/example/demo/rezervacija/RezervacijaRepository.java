package com.example.demo.rezervacija;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {

	public Long countById(Integer id);
	
}
