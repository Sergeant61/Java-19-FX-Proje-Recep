package com.recep.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OzelAlert {

	public static void alert(AlertType alertType, String setTitle, String setHeaderText, String setContentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(setTitle);
		alert.setHeaderText(setHeaderText);
		alert.setContentText(setContentText);
		alert.showAndWait();
	}

}
