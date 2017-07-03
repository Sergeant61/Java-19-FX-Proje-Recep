package com.recep.converter;

import java.util.Date;

import com.recep.entity.Personel;
import com.recep.entity.Satici;
import com.recep.entity.Ucret;

public class LisansConverter {

	private int sira_no;

	private String isim;

	private Date satin_alinma_tarihi;

	private Date gecerlilik_tarihi;

	private Date alinmasi_planlanan_tarih;

	private String satici_name;

	private String satici_adres;

	private String personel_name;

	private int ucret;

	private String ucret_type;

	private Satici satici;

	private Personel personel;

	private Ucret ucrett;

	public LisansConverter() {
	}

	public LisansConverter(int sira_no, String isim, Date satin_alinma_tarihi, Date gecerlilik_tarihi,
			Date alinmasi_planlanan_tarih, String satici_name, String satici_adres, String personel_name, int ucret,
			String ucret_type, Satici satici, Personel personel, Ucret ucrett) {
		super();
		this.sira_no = sira_no;
		this.isim = isim;
		this.satin_alinma_tarihi = satin_alinma_tarihi;
		this.gecerlilik_tarihi = gecerlilik_tarihi;
		this.alinmasi_planlanan_tarih = alinmasi_planlanan_tarih;
		this.satici_name = satici_name;
		this.satici_adres = satici_adres;
		this.personel_name = personel_name;
		this.ucret = ucret;
		this.ucret_type = ucret_type;
		this.satici = satici;
		this.personel = personel;
		this.ucrett = ucrett;
	}

	public int getSira_no() {
		return sira_no;
	}

	public void setSira_no(int sira_no) {
		this.sira_no = sira_no;
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

	public Date getAlinmasi_planlanan_tarih() {
		return alinmasi_planlanan_tarih;
	}

	public void setAlinmasi_planlanan_tarih(Date alinmasi_planlanan_tarih) {
		this.alinmasi_planlanan_tarih = alinmasi_planlanan_tarih;
	}

	public String getSatici_name() {
		return satici_name;
	}

	public void setSatici_name(String satici_name) {
		this.satici_name = satici_name;
	}

	public String getSatici_adres() {
		return satici_adres;
	}

	public void setSatici_adres(String satici_adres) {
		this.satici_adres = satici_adres;
	}

	public String getPersonel_name() {
		return personel_name;
	}

	public void setPersonel_name(String personel_name) {
		this.personel_name = personel_name;
	}

	public int getUcret() {
		return ucret;
	}

	public void setUcret(int ucret) {
		this.ucret = ucret;
	}

	public String getUcret_type() {
		return ucret_type;
	}

	public void setUcret_type(String ucret_type) {
		this.ucret_type = ucret_type;
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

	public Ucret getUcrett() {
		return ucrett;
	}

	public void setUcrett(Ucret ucrett) {
		this.ucrett = ucrett;
	}

}
