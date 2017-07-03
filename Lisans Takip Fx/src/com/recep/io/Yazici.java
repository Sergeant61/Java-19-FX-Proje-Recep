package com.recep.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Yazici {

	private PrintWriter pw;

	public void dosyaAcYaz(String dosyaYolu, String yazilacakText) {
		try {
			this.pw = new PrintWriter(dosyaYolu);
			this.pw.println(yazilacakText);
			this.pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
