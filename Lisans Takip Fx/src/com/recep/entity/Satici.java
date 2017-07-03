package com.recep.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Satici {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String isim;

	private String adres;

	public Satici() {
	}

	public Satici(String isim, String adres) {
		super();
		this.isim = isim;
		this.adres = adres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

}
