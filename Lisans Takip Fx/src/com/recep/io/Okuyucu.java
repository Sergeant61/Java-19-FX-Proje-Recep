package com.recep.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Okuyucu {

	/**
	 * 
	 * Bu fonksiyon bir text dosyasýndan okuma iþlemi yapýyor.
	 * 
	 * @param okunacakDosyaYolu
	 */
	public String dosyaAcOku(String okunacakDosyaYolu) {

		File file = new File(okunacakDosyaYolu);
		String text = "";
		Scanner oku = null;

		try {
			oku = new Scanner(file);

			while (oku.hasNext()) {
				text = text + oku.nextLine();
				// System.out.println(text);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		oku.close();
		return text;
	}

}