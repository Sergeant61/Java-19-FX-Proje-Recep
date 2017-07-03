package com.recep.rol;

import java.util.ArrayList;
import java.util.List;

import com.recep.entity.Rol;

public class RolControl {

	public static List<String> getRolString(Rol rol) {

		List<String> list = new ArrayList<>();

		if (rol.getRol0()) {
			list.add(RolEnum.KULLANICI_KAYIT.getRolTrue());

		} else {
			list.add(RolEnum.KULLANICI_KAYIT.getRolFalse());
		}

		if (rol.getRol1()) {
			list.add(RolEnum.KULLANICI_DUZENLEME.getRolTrue());

		} else {
			list.add(RolEnum.KULLANICI_DUZENLEME.getRolFalse());
		}

		if (rol.getRol2()) {
			list.add(RolEnum.LISANS_KAYIT.getRolTrue());

		} else {
			list.add(RolEnum.LISANS_KAYIT.getRolFalse());
		}

		if (rol.getRol3()) {
			list.add(RolEnum.LISANS_DUZENLEME.getRolTrue());

		} else {
			list.add(RolEnum.LISANS_DUZENLEME.getRolFalse());
		}
		
		if (rol.getRol4()) {
			list.add(RolEnum.ADMIN.getRolTrue());
			
		} else {
			list.add(RolEnum.ADMIN.getRolFalse());
		}

		return list;
	}

}
