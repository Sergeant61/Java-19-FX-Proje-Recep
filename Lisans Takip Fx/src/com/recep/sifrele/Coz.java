package com.recep.sifrele;

public class Coz {

	private String name;

	private String password;

	public Coz(String name, String password) {

		this.name = name;
		this.password = password;
	}

	public String getSifrele() {

		return this.name + " " + this.password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}
