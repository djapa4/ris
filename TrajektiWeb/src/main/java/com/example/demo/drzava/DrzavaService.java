package com.example.demo.drzava;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Drzava;

@Service
public class DrzavaService {

	@Autowired
	DrzavaRepository repo;
	
	public List<Drzava> izlistajSve() {
		return repo.findAll();
	}
	
	public void sacuvaj(Drzava drzava) {
		repo.save(drzava);
	}
	
	public Drzava get(Integer drzava_id) throws CountryNotFound {
		Optional<Drzava> drzava = repo.findById(drzava_id);
		if(drzava.isPresent()) {
			return drzava.get();
		}
		throw new CountryNotFound("Ne postoji drzava sa id: " + drzava_id);
	}
	
	public void obrisiDrzavu(Integer drzava_id) throws CountryNotFound {
		Long count = repo.countById(drzava_id);
		if(count == null || count == 0) {
			throw new CountryNotFound("Nije pronadjena niti jedna drzava sa id: " + drzava_id);
		}
		repo.deleteById(drzava_id);
	}
	
}
