package com.recep.io;

public class Islem {

	public static void main(String[] args) {

		String url = "d:/recep.txt";

		Yazici yazici = new Yazici();

		yazici.dosyaAcYaz(url, "Naber \n recep");

		Okuyucu okuyucu = new Okuyucu();

		System.out.println(okuyucu.dosyaAcOku(url));

	}

}
