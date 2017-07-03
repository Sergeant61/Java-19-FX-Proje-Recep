package com.recep.rol;

public enum RolEnum {

	KULLANICI_KAYIT("Kullanýcý Kayýt (True)","Kullanýcý Kayýt (False)"),
	KULLANICI_DUZENLEME("Kullanýcý Düzenleme (True)","Kullanýcý Düzenleme (False)"),
	LISANS_KAYIT("Lisans Kayýt (True)","Lisans Kayýt (False)"),
	LISANS_DUZENLEME("Lisans Düzenleme (True)","Lisans Düzenleme (False)"),
	ADMIN("Admin (True)","Admin (False)");

	private String rolTrue;
	private String rolFalse;

	RolEnum(String rolTrue,String rolFalse) {
		this.rolTrue = rolTrue;
		this.rolFalse = rolFalse;
	}

	public String getRolTrue() {
		return rolTrue;
	}

	public String getRolFalse() {
		return rolFalse;
	}

	

}
