package com.recep.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.recep.converter.KullaniciConverter;
import com.recep.converter.LisansConverter;
import com.recep.entity.Kullanici;
import com.recep.entity.Lisans;
import com.recep.entity.SatinAlinacakLisans;
import com.recep.model.KullaniciModel;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DAO {

	private SessionFactory sessionFactory;
	private Configuration configuration;

	private static DAO uniqueInstance;

	public static DAO getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DAO();
		}
		return uniqueInstance;
	}

	public static void setInstance() {
		if (uniqueInstance != null) {
			uniqueInstance = null;
		}
	}

	public DAO() {
		try {
			configuration = new Configuration();
			configuration.configure("/com/recep/resource/hibernate.cfg.xml");

			// String[] urlTxt = Config.configOku();
			// configuration.setProperty("hibernate.connection.driver_class",
			// "com.mysql.jdbc.Driver");
			// configuration.setProperty("hibernate.connection.url", urlTxt[0]);
			// configuration.setProperty("hibernate.connection.username",
			// urlTxt[1]);
			// configuration.setProperty("hibernate.connection.password",
			// urlTxt[2]);
			// configuration.setProperty("hibernate.dialect",
			// "org.hibernate.dialect.MySQLDialect");

			sessionFactory = configuration.buildSessionFactory();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Veritabaný Hatasý");
			alert.setHeaderText("Veritabaný Konfigürasyon Hatalý");
			alert.setContentText("Ayarlar bölümünden veritabaný konfigürasyon ayarlarýný yapabilirsiniz.");
			alert.showAndWait();
		}
	}

	public Object addValue(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return obj;
	}

	public void deleteValue(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
	}

	public Object updateValue(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return obj;
	}

	public Kullanici checkKullanici(KullaniciModel kullaniciModel) {

		Kullanici user = null, userTest = null;
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Kullanici.class);

		// System.out.println(userModel.getKullaniciAdi() + " //// " +
		// userModel.getParola());

		criteria.add(Restrictions.eq("name", kullaniciModel.getName()));
		criteria.add(Restrictions.eq("password", kullaniciModel.getPassword()));

		List<Kullanici> myList = criteria.list();

		for (int i = 0; i < myList.size(); i++) {
			userTest = myList.get(i);

			if (userTest.getName().equals(kullaniciModel.getName())
					&& userTest.getPassword().equals(kullaniciModel.getPassword())) {
				user = myList.get(i);
			}
		}

		session.close();

		return user;
	}

	public List<Lisans> getListLisans() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Lisans.class);
		List<Lisans> myList = criteria.list();
		session.close();
		return myList;
	}

	public List<SatinAlinacakLisans> getListSatinLisans() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SatinAlinacakLisans.class);
		List<SatinAlinacakLisans> myList = criteria.list();
		session.close();
		return myList;
	}

	public List<LisansConverter> getListConverter() {

		List<Lisans> lisansList = getListLisans();
		List<SatinAlinacakLisans> satinLisansList = getListSatinLisans();
		List<LisansConverter> myList = new ArrayList<>();

		for (int i = 0; i < lisansList.size(); i++) {
			myList.add(new LisansConverter(lisansList.get(i).getId(), lisansList.get(i).getIsim(),
					lisansList.get(i).getSatin_alinma_tarihi(), lisansList.get(i).getGecerlilik_tarihi(), null,
					lisansList.get(i).getSatici().getIsim(), lisansList.get(i).getSatici().getAdres(),
					lisansList.get(i).getPersonel().getName(), lisansList.get(i).getUcret().getPara(),
					lisansList.get(i).getUcret().getType(), lisansList.get(i).getSatici(),
					lisansList.get(i).getPersonel(), lisansList.get(i).getUcret()));

		}

		for (int i = 0; i < satinLisansList.size(); i++) {
			myList.add(new LisansConverter(satinLisansList.get(i).getId(), satinLisansList.get(i).getIsim(), null, null,
					satinLisansList.get(i).getAlinmasi_planlanan_tarih(), satinLisansList.get(i).getSatici().getIsim(),
					satinLisansList.get(i).getSatici().getAdres(), satinLisansList.get(i).getPersonel().getName(),
					satinLisansList.get(i).getUcret().getPara(), satinLisansList.get(i).getUcret().getType(),
					satinLisansList.get(i).getSatici(), satinLisansList.get(i).getPersonel(),
					satinLisansList.get(i).getUcret()));
		}

		return myList;
	}

	public List<KullaniciConverter> getListKullaniciConverter() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Kullanici.class);
		List<Kullanici> list = criteria.list();
		List<KullaniciConverter> myList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			myList.add(new KullaniciConverter(list.get(i).getId(), list.get(i).getName(), list.get(i).getPassword(),
					list.get(i).getPersonel().getName(), list.get(i).getRol().getRol0(), list.get(i).getRol().getRol1(),
					list.get(i).getRol().getRol2(), list.get(i).getRol().getRol3(), list.get(i).getRol().getRol4(),
					list.get(i).getRol(), list.get(i).getPersonel()));
		}

		session.close();
		return myList;
	}

}
