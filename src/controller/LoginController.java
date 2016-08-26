package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.User;

/**
* <h1>Login Controller</h1>
* Contains the methods for logging in to the dashboard.
* Redirects to login page if credentials were wrong or user logs out.
*
*/

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String action = request.getParameter("logout");
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		String url;
		request.setAttribute("isError", false);
		HttpSession session = request.getSession();
		
		// GET LOGIN FORM
		if (email == null) {
			url = "/jsp/login.jsp";
			session.setAttribute("isLoggedIn", false);
		}
		else if (action != null && action.equals("logout")) {
			session.invalidate();
			url = "/jsp/login.jsp";
			request.setAttribute("isError", true);
			session.setAttribute("isLoggedIn", false);
			System.out.println("Redirect to login page");
		}
		// POST METHOD
		else {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			User user = beautyDAO.login(email, password);
			System.out.println("We got the user: " + user.getFirstName() + " with id " + user.getId());
			
			// CHECK IF THE CREDENTIALS WERE CORRECT
			if (user.getId() != 0) {
				url = "/admin";
				session.setAttribute("isLoggedIn", true);
				session.setAttribute("user", user);
				System.out.println("Hello admin");
			}
			// IF NOT REDIRECT TO LOGIN PAGE
			else {
				url = "/jsp/login.jsp";
				request.setAttribute("isError", true);
				session.setAttribute("isLoggedIn", false);
				System.out.println("Redirect to login page");
			}
			
		}
		
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
