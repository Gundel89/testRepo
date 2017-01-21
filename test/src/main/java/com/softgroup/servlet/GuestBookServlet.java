package com.softgroup.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softgroup.dao.GuestBookDao;
import com.softgroup.entity.Contact;

public class GuestBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private GuestBookDao dao;
	
	public GuestBookServlet() {
		super();
		dao = new GuestBookDao();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		Contact contact = new Contact();
		contact.setFirstName(req.getParameter("firstName"));
		contact.setLastName(req.getParameter("lastName"));
		contact.setBirthday(Date.valueOf(req.getParameter("birthday")));
		contact.setPhoneNumber(req.getParameter("number"));
		contact.setEmail(req.getParameter("email"));
		contact.setCountry(req.getParameter("country"));
		contact.setCity(req.getParameter("city") + " " + req.getParameter("district"));
		try {
			dao.save(contact);
			req.setAttribute("success", "Контакт успішно збережено");
		} catch (Exception e) {
			req.setAttribute("error", "Нажаль не вдалося зберегти контакт");
		}
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, res);
	}
}
