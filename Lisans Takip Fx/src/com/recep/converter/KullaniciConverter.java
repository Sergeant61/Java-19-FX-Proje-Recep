package com.recep.converter;

import com.recep.entity.Personel;
import com.recep.entity.Rol;

public class KullaniciConverter {

	private int id;

	private String name;

	private String password;

	private String personelName;

	private Boolean rol0;

	private Boolean rol1;

	private Boolean rol2;

	private Boolean rol3;

	private Boolean rol4;

	private Rol rol;

	private Personel personel;

	public KullaniciConverter() {
	}

	public KullaniciConverter(int id, String name, String password, String personelName, Boolean rol0, Boolean rol1,
			Boolean rol2, Boolean rol3, Boolean rol4, Rol rol, Personel personel) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.personelName = personelName;
		this.rol0 = rol0;
		this.rol1 = rol1;
		this.rol2 = rol2;
		this.rol3 = rol3;
		this.rol4 = rol4;
		this.rol = rol;
		this.personel = personel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPersonelName() {
		return personelName;
	}

	public void setPersonelName(String personelName) {
		this.personelName = personelName;
	}

	public Boolean getRol0() {
		return rol0;
	}

	public void setRol0(Boolean rol0) {
		this.rol0 = rol0;
	}

	public Boolean getRol1() {
		return rol1;
	}

	public void setRol1(Boolean rol1) {
		this.rol1 = rol1;
	}

	public Boolean getRol2() {
		return rol2;
	}

	public void setRol2(Boolean rol2) {
		this.rol2 = rol2;
	}

	public Boolean getRol3() {
		return rol3;
	}

	public void setRol3(Boolean rol3) {
		this.rol3 = rol3;
	}

	public Boolean getRol4() {
		return rol4;
	}

	public void setRol4(Boolean rol4) {
		this.rol4 = rol4;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Personel getPersonel() {
		return personel;
	}

	public void setPersonel(Personel personel) {
		this.personel = personel;
	}

}
