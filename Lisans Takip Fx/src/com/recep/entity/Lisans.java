package com.recep.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Lisans {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String isim;

	private Date satin_alinma_tarihi;

	private Date gecerlilik_tarihi;

	@OneToOne(cascade = CascadeType.ALL)
	private Satici satici;

	@OneToOne(cascade = CascadeType.ALL)
	private Personel personel;

	@OneToOne(cascade = CascadeType.ALL)
	private Ucret ucret;

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

	public Date getSatin_alinma_tarihi() {
		return satin_alinma_tarihi;
	}

	public void setSatin_alinma_tarihi(Date satin_alinma_tarihi) {
		this.satin_alinma_tarihi = satin_alinma_tarihi;
	}

	public Date getGecerlilik_tarihi() {
		return gecerlilik_tarihi;
	}

	public void setGecerlilik_tarihi(Date gecerlilik_tarihi) {
		this.gecerlilik_tarihi = gecerlilik_tarihi;
	}

	public Satici getSatici() {
		return satici;
	}

	public void setSatici(Satici satici) {
		this.satici = satici;
	}

	public Personel getPersonel() {
		return personel;
	}

	public void setPersonel(Personel personel) {
		this.personel = personel;
	}

	public Ucret getUcret() {
		return ucret;
	}

	public void setUcret(Ucret ucret) {
		this.ucret = ucret;
	}

}
