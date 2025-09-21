package com.example.demo.korisnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Korisnik;

@Controller
public class KorisnikController {

	@Autowired
	private KorisnikService service;

	@GetMapping("/users")
	public String prikaziListuKorisnika(Model model) {
		List<Korisnik> listaKorisnika = service.izlistajSve();
		model.addAttribute("prikaziListuKorisnika", listaKorisnika);
		return "users";
	}

	@GetMapping("/users/new")
	public String prikaziNovuFormu(Model model) {
		model.addAttribute("korisnik", new Korisnik());
		model.addAttribute("pageTitle", "Dodavanje novog korisnika");
		return "user_form";
	}

	@PostMapping("/users/save")
	public String sacuvajKorisnika(Korisnik korisnik, RedirectAttributes ra) {
		service.sacuvaj(korisnik);
		ra.addFlashAttribute("message", "Korisnik uspesno dodat");
		return "redirect:/users";
	}


	@GetMapping("/users/delete/{id}")
	public String obrisiKorisnika(@PathVariable("id") Integer id, RedirectAttributes ra) {
		try {
			service.obrisiKorisnika(id);
			ra.addFlashAttribute("message", "Korisnik je uspešno obrisan");
		} catch (UserNotFound e) {
			System.out.println("Korisnik nije pronadjen.");
		} catch (DataIntegrityViolationException e) {
			ra.addFlashAttribute("message", "Nije moguce obrisati korisnika jer postoje rezervacije vezane za njega");
		}
		return "redirect:/users";
	}

}
