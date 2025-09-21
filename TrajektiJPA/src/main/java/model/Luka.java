package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the luka database table.
 * 
 */
@Entity
@NamedQuery(name="Luka.findAll", query="SELECT l FROM Luka l")
public class Luka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="luka_id")
	private int id;

	@Column(name="naziv_luke")
	private String nazivLuke;

	//bi-directional many-to-one association to Drzava
	@ManyToOne
	private Drzava drzava;

	//bi-directional many-to-one association to Trajekt
	@OneToMany(mappedBy="luka1")
	private List<Trajekt> trajekts1;

	//bi-directional many-to-one association to Trajekt
	@OneToMany(mappedBy="luka2")
	private List<Trajekt> trajekts2;

	public Luka() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazivLuke() {
		return this.nazivLuke;
	}

	public void setNazivLuke(String nazivLuke) {
		this.nazivLuke = nazivLuke;
	}

	public Drzava getDrzava() {
		return this.drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public List<Trajekt> getTrajekts1() {
		return this.trajekts1;
	}

	public void setTrajekts1(List<Trajekt> trajekts1) {
		this.trajekts1 = trajekts1;
	}

	public Trajekt addTrajekts1(Trajekt trajekts1) {
		getTrajekts1().add(trajekts1);
		trajekts1.setLuka1(this);

		return trajekts1;
	}

	public Trajekt removeTrajekts1(Trajekt trajekts1) {
		getTrajekts1().remove(trajekts1);
		trajekts1.setLuka1(null);

		return trajekts1;
	}

	public List<Trajekt> getTrajekts2() {
		return this.trajekts2;
	}

	public void setTrajekts2(List<Trajekt> trajekts2) {
		this.trajekts2 = trajekts2;
	}

	public Trajekt addTrajekts2(Trajekt trajekts2) {
		getTrajekts2().add(trajekts2);
		trajekts2.setLuka2(this);

		return trajekts2;
	}

	public Trajekt removeTrajekts2(Trajekt trajekts2) {
		getTrajekts2().remove(trajekts2);
		trajekts2.setLuka2(null);

		return trajekts2;
	}

}