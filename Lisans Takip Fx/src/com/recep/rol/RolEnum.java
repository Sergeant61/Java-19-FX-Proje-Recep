package com.recep.rol;

public enum RolEnum {

	KULLANICI_KAYIT("Kullan�c� Kay�t (True)","Kullan�c� Kay�t (False)"),
	KULLANICI_DUZENLEME("Kullan�c� D�zenleme (True)","Kullan�c� D�zenleme (False)"),
	LISANS_KAYIT("Lisans Kay�t (True)","Lisans Kay�t (False)"),
	LISANS_DUZENLEME("Lisans D�zenleme (True)","Lisans D�zenleme (False)"),
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
