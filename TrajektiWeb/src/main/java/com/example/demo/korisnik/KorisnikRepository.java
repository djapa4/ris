package com.example.demo.korisnik;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{

	public Long countById(Integer id);
	Optional<Korisnik> findByGmail(String gmail);
	
}
