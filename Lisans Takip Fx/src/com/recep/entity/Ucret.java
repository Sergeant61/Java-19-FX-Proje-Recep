package com.recep.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ucret {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int para;

	private String type;

	public Ucret() {
	}

	public Ucret(int para, String type) {
		super();
		this.para = para;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPara() {
		return para;
	}

	public void setPara(int para) {
		this.para = para;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
