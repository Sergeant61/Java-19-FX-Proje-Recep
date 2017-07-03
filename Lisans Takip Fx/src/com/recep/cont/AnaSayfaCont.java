package com.recep.cont;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.recep.alert.OzelAlert;
import com.recep.converter.KullaniciConverter;
import com.recep.converter.LisansConverter;
import com.recep.dao.DAO;
import com.recep.entity.Kullanici;
import com.recep.entity.Lisans;
import com.recep.entity.Personel;
import com.recep.entity.Rol;
import com.recep.entity.Satici;
import com.recep.entity.SatinAlinacakLisans;
import com.recep.entity.Ucret;
import com.recep.rol.RolControl;
import com.recep.rol.RolEnum;
import com.recep.sabitler.Sabitler;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AnaSayfaCont extends Application implements Initializable {

	@FXML
	private Label t_kullanici_adi;
	@FXML
	private Label t_kul_bilgi;
	@FXML
	private Label t_date;
	@FXML
	private Label t_cýkýs;
	@FXML
	private TabPane tabPlane;
	@FXML
	private Button btn_guncelle;
	@FXML
	private Button btn_kaydet;
	@FXML
	private Tab tab_lk;
	@FXML
	private Tab tab_lg;
	@FXML
	private Tab tab_kk;
	@FXML
	private Tab tab_kg;
	@FXML
	private ComboBox<Integer> cb_ucret;
	@FXML
	private ComboBox<String> cb_ucret_type;
	@FXML
	private TextField tf_lisans_ismi;
	@FXML
	private TextArea tf_satici_adres;
	@FXML
	private TextField tf_satici_name;
	@FXML
	private TextField tf_personel_name;
	@FXML
	private DatePicker dp_alindigi_tarih;
	@FXML
	private DatePicker dp_gecerlilik_tarih;
	@FXML
	private CheckBox cb_is_alinacak_lisans;
	@FXML
	private TableView<LisansConverter> tableList;
	@FXML
	private TableView<KullaniciConverter> tableList1;
	@FXML
	private RadioButton rb_t_eski;
	@FXML
	private RadioButton rb_t_yeni;
	@FXML
	private RadioButton rb_t_alinmamis;
	@FXML
	private RadioButton rb_t_tumu;
	@FXML
	private TextField tf_kullanici_name;
	@FXML
	private TextField tf_parola;
	@FXML
	private TextField tf_parola_tekrar;
	@FXML
	private TextField tf_personel_kayit_name;
	@FXML
	private CheckBox cb_rol00;
	@FXML
	private CheckBox cb_rol01;
	@FXML
	private CheckBox cb_rol02;
	@FXML
	private CheckBox cb_rol03;
	@FXML
	private CheckBox cb_rol04;
	@FXML
	private Button btn_guncelle1;
	@FXML
	private Button btn_kaydet1;

	private TarihYaz tarihYaz = new TarihYaz();
	private Timer myTimer = new Timer();
	private Date date = null;
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy EEEE kk:mm:ss");

	private ObservableList<Integer> data1;
	private ObservableList<String> data2;
	private ObservableList<LisansConverter> data = null;
	private ObservableList<KullaniciConverter> dataKullanici = null;

	@SuppressWarnings("deprecation")
	@Override
	public void stop() {
		tarihYaz.stop();
		System.gc();
		Platform.exit();
		System.exit(0);
		stop();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.setTitle("Lisans Takip Programý");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/recep/view/AnaSayfa.fxml"));
			Scene scene = new Scene(root, 1000, 520);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:icon.png"));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<LisansConverter, String> tbisim = new TableColumn<LisansConverter, String>("Lisans Ýsmi");
		tbisim.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("isim"));
		tbisim.setMinWidth(150);

		TableColumn<LisansConverter, String> tbsatin_alinma_tarihi = new TableColumn<LisansConverter, String>(
				"Satýn Alýnma Tarihi");
		tbsatin_alinma_tarihi
				.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("satin_alinma_tarihi"));
		tbsatin_alinma_tarihi.setMinWidth(175);

		TableColumn<LisansConverter, String> tbgecerlilik_tarihi = new TableColumn<LisansConverter, String>(
				"Geçerlilik Tarihi");
		tbgecerlilik_tarihi.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("gecerlilik_tarihi"));
		tbgecerlilik_tarihi.setMinWidth(175);

		TableColumn<LisansConverter, String> tbalinmasi_planlanan_tarih = new TableColumn<LisansConverter, String>(
				"Alýnmasý Planlanan Tarih");
		tbalinmasi_planlanan_tarih
				.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("alinmasi_planlanan_tarih"));
		tbalinmasi_planlanan_tarih.setMinWidth(185);

		TableColumn<LisansConverter, String> tbsatici_name = new TableColumn<LisansConverter, String>("Satýcý Ýsmi");
		tbsatici_name.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("satici_name"));
		tbsatici_name.setMinWidth(100);

		TableColumn<LisansConverter, String> tbsatici_adres = new TableColumn<LisansConverter, String>("Satýcý Adresi");
		tbsatici_adres.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("satici_adres"));
		tbsatici_adres.setMinWidth(150);

		TableColumn<LisansConverter, String> tbpersonel_name = new TableColumn<LisansConverter, String>(
				"Persnonel Ýsmi");
		tbpersonel_name.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("personel_name"));
		tbpersonel_name.setMinWidth(100);

		TableColumn<LisansConverter, String> tbucret = new TableColumn<LisansConverter, String>("Ücret");
		tbucret.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("ucret"));

		TableColumn<LisansConverter, String> tbucret_type = new TableColumn<LisansConverter, String>("Ücret Tipi");
		tbucret_type.setCellValueFactory(new PropertyValueFactory<LisansConverter, String>("ucret_type"));

		tableList.getColumns().addAll(tbisim, tbsatin_alinma_tarihi, tbgecerlilik_tarihi, tbalinmasi_planlanan_tarih,
				tbsatici_name, tbsatici_adres, tbpersonel_name, tbucret, tbucret_type);
		tableList.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

		/*----------------------------------------------------------------------------------------------*/

		TableColumn<KullaniciConverter, String> tb_kul_name = new TableColumn<KullaniciConverter, String>(
				"Kullanýcý Ýsmi");
		tb_kul_name.setCellValueFactory(new PropertyValueFactory<KullaniciConverter, String>("name"));
		tb_kul_name.setMinWidth(150);

		TableColumn<KullaniciConverter, String> tb_pass_name = new TableColumn<KullaniciConverter, String>(
				"Personel Ýsmi");
		tb_pass_name.setCellValueFactory(new PropertyValueFactory<KullaniciConverter, String>("personelName"));
		tb_pass_name.setMinWidth(150);

		TableColumn<KullaniciConverter, String> tb_rol0 = new TableColumn<KullaniciConverter, String>("Rol 0");
		tb_rol0.setCellValueFactory(new PropertyValueFactory<KullaniciConverter, String>("rol0"));
		tb_rol0.setMinWidth(50);

		TableColumn<KullaniciConverter, String> tb_rol1 = new TableColumn<KullaniciConverter, String>("Rol 1");
		tb_rol1.setCellValueFactory(new PropertyValueFactory<KullaniciConverter, String>("rol1"));
		tb_rol1.setMinWidth(50);

		TableColumn<KullaniciConverter, String> tb_rol2 = new TableColumn<KullaniciConverter, String>("Rol 2");
		tb_rol2.setCellValueFactory(new PropertyValueFactory<KullaniciConverter, String>("rol2"));
		tb_rol2.setMinWidth(50);

		TableColumn<KullaniciConverter, String> tb_rol3 = new TableColumn<KullaniciConverter, String>("Rol 3");
		tb_rol3.setCellValueFactory(new PropertyValueFactory<KullaniciConverter, String>("rol3"));
		tb_rol3.setMinWidth(50);

		TableColumn<KullaniciConverter, String> tb_rol4 = new TableColumn<KullaniciConverter, String>("Rol 4");
		tb_rol4.setCellValueFactory(new PropertyValueFactory<KullaniciConverter, String>("rol4"));
		tb_rol4.setMinWidth(50);

		tableList1.getColumns().addAll(tb_kul_name, tb_pass_name, tb_rol0, tb_rol1, tb_rol2, tb_rol3, tb_rol4);
		tableList1.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

		ToggleGroup group = new ToggleGroup();
		rb_t_tumu.setSelected(true);

		rb_t_eski.setToggleGroup(group);
		rb_t_yeni.setToggleGroup(group);
		rb_t_alinmamis.setToggleGroup(group);
		rb_t_tumu.setToggleGroup(group);

		Integer[] ucret = new Integer[1000];
		for (int i = 0; i < 1000; i++) {
			ucret[i] = i + 1;
		}

		String[] ucretType = new String[] { "TL", "DOLAR", "EURO", "ALTIN", "STERLÝN" };

		data1 = FXCollections.observableArrayList(ucret);
		data2 = FXCollections.observableArrayList(ucretType);

		cb_ucret.setItems(data1);
		cb_ucret.setPromptText("Ücreti Seçin");
		cb_ucret_type.setItems(data2);
		cb_ucret_type.setPromptText("Ücret Tipini Seçin");

		tf_personel_name.setText(Sabitler.getKullanici().getPersonel().getName());
		cb_is_alinacak_lisans.setText("Alýnmýþ Lisans");
		dp_alindigi_tarih.setPromptText("Alýndýðý Tarih");
		t_cýkýs.setTextFill(Color.WHITE);
		t_kullanici_adi.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		t_kul_bilgi.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		t_date.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		t_cýkýs.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));

		DAO.getInstance();

		t_kullanici_adi.setText("Kullanýcý Adý: " + Sabitler.getKullanici().getName() + " ("
				+ Sabitler.getKullanici().getPersonel().getName() + ")");

		if (!Sabitler.getKullanici().getRol().getRol0()) {
			tab_lk.setDisable(true);
		}
		if (!Sabitler.getKullanici().getRol().getRol1()) {
			tab_lg.setDisable(true);
		}
		if (!Sabitler.getKullanici().getRol().getRol2()) {
			tab_kk.setDisable(true);
		}
		if (!Sabitler.getKullanici().getRol().getRol3()) {
			tab_kg.setDisable(true);
		}
		if (Sabitler.getKullanici().getRol().getRol4()) {
			tab_lk.setDisable(false);
			tab_lg.setDisable(false);
			tab_kk.setDisable(false);
			tab_kg.setDisable(false);
		}

		cb_rol00.setText(RolEnum.KULLANICI_KAYIT.getRolFalse());
		cb_rol01.setText(RolEnum.KULLANICI_DUZENLEME.getRolFalse());
		cb_rol02.setText(RolEnum.LISANS_KAYIT.getRolFalse());
		cb_rol03.setText(RolEnum.LISANS_DUZENLEME.getRolFalse());
		cb_rol04.setText(RolEnum.ADMIN.getRolFalse());

		List<String> list = RolControl.getRolString(Sabitler.getKullanici().getRol());

		t_kul_bilgi.setText(
				list.get(0) + ", " + list.get(1) + ", " + list.get(2) + ",\n" + list.get(3) + ", " + list.get(4));
		tarihYaz.start();

	}

	class TarihYaz extends Thread {

		public void run() {

			myTimer.schedule(new TimerTask() {

				@Override
				public void run() {
					Platform.runLater(new Runnable() {
						public void run() {
							date = Calendar.getInstance().getTime();

							t_date.setText(df.format(date));
						}
					});

				}
			}, 0, 500);

		}

	}

	@FXML
	public void onTcýkýsMoved() {
		t_cýkýs.setUnderline(true);
	}

	@FXML
	public void onTcýkýsCkýck() {

		MainCont mainCont = new MainCont();
		Stage primaryStage = new Stage();
		try {

			Sabitler.setKullanici(null);
			mainCont.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stage stage = (Stage) t_cýkýs.getScene().getWindow();
		stage.close();

	}

	@FXML
	public void onTcýkýsExited() {
		t_cýkýs.setUnderline(false);
	}

	@FXML
	public void onClickKaydet() {

		if (cb_is_alinacak_lisans.isSelected()) {

			if (!tf_lisans_ismi.getText().equals("")) {

				if (cb_ucret.getValue() != null && cb_ucret_type.getValue() != null) {

					SatinAlinacakLisans alinacakLisans = new SatinAlinacakLisans();
					Satici satici = new Satici(tf_satici_name.getText(), tf_satici_adres.getText());
					Ucret ucret = new Ucret(cb_ucret.getValue(), cb_ucret_type.getValue());

					alinacakLisans.setIsim(tf_lisans_ismi.getText());

					alinacakLisans.setAlinmasi_planlanan_tarih(getDatePickerDate(dp_alindigi_tarih.getValue()));
					alinacakLisans.setPersonel(Sabitler.getKullanici().getPersonel());
					alinacakLisans.setSatici(satici);
					alinacakLisans.setUcret(ucret);

					DAO.getInstance().addValue(satici);
					DAO.getInstance().addValue(ucret);
					SatinAlinacakLisans getalinacakLisans = (SatinAlinacakLisans) DAO.getInstance()
							.addValue(alinacakLisans);

					if (getalinacakLisans.getId() != 0) {

						tf_satici_name.setText("");
						tf_satici_adres.setText("");
						tf_lisans_ismi.setText("");
						dp_gecerlilik_tarih.setValue(null);
						dp_alindigi_tarih.setValue(null);
						cb_ucret.setValue(null);
						cb_ucret_type.setValue(null);

						OzelAlert.alert(AlertType.INFORMATION, "Kayýt Baþarýlý", "Lisans Kaydý",
								"Lisans kaydý baþarýlý.");
					} else {
						OzelAlert.alert(AlertType.ERROR, "Kayýt Baþarýsýz", "Lisans Kaydý", "Lisans kaydý baþarýsýz.");

					}

				} else {
					OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Ücret bölümü boþ",
							"Ücret ve ücret tipi boþ býrakýlamaz.");
				}

			} else {
				OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Lisans ismi boþ", "Lisans ismi boþ býrakýlamaz.");
			}

		} else {

			if (!tf_lisans_ismi.getText().equals("")) {

				if (cb_ucret.getValue() != null && cb_ucret_type.getValue() != null) {

					Lisans lisans = new Lisans();
					Satici satici = new Satici(tf_satici_name.getText(), tf_satici_adres.getText());
					Ucret ucret = new Ucret(cb_ucret.getValue(), cb_ucret_type.getValue());

					lisans.setIsim(tf_lisans_ismi.getText());
					lisans.setPersonel(Sabitler.getKullanici().getPersonel());
					lisans.setSatici(satici);
					lisans.setUcret(ucret);
					lisans.setSatin_alinma_tarihi(getDatePickerDate(dp_alindigi_tarih.getValue()));
					lisans.setGecerlilik_tarihi(getDatePickerDate(dp_gecerlilik_tarih.getValue()));

					DAO.getInstance().addValue(satici);
					DAO.getInstance().addValue(ucret);
					Lisans getLisans = (Lisans) DAO.getInstance().addValue(lisans);

					if (getLisans.getId() != 0) {

						tf_satici_name.setText("");
						tf_satici_adres.setText("");
						tf_lisans_ismi.setText("");
						dp_gecerlilik_tarih.setValue(null);
						dp_alindigi_tarih.setValue(null);
						cb_ucret.setValue(null);
						cb_ucret_type.setValue(null);

						OzelAlert.alert(AlertType.INFORMATION, "Kayýt Baþarýlý", "Lisans Kaydý",
								"Lisans kaydý baþarýlý.");
					} else {
						OzelAlert.alert(AlertType.ERROR, "Kayýt Baþarýsýz", "Lisans Kaydý", "Lisans kaydý baþarýsýz.");

					}

				} else {
					OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Ücret bölümü boþ",
							"Ücret ve ücret tipi boþ býrakýlamaz.");
				}

			} else {
				OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Lisans ismi boþ", "Lisans ismi boþ býrakýlamaz.");

			}

		}

	}

	@FXML
	public void onClickGuncelle() {

		if (cb_is_alinacak_lisans.isSelected()) {

			if (!tf_lisans_ismi.getText().equals("")) {

				if (cb_ucret.getValue() != null && cb_ucret_type.getValue() != null) {

					SatinAlinacakLisans alinacakLisans = new SatinAlinacakLisans();
					Satici satici = new Satici(tf_satici_name.getText(), tf_satici_adres.getText());
					Ucret ucret = new Ucret(cb_ucret.getValue(), cb_ucret_type.getValue());
					satici.setId(Sabitler.getGuncellenecekLisans().getSatici().getId());
					ucret.setId(Sabitler.getGuncellenecekLisans().getUcrett().getId());
					alinacakLisans.setId(Sabitler.getGuncellenecekLisans().getSira_no());

					alinacakLisans.setIsim(tf_lisans_ismi.getText());

					alinacakLisans.setAlinmasi_planlanan_tarih(getDatePickerDate(dp_alindigi_tarih.getValue()));
					alinacakLisans.setPersonel(Sabitler.getKullanici().getPersonel());
					alinacakLisans.setSatici(satici);
					alinacakLisans.setUcret(ucret);

					DAO.getInstance().updateValue(satici);
					DAO.getInstance().updateValue(ucret);
					SatinAlinacakLisans getalinacakLisans = (SatinAlinacakLisans) DAO.getInstance()
							.updateValue(alinacakLisans);

					if (getalinacakLisans.getId() != 0) {

						tf_satici_name.setText("");
						tf_satici_adres.setText("");
						tf_lisans_ismi.setText("");
						dp_gecerlilik_tarih.setValue(null);
						dp_alindigi_tarih.setValue(null);
						cb_ucret.setValue(null);
						cb_ucret_type.setValue(null);

						OzelAlert.alert(AlertType.INFORMATION, "Kayýt Baþarýlý", "Lisans Kaydý",
								"Lisans kaydý baþarýlý.");
					} else {
						OzelAlert.alert(AlertType.ERROR, "Kayýt Baþarýsýz", "Lisans Kaydý", "Lisans kaydý baþarýsýz.");

					}

				} else {
					OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Ücret bölümü boþ",
							"Ücret ve ücret tipi boþ býrakýlamaz.");
				}

			} else {
				OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Lisans ismi boþ", "Lisans ismi boþ býrakýlamaz.");
			}

		} else {

			if (!tf_lisans_ismi.getText().equals("")) {

				if (cb_ucret.getValue() != null && cb_ucret_type.getValue() != null) {

					Lisans lisans = new Lisans();
					Satici satici = new Satici(tf_satici_name.getText(), tf_satici_adres.getText());
					Ucret ucret = new Ucret(cb_ucret.getValue(), cb_ucret_type.getValue());
					satici.setId(Sabitler.getGuncellenecekLisans().getSatici().getId());
					ucret.setId(Sabitler.getGuncellenecekLisans().getUcrett().getId());
					lisans.setId(Sabitler.getGuncellenecekLisans().getSira_no());

					lisans.setIsim(tf_lisans_ismi.getText());
					lisans.setPersonel(Sabitler.getKullanici().getPersonel());
					lisans.setSatici(satici);
					lisans.setUcret(ucret);
					lisans.setSatin_alinma_tarihi(getDatePickerDate(dp_alindigi_tarih.getValue()));
					lisans.setGecerlilik_tarihi(getDatePickerDate(dp_gecerlilik_tarih.getValue()));

					DAO.getInstance().updateValue(satici);
					DAO.getInstance().updateValue(ucret);
					Lisans getLisans = (Lisans) DAO.getInstance().updateValue(lisans);

					if (getLisans.getId() != 0) {

						tf_satici_name.setText("");
						tf_satici_adres.setText("");
						tf_lisans_ismi.setText("");
						dp_gecerlilik_tarih.setValue(null);
						dp_alindigi_tarih.setValue(null);
						cb_ucret.setValue(null);
						cb_ucret_type.setValue(null);

						OzelAlert.alert(AlertType.INFORMATION, "Kayýt Baþarýlý", "Lisans Kaydý",
								"Lisans kaydý baþarýlý.");
					} else {
						OzelAlert.alert(AlertType.ERROR, "Kayýt Baþarýsýz", "Lisans Kaydý", "Lisans kaydý baþarýsýz.");

					}

				} else {
					OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Ücret bölümü boþ",
							"Ücret ve ücret tipi boþ býrakýlamaz.");
				}

			} else {
				OzelAlert.alert(AlertType.ERROR, "Kayýt Hatasý", "Lisans ismi boþ", "Lisans ismi boþ býrakýlamaz.");

			}

		}

	}

	@FXML
	public void onClickKaydet1() {

		if (!tf_kullanici_name.getText().equals("") && !tf_parola.getText().equals("")
				&& !tf_parola_tekrar.getText().equals("")) {

			if (tf_parola.getText().equals(tf_parola_tekrar.getText())) {

				Personel personel = new Personel();
				personel.setName(tf_personel_kayit_name.getText());

				Rol rol = new Rol();

				System.out.println();

				rol.setRol0(cb_rol00.isSelected());
				rol.setRol1(cb_rol01.isSelected());
				rol.setRol2(cb_rol02.isSelected());
				rol.setRol3(cb_rol03.isSelected());
				rol.setRol4(cb_rol04.isSelected());

				Kullanici kullanici = new Kullanici();
				kullanici.setName(tf_kullanici_name.getText());
				kullanici.setPassword(tf_parola.getText());
				kullanici.setPersonel(personel);
				kullanici.setRol(rol);

				DAO.getInstance().addValue(rol);
				DAO.getInstance().addValue(personel);
				Kullanici getKullanici = (Kullanici) DAO.getInstance().addValue(kullanici);
				if (getKullanici.getId() != 0) {
					tf_kullanici_name.setText("");
					tf_parola.setText("");
					tf_parola_tekrar.setText("");
					tf_personel_kayit_name.setText("");
					cb_rol00.setSelected(false);
					cb_rol01.setSelected(false);
					cb_rol02.setSelected(false);
					cb_rol03.setSelected(false);
					cb_rol04.setSelected(false);
					cb_rol00.setText(RolEnum.KULLANICI_KAYIT.getRolFalse());
					cb_rol01.setText(RolEnum.KULLANICI_DUZENLEME.getRolFalse());
					cb_rol02.setText(RolEnum.LISANS_KAYIT.getRolFalse());
					cb_rol03.setText(RolEnum.LISANS_DUZENLEME.getRolFalse());
					cb_rol04.setText(RolEnum.ADMIN.getRolFalse());

					OzelAlert.alert(AlertType.INFORMATION, "Kayýt Baþarýlý", "Kullanýcý Kaydý",
							"Kullanýcý kaydý baþarýlý.");
				}

			} else {
				OzelAlert.alert(AlertType.ERROR, "Kayýt Hatýsý", "Parola uyuþmazlýðý",
						"Girmiþ olduðunuz parolalarý kontrol ederek tekrar deneyiniz.");
			}

		} else {
			OzelAlert.alert(AlertType.ERROR, "Kayýt Hatýsý", "Gerekli bölümler boþ",
					"'*' ile olan bölümler zorunludur.");
		}

	}

	@FXML
	public void onClickGuncelle1() {

		if (!tf_kullanici_name.getText().equals("") && !tf_parola.getText().equals("")
				&& !tf_parola_tekrar.getText().equals("")) {

			if (tf_parola.getText().equals(tf_parola_tekrar.getText())) {

				Personel personel = new Personel();
				personel.setName(tf_personel_kayit_name.getText());
				personel.setId(Sabitler.getGuncellenecekKullanici().getPersonel().getId());

				Rol rol = new Rol();
				rol.setId(Sabitler.getGuncellenecekKullanici().getRol().getId());

				rol.setRol0(cb_rol00.isSelected());
				rol.setRol1(cb_rol01.isSelected());
				rol.setRol2(cb_rol02.isSelected());
				rol.setRol3(cb_rol03.isSelected());
				rol.setRol4(cb_rol04.isSelected());

				Kullanici kullanici = new Kullanici();
				kullanici.setId(Sabitler.getGuncellenecekKullanici().getId());
				kullanici.setName(tf_kullanici_name.getText());
				kullanici.setPassword(tf_parola.getText());
				kullanici.setPersonel(personel);
				kullanici.setRol(rol);

				DAO.getInstance().updateValue(rol);
				DAO.getInstance().updateValue(personel);
				Kullanici getKullanici = (Kullanici) DAO.getInstance().updateValue(kullanici);
				if (getKullanici.getId() != 0) {
					tf_kullanici_name.setText("");
					tf_parola.setText("");
					tf_parola_tekrar.setText("");
					tf_personel_kayit_name.setText("");
					cb_rol00.setSelected(false);
					cb_rol01.setSelected(false);
					cb_rol02.setSelected(false);
					cb_rol03.setSelected(false);
					cb_rol04.setSelected(false);
					cb_rol00.setText(RolEnum.KULLANICI_KAYIT.getRolFalse());
					cb_rol01.setText(RolEnum.KULLANICI_DUZENLEME.getRolFalse());
					cb_rol02.setText(RolEnum.LISANS_KAYIT.getRolFalse());
					cb_rol03.setText(RolEnum.LISANS_DUZENLEME.getRolFalse());
					cb_rol04.setText(RolEnum.ADMIN.getRolFalse());

					OzelAlert.alert(AlertType.INFORMATION, "Kayýt Baþarýlý", "Kullanýcý Kaydý",
							"Kullanýcý kaydý baþarýlý.");
				}

			} else {
				OzelAlert.alert(AlertType.ERROR, "Kayýt Hatýsý", "Parola uyuþmazlýðý",
						"Girmiþ olduðunuz parolalarý kontrol ederek tekrar deneyiniz.");
			}

		} else {
			OzelAlert.alert(AlertType.ERROR, "Kayýt Hatýsý", "Gerekli bölümler boþ",
					"'*' ile olan bölümler zorunludur.");
		}

	}

	private Date getDatePickerDate(LocalDate value) {

		LocalDate ld;
		Calendar c;
		Date getDate1;

		ld = value;
		c = Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
		getDate1 = c.getTime();

		return getDate1;
	}

	@FXML
	public void onActionCheckLisans() {

		if (cb_is_alinacak_lisans.isSelected()) {
			cb_is_alinacak_lisans.setText("Alýnacaðý Planlanan Lisans");
			dp_alindigi_tarih.setPromptText("Alýnacaðý Tarih");
			dp_gecerlilik_tarih.setDisable(true);

		} else {
			cb_is_alinacak_lisans.setText("Hali Hazýrda Alýnmýþ Lisans");
			dp_alindigi_tarih.setPromptText("Alýndýðý Tarih");
			dp_gecerlilik_tarih.setDisable(false);

		}

	}

	@FXML
	public void onLgChanged() {

		List<LisansConverter> list = DAO.getInstance().getListConverter();

		data = FXCollections.observableArrayList(list);

		/*
		 * Burdan bir sonraki yorum satýrýna kadar olanlar; TableView'ýn
		 * özelleþtirmesini içeren kodlar
		 * 
		 */
		tableList.setRowFactory(new Callback<TableView<LisansConverter>, TableRow<LisansConverter>>() {

			@Override
			public TableRow<LisansConverter> call(TableView<LisansConverter> param) {

				return new TableRow<LisansConverter>() {

					@Override
					protected void updateItem(LisansConverter item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setText(null);
							setStyle("");
						} else {

							tabloDuzenle(item);

							selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
								if (isNowSelected) {
									setTextFill(Color.BLACK);
									setStyle("-fx-font-weight:bold;");
								} else {

									tabloDuzenle(item);
								}
							});

						}

					}

					private void tabloDuzenle(LisansConverter item) {
						Date date = Calendar.getInstance().getTime();
						String style;
						if (item.getGecerlilik_tarihi() != null) {

							if (item.getGecerlilik_tarihi().getTime() >= date.getTime()) {
								setTextFill(Color.BLACK);
								style = "-fx-background-color: linear-gradient(#007F0E 0%, #FFFFFF 90%, #eaeaea 50%);";
								setStyle(style);

							} else if (item.getGecerlilik_tarihi().getTime() < date.getTime()) {

								setTextFill(Color.BLACK);
								style = "-fx-background-color: linear-gradient(#ff0000 0%, #FFFFFF 90%, #eaeaea 50%);";
								setStyle(style);
							}

						} else {
							setTextFill(Color.BLACK);
							style = "-fx-background-color: linear-gradient(#fde400 0%, #FFFFFF 90%, #eaeaea 50%);";
							setStyle(style);

						}
					}

				};
			}

		});
		/*
		 * Bitiþ
		 */

		/*
		 * Burdan bir sonraki yorum satýrýna kadar olanlar; TableView'ýn radion
		 * buton seçeneklerine göre sýralanmasýný saðlayan kodlar aþaðýdadýr.
		 */
		rb_t_tumu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (rb_t_tumu.isSelected()) {
					data = FXCollections.observableArrayList(list);
					tableList.setItems(data);

				}
			}
		});

		rb_t_eski.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (rb_t_eski.isSelected()) {

					List<LisansConverter> myList = new ArrayList<>();
					Date date = Calendar.getInstance().getTime();

					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getGecerlilik_tarihi() != null) {
							if (list.get(i).getGecerlilik_tarihi().getTime() < date.getTime()) {
								myList.add(list.get(i));
							}
						}
					}

					data = FXCollections.observableArrayList(myList);
					tableList.setItems(data);
				}
			}
		});

		rb_t_yeni.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (rb_t_yeni.isSelected()) {

					List<LisansConverter> myList = new ArrayList<>();
					Date date = Calendar.getInstance().getTime();

					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getGecerlilik_tarihi() != null) {
							if (list.get(i).getGecerlilik_tarihi().getTime() >= date.getTime()) {
								myList.add(list.get(i));
							}
						}
					}

					data = FXCollections.observableArrayList(myList);
					tableList.setItems(data);
				}
			}
		});

		rb_t_alinmamis.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (rb_t_alinmamis.isSelected()) {

					List<LisansConverter> myList = new ArrayList<>();

					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getGecerlilik_tarihi() == null) {
							myList.add(list.get(i));
						}
					}

					data = FXCollections.observableArrayList(myList);
					tableList.setItems(data);
				}
			}
		});

		tableList.setItems(data);
		/*
		 * Bitiþ
		 */

		/*
		 * Burdan bir sonraki yorum satýrýna kadar olanlar; Güncelleme bölümü
		 * için bir ContextMenu oluþturuldu, ContextMenu'nün 2 tane MenuItemi
		 * mevcut "Sil","Düzenle" adýnda, Silme ve Düzenleme geçiþlerini
		 * saðlayan kodlar aþaðýdadýr.
		 */
		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("Sil");
		cm.getItems().add(mi1);
		MenuItem mi2 = new MenuItem("Düzenle");
		cm.getItems().add(mi2);

		tableList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {
					cm.show(tableList, t.getScreenX(), t.getScreenY());
				}
			}
		});

		mi1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cm.hide();
				LisansConverter lisansConverter = tableList.getSelectionModel().getSelectedItem();
				if (lisansConverter.getGecerlilik_tarihi() == null) {

					SatinAlinacakLisans alinacakLisans = new SatinAlinacakLisans();
					alinacakLisans.setId(lisansConverter.getSira_no());
					DAO.getInstance().deleteValue(alinacakLisans);

					Satici satici = new Satici();
					satici.setId(lisansConverter.getSatici().getId());
					DAO.getInstance().deleteValue(satici);

					Ucret ucret = new Ucret();
					ucret.setId(lisansConverter.getUcrett().getId());
					DAO.getInstance().deleteValue(ucret);

					List<LisansConverter> list = DAO.getInstance().getListConverter();
					data = FXCollections.observableArrayList(list);
					tableList.setItems(data);

				} else {
					Lisans lisans = new Lisans();
					lisans.setId(lisansConverter.getSira_no());
					DAO.getInstance().deleteValue(lisans);

					Satici satici = new Satici();
					satici.setId(lisansConverter.getSatici().getId());
					DAO.getInstance().deleteValue(satici);

					Ucret ucret = new Ucret();
					ucret.setId(lisansConverter.getUcrett().getId());
					DAO.getInstance().deleteValue(ucret);

					List<LisansConverter> list = DAO.getInstance().getListConverter();
					data = FXCollections.observableArrayList(list);
					tableList.setItems(data);
				}

			}
		});

		mi2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cm.hide();
				LisansConverter lisansConverter = tableList.getSelectionModel().getSelectedItem();
				if (lisansConverter.getGecerlilik_tarihi() == null) {
					cb_is_alinacak_lisans.setDisable(true);
					cb_is_alinacak_lisans.setSelected(true);
					dp_gecerlilik_tarih.setDisable(true);

					tf_lisans_ismi.setText(lisansConverter.getIsim());
					tf_personel_name.setText(lisansConverter.getPersonel_name());
					tf_satici_name.setText(lisansConverter.getSatici_name());
					tf_satici_adres.setText(lisansConverter.getSatici_adres());
					cb_ucret.setValue(lisansConverter.getUcret());
					cb_ucret_type.setValue(lisansConverter.getUcret_type());

					dp_alindigi_tarih.setValue(lisansConverter.getAlinmasi_planlanan_tarih().toInstant()
							.atZone(ZoneId.systemDefault()).toLocalDate());

				} else {
					cb_is_alinacak_lisans.setDisable(true);
					cb_is_alinacak_lisans.setSelected(false);
					dp_gecerlilik_tarih.setDisable(false);

					tf_lisans_ismi.setText(lisansConverter.getIsim());
					tf_personel_name.setText(lisansConverter.getPersonel_name());
					tf_satici_name.setText(lisansConverter.getSatici_name());
					tf_satici_adres.setText(lisansConverter.getSatici_adres());
					cb_ucret.setValue(lisansConverter.getUcret());
					cb_ucret_type.setValue(lisansConverter.getUcret_type());

					dp_alindigi_tarih.setValue(lisansConverter.getSatin_alinma_tarihi().toInstant()
							.atZone(ZoneId.systemDefault()).toLocalDate());
					dp_gecerlilik_tarih.setValue(lisansConverter.getGecerlilik_tarihi().toInstant()
							.atZone(ZoneId.systemDefault()).toLocalDate());

				}

				Sabitler.setGuncellenecekLisans(lisansConverter);
				tabPlane.getSelectionModel().select(0);
				btn_kaydet.setVisible(false);
				btn_guncelle.setVisible(true);

			}
		});
		/*
		 * Bitiþ.
		 */

	}

	@FXML
	public void onLkChanged() {
		tabPlane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
				if (t != tab_lg) {
					btn_kaydet.setVisible(true);
					btn_guncelle.setVisible(false);
					tf_satici_name.setText("");
					tf_satici_adres.setText("");
					tf_lisans_ismi.setText("");
					dp_gecerlilik_tarih.setValue(null);
					dp_alindigi_tarih.setValue(null);
					cb_ucret.setValue(null);
					cb_ucret_type.setValue(null);
					cb_is_alinacak_lisans.setDisable(false);
				}

			}
		});

	}

	@FXML
	public void onKgChanged() {

		List<KullaniciConverter> list = DAO.getInstance().getListKullaniciConverter();

		dataKullanici = FXCollections.observableArrayList(list);

		tableList1.setItems(dataKullanici);
		/*
		 * Burdan bir sonraki yorum satýrýna kadar olanlar; Güncelleme bölümü
		 * için bir ContextMenu oluþturuldu, ContextMenu'nün 2 tane MenuItemi
		 * mevcut "Sil","Düzenle" adýnda, Silme ve Düzenleme geçiþlerini
		 * saðlayan kodlar aþaðýdadýr.
		 */
		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("Sil");
		cm.getItems().add(mi1);
		MenuItem mi2 = new MenuItem("Düzenle");
		cm.getItems().add(mi2);

		tableList1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {
					cm.show(tableList, t.getScreenX(), t.getScreenY());
				}
			}
		});

		mi1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cm.hide();
				KullaniciConverter kullaniciConverter = tableList1.getSelectionModel().getSelectedItem();
				Kullanici kullanici = new Kullanici();
				kullanici.setId(kullaniciConverter.getId());
				DAO.getInstance().deleteValue(kullanici);

				Personel personel = new Personel();
				personel.setId(kullaniciConverter.getPersonel().getId());
				DAO.getInstance().deleteValue(personel);

				List<KullaniciConverter> list = DAO.getInstance().getListKullaniciConverter();
				dataKullanici = FXCollections.observableArrayList(list);
				tableList1.setItems(dataKullanici);

			}
		});

		mi2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cm.hide();
				KullaniciConverter kullaniciConverter = tableList1.getSelectionModel().getSelectedItem();

				tf_kullanici_name.setText(kullaniciConverter.getName());
				tf_parola.setText(kullaniciConverter.getPassword());
				tf_parola_tekrar.setText(kullaniciConverter.getPassword());
				tf_personel_kayit_name.setText(kullaniciConverter.getPersonelName());

				cb_rol00.setSelected(kullaniciConverter.getRol0());
				cb_rol01.setSelected(kullaniciConverter.getRol1());
				cb_rol02.setSelected(kullaniciConverter.getRol2());
				cb_rol03.setSelected(kullaniciConverter.getRol3());
				cb_rol04.setSelected(kullaniciConverter.getRol4());

				if (cb_rol00.isSelected()) {
					cb_rol00.setText(RolEnum.KULLANICI_KAYIT.getRolTrue());
				}
				if (cb_rol01.isSelected()) {
					cb_rol01.setText(RolEnum.KULLANICI_DUZENLEME.getRolTrue());
				}
				if (cb_rol02.isSelected()) {
					cb_rol02.setText(RolEnum.LISANS_KAYIT.getRolTrue());
				}
				if (cb_rol03.isSelected()) {
					cb_rol03.setText(RolEnum.LISANS_DUZENLEME.getRolTrue());
				}
				if (cb_rol04.isSelected()) {
					cb_rol04.setText(RolEnum.ADMIN.getRolTrue());
				}

				Sabitler.setGuncellenecekKullanici(kullaniciConverter);
				tabPlane.getSelectionModel().select(2);
				btn_kaydet1.setVisible(false);
				btn_guncelle1.setVisible(true);
			}
		});
		/*
		 * Bitiþ.
		 */

	}

	@FXML
	public void onKkChanged() {
		tabPlane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
				if (t != tab_kg) {
					btn_kaydet1.setVisible(true);
					btn_guncelle1.setVisible(false);
					tf_kullanici_name.setText("");
					tf_parola.setText("");
					tf_parola_tekrar.setText("");
					tf_personel_kayit_name.setText("");
					cb_rol00.setSelected(false);
					cb_rol01.setSelected(false);
					cb_rol02.setSelected(false);
					cb_rol03.setSelected(false);
					cb_rol04.setSelected(false);
					cb_rol00.setText(RolEnum.KULLANICI_KAYIT.getRolFalse());
					cb_rol01.setText(RolEnum.KULLANICI_DUZENLEME.getRolFalse());
					cb_rol02.setText(RolEnum.LISANS_KAYIT.getRolFalse());
					cb_rol03.setText(RolEnum.LISANS_DUZENLEME.getRolFalse());
					cb_rol04.setText(RolEnum.ADMIN.getRolFalse());
				}

			}
		});

	}

	@FXML
	public void onActionCheckRol0() {

		if (cb_rol00.isSelected()) {
			cb_rol00.setText(RolEnum.KULLANICI_KAYIT.getRolTrue());

		} else {
			cb_rol00.setText(RolEnum.KULLANICI_KAYIT.getRolFalse());
		}

	}

	@FXML
	public void onActionCheckRol1() {

		if (cb_rol01.isSelected()) {
			cb_rol01.setText(RolEnum.KULLANICI_DUZENLEME.getRolTrue());

		} else {
			cb_rol01.setText(RolEnum.KULLANICI_DUZENLEME.getRolFalse());
		}

	}

	@FXML
	public void onActionCheckRol2() {

		if (cb_rol02.isSelected()) {
			cb_rol02.setText(RolEnum.LISANS_KAYIT.getRolTrue());

		} else {
			cb_rol02.setText(RolEnum.LISANS_KAYIT.getRolFalse());
		}

	}

	@FXML
	public void onActionCheckRol3() {

		if (cb_rol03.isSelected()) {
			cb_rol03.setText(RolEnum.LISANS_DUZENLEME.getRolTrue());

		} else {
			cb_rol03.setText(RolEnum.LISANS_DUZENLEME.getRolFalse());
		}

	}

	@FXML
	public void onActionCheckRol4() {

		if (cb_rol04.isSelected()) {
			cb_rol04.setText(RolEnum.ADMIN.getRolTrue());

		} else {
			cb_rol04.setText(RolEnum.ADMIN.getRolFalse());
		}

	}

}