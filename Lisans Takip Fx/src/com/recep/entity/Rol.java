package com.recep.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Boolean rol0;

	private Boolean rol1;

	private Boolean rol2;

	private Boolean rol3;

	private Boolean rol4;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Boolean getRol0() {
		return rol0;
	}

	public void setRol0(Boolean rol0) {
		this.rol0 = rol0;
	}

}
