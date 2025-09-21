package com.example.demo.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.korisnik.KorisnikRepository;

import model.Korisnik;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired KorisnikRepository repo;

	public CustomUserDetailsService(KorisnikRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
		Korisnik korisnik = repo.findByGmail(gmail)
			.orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronadjen"));
		return new org.springframework.security.core.userdetails.User(
				korisnik.getGmail(),
				korisnik.getPassword(),
				List.of(new SimpleGrantedAuthority("ROLE_" + korisnik.getUloga().getNazivUloge()))
				);
	}

}
