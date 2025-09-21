package com.example.demo.kompanija;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Kompanija;

public interface KompanijeRepository extends JpaRepository<Kompanija, Integer>{

	public Long countById(Integer id);
	
}
