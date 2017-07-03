package com.recep.io;

public class Config {

	public static String[] configOku(String url){
		String[] txtDizi;
		
		String  NEWURL,NEWUSERNAME,NEWPASSWORD;
		
		Okuyucu okuyucuKonsol = new Okuyucu();
		
		
		String text = okuyucuKonsol.dosyaAcOku(url);
		
		NEWURL = text.substring(0, text.indexOf(" "));
			
		text = text.substring(text.indexOf(" ")+1);
			
		NEWUSERNAME = text.substring(0, text.indexOf(" "));
		
		NEWPASSWORD = text.substring(text.indexOf(" ")+1);
		
		txtDizi = new String[]{NEWURL, NEWUSERNAME, NEWPASSWORD};
		return txtDizi;
	}
	
}
