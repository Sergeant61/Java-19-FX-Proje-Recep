package com.recep.model;

import com.recep.sifrele.Sifrele;

public class KullaniciModel {

	private String name;

	private String password;

	public KullaniciModel(Sifrele sifrele) {
		this.name = sifrele.getName();
		this.password = sifrele.getPassword();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
