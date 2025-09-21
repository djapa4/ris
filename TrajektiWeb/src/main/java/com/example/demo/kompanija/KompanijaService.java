package com.example.demo.kompanija;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Kompanija;

@Service
public class KompanijaService {

	@Autowired KompanijeRepository repo;
	
	public List<Kompanija> izlistajSve() {
		return repo.findAll();
	}
	
	public void sacuvaj(Kompanija kompanija) {
		repo.save(kompanija);
	}
	
	public Kompanija get(Integer id) throws CompanyNotFound {
		Optional<Kompanija> kompanija = repo.findById(id);
		if(kompanija.isPresent()) {
			return kompanija.get();
		}
		throw new CompanyNotFound("Ne postoji kompanija sa id: " + id);
	}
	
	public void obrisiKompaniju(Integer id) throws CompanyNotFound {
		Long count = repo.countById(id);
		if(count == 0 || count == null) {
			throw new CompanyNotFound("Nije pronadjena niti jedna kompanija sa id: " + id);
		}
		repo.deleteById(id);
	}
	
}
