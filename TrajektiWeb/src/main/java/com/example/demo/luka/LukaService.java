package com.example.demo.luka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.drzava.DrzavaRepository;

import model.Luka;

@Service
public class LukaService {

	@Autowired private LukaRepository repo;
	@Autowired private DrzavaRepository drzaveRepo;
	
	public List<Luka> izlistajSve() {
		List<Luka> luke = repo.findAll();
		return (List<Luka>) luke;
	}

	public List<Luka> izlistajSveZaDrzavu(Integer id) {
		List<Luka> listaLuka = repo.findByDrzava_Id(id);
		return listaLuka;
	}
	
	public void sacuvaj(Luka luka) {
		repo.save(luka);
	}
	
	public void obrisiLuku(Integer lukaId) throws LukaNotFound {
		Long count = repo.countById(lukaId);
		if(count == null || count == 0) {
			throw new LukaNotFound("Nije pronadjena niti jedna luka sa id: " + lukaId);
		}
		repo.deleteById(lukaId);
	}
	
}
