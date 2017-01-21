package com.softgroup.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.softgroup.entity.Contact;

/*
 * Клас відповідає за роботу з базою даних. Всі необхідні параметри для
 * підключення до неї зберігаются в окремому файлі DB.properties
 * Змініть значення ключів у ньому у відповідності до вашої бази даних.
 * Таблиця для контактів буде створена автоматично при першому запуску програми
 */
public class GuestBookDao {

	private static final String DB_PROPERTIES_FILE = "DB.properties";
	private static final String CREATE_TABLE_QUERY =
			"CREATE TABLE IF NOT EXISTS `contact_tb`("
			+ "id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, "
			+ "first_name VARCHAR(20) NOT NULL, "
			+ "last_name VARCHAR(20) NOT NULL, "
			+ "birthday DATE NOT NULL, "
			+ "phone_number VARCHAR(20) NOT NULL, "
			+ "email VARCHAR(50) NOT NULL, "
			+ "country VARCHAR(20) NOT NULL, "
			+ "city VARCHAR(50) NOT NULL) "
			+ "CHARACTER SET utf8";
	private static final String SAVE_CONTACT_QUERY =
			"INSERT INTO `contact_tb`(first_name, last_name, birthday, "
			+ "phone_number, email, country, city) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_QUERY = "SELECT * FROM `contact_tb`";
	
	private static BasicDataSource dataSource;
	
	public GuestBookDao() {
		initDataSource();
		createTable();
	}
	
	public void save(Contact contact) {
		try (Connection conn = getConnection()) {
			try (PreparedStatement st = conn.prepareStatement(SAVE_CONTACT_QUERY)) {
				st.setString(1, contact.getFirstName());
				st.setString(2, contact.getLastName());
				st.setDate(3, contact.getBirthday());
				st.setString(4, contact.getPhoneNumber());
				st.setString(5, contact.getEmail());
				st.setString(6, contact.getCountry());
				st.setString(7, contact.getCity());
				st.execute();
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new RuntimeException("Fail to save a contact", e);
			}
		} catch (SQLException e1) {
			throw new RuntimeException("A problem with connection", e1);
		}
	}
	
	public List<Contact> getAllContacts() {
		try (Connection conn = getConnection()) {
			try (PreparedStatement st = conn.prepareStatement(GET_ALL_QUERY)) {
				ResultSet res = st.executeQuery();
				ArrayList<Contact> list = new ArrayList<>();
				while(res.next()) {
					Contact contact = new Contact();
					contact.setFirstName(res.getString("first_name"));
					contact.setLastName(res.getString("last_name"));
					contact.setBirthday(res.getDate("birthday"));
					contact.setPhoneNumber(res.getString("phone_number"));
					contact.setEmail(res.getString("email"));
					contact.setCountry(res.getString("country"));
					contact.setCity(res.getString("city"));
					list.add(contact);
				}
				return list;
			} catch (SQLException e) {
				throw new RuntimeException("Fail to get contacts", e);
			}
		} catch (SQLException e1) {
			throw new RuntimeException("A problem with connection", e1);
		}
	}
	
	private void initDataSource() {
		if (dataSource != null) {
			return;
		}
		try (InputStream inputStream =
                getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
			Properties properties = new Properties();
			properties.load(inputStream);
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
		} catch (IOException e) {
			throw new RuntimeException("Couldn't get DB properties", e);
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	
	private void createTable() {
		try (Connection conn = getConnection()) {
			try (PreparedStatement st = conn.prepareStatement(CREATE_TABLE_QUERY)) {
				st.execute();
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new RuntimeException("Fail to create a table", e);
			}
		} catch (SQLException e1) {
			throw new RuntimeException("A problem with connection", e1);
		}
	}
}
