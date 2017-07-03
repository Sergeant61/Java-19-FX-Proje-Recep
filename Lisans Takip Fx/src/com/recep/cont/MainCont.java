package com.recep.cont;

import java.net.URL;
import java.util.ResourceBundle;

import com.recep.dao.DAO;
import com.recep.entity.Kullanici;
import com.recep.io.Okuyucu;
import com.recep.io.Yazici;
import com.recep.model.KullaniciModel;
import com.recep.sabitler.Sabitler;
import com.recep.sifrele.Coz;
import com.recep.sifrele.Sifrele;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainCont extends Application implements Initializable {

	@FXML
	private Button btn_giris;
	@FXML
	private TextField tf_username;
	@FXML
	private PasswordField tf_password;
	@FXML
	private Text t_warning;
	@FXML
	private CheckBox cb_beni_hatirla;

	private Stage primaryStage;
	private static String PASSURL = "pass.xlrx";
	private String warning1 = "Bilgileriniz þifrelenerek saklanýyor olsada, bilgisayarýnýza kayýt edildiði için güvenilir deðildir.";

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Lisans Takip Programý");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/recep/view/main.fxml"));
			Scene scene = new Scene(root, 400, 210);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:icon.png"));
			primaryStage.show();
			this.primaryStage = primaryStage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		primaryStage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DAO.getInstance();

		Okuyucu okuyucu = new Okuyucu();
		String value = okuyucu.dosyaAcOku(PASSURL);

		if (!value.equals("")) {

			String username = value.substring(0, value.indexOf(" "));

			String password = value.substring(value.indexOf(" ") + 1);

			Coz coz = new Coz(username, password);

			tf_username.setText(coz.getName());
			tf_password.setText(coz.getPassword());

			cb_beni_hatirla.setSelected(true);
			if (cb_beni_hatirla.isSelected()) {
				t_warning.setText(warning1);
			} else {
				t_warning.setText("");
			}
		}

		cb_beni_hatirla.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (cb_beni_hatirla.isSelected()) {
					t_warning.setText(warning1);
				} else {
					t_warning.setText("");
				}

			}
		});

	}

	@FXML
	public void onClickLogin(ActionEvent event) {

		String username = tf_username.getText(), password = tf_password.getText();

		if (!username.equals("") && !password.equals("")) {

			Sifrele sifrele = new Sifrele(username, password);
			KullaniciModel model = new KullaniciModel(sifrele);

			Kullanici kullanici = DAO.getInstance().checkKullanici(model);

			if (kullanici != null) {

				Sabitler.setKullanici(kullanici);

				Yazici yazici = new Yazici();

				if (cb_beni_hatirla.isSelected()) {
					yazici.dosyaAcYaz(PASSURL, sifrele.getSifrele());
				} else {
					yazici.dosyaAcYaz(PASSURL, "");
				}

				AnaSayfaCont anaSayfaCont = new AnaSayfaCont();
				Stage primaryStage = new Stage();
				try {
					anaSayfaCont.start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Stage stage = (Stage) btn_giris.getScene().getWindow();
				stage.hide();
				System.gc();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Giriþ Hatasý");
				alert.setHeaderText("Kullanýcý Adý ve Parola Hatalý");
				alert.setContentText("Bilgilerinizi kontrol ederek tekrar deneyiniz.");
				alert.showAndWait();
			}

		}

	}

}