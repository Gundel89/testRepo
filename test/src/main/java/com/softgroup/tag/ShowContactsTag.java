package com.softgroup.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.softgroup.dao.GuestBookDao;
import com.softgroup.entity.Contact;

public class ShowContactsTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private GuestBookDao dao;
	
	public ShowContactsTag() {
		super();
		dao = new GuestBookDao();
	}

	@Override
	public int doStartTag() throws JspException {
		List<Contact> contacts = dao.getAllContacts();
		JspWriter wr = pageContext.getOut();
		
		try {
			wr.println("<tr>" 
					+ "<th>Ім'я</th><th>Прізвище</th>"
					+ "<th>Дата народження</th><th>Номер телефону</th>"
					+ "<th>Електронна пошта</th><th>Країна</th><th>Місто</th>"
					+ "</tr>");
			for (Contact contact : contacts) {
				wr.println("<tr>" 
						+ "<td>" + contact.getFirstName() + "</td>"
						+ "<td>" + contact.getLastName() + "</td>"
						+ "<td>" + contact.getBirthday() + "</td>"
						+ "<td>" + contact.getPhoneNumber() + "</td>"
						+ "<td>" + contact.getEmail() + "</td>"
						+ "<td>" + contact.getCountry() + "</td>"
						+ "<td>" + contact.getCity() + "</td>"
						+ "</tr>");
			}
		} catch (IOException e) {
			throw new RuntimeException("A problem appeared while printing tag's out", e);
		}
		return SKIP_BODY;
	}
}
