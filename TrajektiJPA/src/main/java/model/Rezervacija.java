package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * The persistent class for the rezervacija database table.
 * 
 */
@Entity
@NamedQuery(name="Rezervacija.findAll", query="SELECT r FROM Rezervacija r")
public class Rezervacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rezervacija_id")
	private int id;

	@Column(name="broj_karata")
	private int brojKarata;

	@Column(name="cena_ukupna")
	private BigDecimal cenaUkupna;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_rezervacije")
	private LocalDateTime datumRezervacije;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Trajekt
	@ManyToOne
	private Trajekt trajekt;

	//bi-directional many-to-one association to Vozilo
	@ManyToOne
	private Vozilo vozilo;

	public Rezervacija() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBrojKarata() {
		return this.brojKarata;
	}

	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}

	public BigDecimal getCenaUkupna() {
		return this.cenaUkupna;
	}

	public void setCenaUkupna(BigDecimal cenaUkupna) {
		this.cenaUkupna = cenaUkupna;
	}

	public LocalDateTime getDatumRezervacije() {
		return this.datumRezervacije;
	}

	public void setDatumRezervacije(LocalDateTime datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Trajekt getTrajekt() {
		return this.trajekt;
	}

	public void setTrajekt(Trajekt trajekt) {
		this.trajekt = trajekt;
	}

	public Vozilo getVozilo() {
		return this.vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public void izracunajCenu() {
	    if (trajekt == null || vozilo == null) {
	        this.cenaUkupna = BigDecimal.ZERO;
	        return;
	    }

	    BigDecimal osnovnaCena = trajekt.getCena();
	    String tipVozila = vozilo.getTip();

	    switch (tipVozila) {
	        case "Kamion":
	            this.cenaUkupna = osnovnaCena.multiply(BigDecimal.valueOf(1.2));
	            break;
	        case "Autobus":
	            this.cenaUkupna = osnovnaCena.multiply(BigDecimal.valueOf(1.5));
	            break;
	        default:
	            this.cenaUkupna = osnovnaCena;
	            break;
	    }
	}

	
}