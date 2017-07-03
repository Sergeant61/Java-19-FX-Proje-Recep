package com.recep.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SatinAlinacakLisans {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String isim;

	private Date alinmasi_planlanan_tarih;

	@OneToOne(cascade = CascadeType.ALL)
	private Satici satici;

	@OneToOne(cascade = CascadeType.ALL)
	private Personel personel;

	@OneToOne(cascade = CascadeType.ALL)
	private Ucret ucret;

	public Ucret getUcret() {
		return ucret;
	}

	public void setUcret(Ucret ucret) {
		this.ucret = ucret;
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

	public Date getAlinmasi_planlanan_tarih() {
		return alinmasi_planlanan_tarih;
	}

	public void setAlinmasi_planlanan_tarih(Date alinmasi_planlanan_tarih) {
		this.alinmasi_planlanan_tarih = alinmasi_planlanan_tarih;
	}

}
