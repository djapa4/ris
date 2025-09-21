package com.example.demo.luka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.drzava.DrzavaService;
import com.example.demo.trajekti.TrajektService;

import model.Drzava;
import model.Luka;
import model.Trajekt;

@Controller
public class LukaController {

	@Autowired private LukaService service;
	@Autowired private DrzavaService drzavaService;
	@Autowired private TrajektService trajektService;

	@GetMapping("/luke")
    public String getAllLuke(Model model) {
        List<Luka> listaLuka = service.izlistajSve();
        for(Luka l : listaLuka) {
            String nazivLuke = l.getDrzava().getNaziv();
        }
        model.addAttribute("prikaziListuLuka", listaLuka);
        return "luke";
    }

    @GetMapping("/luke/new")
    public String prikaziNovuFormu(Model model) {
        model.addAttribute("luke", new Luka());
        model.addAttribute("pageTitle", "Dodaj novu luku");
        return "luke_form";
    }

    @PostMapping("/luke/save")
    public String sacuvajLuku(Luka luka, RedirectAttributes ra) {
        service.sacuvaj(luka);
        ra.addFlashAttribute("message", "Luka uspesno dodata");
        return "redirect:/luke";
    }
    
    @GetMapping("/luke/delete/{id}")
    public String obrisiLuku(@PathVariable("id") Integer id, RedirectAttributes ra) {
    	try {
			service.obrisiLuku(id);
			ra.addFlashAttribute("message", "Luka je uspesno obrisana.");
		} catch (LukaNotFound e) {
			// TODO Auto-generated catch block
		} catch (DataIntegrityViolationException e) {
			ra.addFlashAttribute("message", "Nije moguce obrisati luke, jer ima trajekte vezane za luku");
		}
    	return "redirect:/luke";
    }
    

    @ModelAttribute("drzava")
    public String prikaziListuDrzava(Model model) {
        List<Drzava> listaDrzava = drzavaService.izlistajSve();
        model.addAttribute("prikaziListuDrzava", listaDrzava);
        return "luke_form";
    }
    
}
