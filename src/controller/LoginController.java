package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.Setting;
import models.User;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/login.jsp";
		request.setAttribute("error", false);
		System.out.println("Get login page");
		getServletContext().getRequestDispatcher(url).forward(request, response);	
		
//		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/login.jsp";
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		
		BeautyDAO beautyDAO = new BeautyDAOImpl();
		User user = beautyDAO.login(email, password);
		
		System.out.println("We got the user: " + user.getFirstName());
		
		if (user.getFirstName() != null) {
			System.out.println("Login succeded, redirecting to admin");
			url = "/jsp/admin/index.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("isLoggedIn", true);
			session.setAttribute("user", user);
		}
		else {
			System.out.println("Wrong username or password");
			request.setAttribute("error", true);
			HttpSession session = request.getSession();
			session.setAttribute("isLoggedIn", false);
		}
		
		String action = request.getParameter("action");
		
		if (action == "logout") {
			System.out.println("Logout the user");
			HttpSession session = request.getSession();
			session.setAttribute("isLoggedIn", false);
			url = "/jsp/login.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
