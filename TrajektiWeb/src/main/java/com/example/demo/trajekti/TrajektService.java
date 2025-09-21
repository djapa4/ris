package com.example.demo.trajekti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Trajekt;

@Service
public class TrajektService {

	@Autowired TrajektRepository repo;
	
	public List<Trajekt> izlistajSve() {
		return (List<Trajekt>) repo.findAll();
	}
	
	public void sacuvaj(Trajekt trajekt) {
		repo.save(trajekt);
	}
	
	public void obrisiTrajekt(Integer id) throws TrajektNotFound {
		Long count = repo.countById(id);
		if(count == null || count == 0) {
			throw new TrajektNotFound("Nije pronadjen niti jedan trajekt sa id: " + id);
		}
		repo.deleteById(id);
	}
	
	public List<Trajekt> izlistajSveTrajekteZaLuku(Integer id) {
		List<Trajekt> listaTrajekta = repo.findByLuka1_id(id);
		return listaTrajekta;
	}
	
}
