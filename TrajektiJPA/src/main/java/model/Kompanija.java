package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the kompanija database table.
 * 
 */
@Entity
@NamedQuery(name="Kompanija.findAll", query="SELECT k FROM Kompanija k")
public class Kompanija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="kompanija_id")
	private int id;

	@Column(name="naziv_kompanije")
	private String nazivKompanije;

	private String opis;

	//bi-directional many-to-one association to Trajekt
	@OneToMany(mappedBy="kompanija")
	private List<Trajekt> trajekts;

	public Kompanija() {
	}

	public int getId() {
		return this.id;
	}

	public void setKompanijaId(int id) {
		this.id = id;
	}

	public String getNazivKompanije() {
		return this.nazivKompanije;
	}

	public void setNazivKompanije(String nazivKompanije) {
		this.nazivKompanije = nazivKompanije;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Trajekt> getTrajekts() {
		return this.trajekts;
	}

	public void setTrajekts(List<Trajekt> trajekts) {
		this.trajekts = trajekts;
	}

	public Trajekt addTrajekt(Trajekt trajekt) {
		getTrajekts().add(trajekt);
		trajekt.setKompanija(this);

		return trajekt;
	}

	public Trajekt removeTrajekt(Trajekt trajekt) {
		getTrajekts().remove(trajekt);
		trajekt.setKompanija(null);

		return trajekt;
	}

}