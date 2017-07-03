package com.recep.sabitler;

import com.recep.converter.KullaniciConverter;
import com.recep.converter.LisansConverter;
import com.recep.entity.Kullanici;

public class Sabitler {

	private static KullaniciConverter guncellenecekKullanici;

	private static LisansConverter guncellenecekLisans;

	private static Kullanici kullanici;

	public static Kullanici getKullanici() {
		return kullanici;
	}

	public static void setKullanici(Kullanici kullanici) {
		Sabitler.kullanici = kullanici;
	}

	public static LisansConverter getGuncellenecekLisans() {
		return guncellenecekLisans;
	}

	public static void setGuncellenecekLisans(LisansConverter guncellenecekLisans) {
		Sabitler.guncellenecekLisans = guncellenecekLisans;
	}

	public static KullaniciConverter getGuncellenecekKullanici() {
		return guncellenecekKullanici;
	}

	public static void setGuncellenecekKullanici(KullaniciConverter guncellenecekKullanici) {
		Sabitler.guncellenecekKullanici = guncellenecekKullanici;
	}

}
