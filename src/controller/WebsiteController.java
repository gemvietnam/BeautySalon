package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.Category;
import models.Employee;
import models.Image;
import models.Service;
import models.Setting;


@WebServlet("/website")
public class WebsiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WebsiteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("page");
		String url = "/index.jsp";
		
		setupApplication();

		
		if (action != null) {
			switch (action) {
			case "category":
				String id = request.getParameter("id");
				url = "/jsp/category.jsp?id=" + id;
				getCategoryById(request, response);
				getServicesByCategoryId(request, response);
				break;
			case "employees":
				url = "/jsp/employees.jsp";
				getEmployees(request, response);
				break;
			case "treatments":
				url = "/jsp/categories.jsp";
				getCategories(request, response);
				break;
			case "about":
				url = "/jsp/about.jsp";
				break;
			case "contact":
				url = "/jsp/contact.jsp";
				break;
			case "gallery":
				url = "/jsp/gallery.jsp";
				BeautyDAO beautyDAO = new BeautyDAOImpl();
				List<Image> images = beautyDAO.getImages();
				request.setAttribute("images", images);
				break;
			}
		}
		else {
			getCategories(request, response);
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	private void setupApplication() {
		
		BeautyDAO beautyDAO = new BeautyDAOImpl();
		Setting settings = (Setting) beautyDAO.getSettings();
		
		getServletContext().setAttribute("siteTitle", settings.getSiteTitle());
		getServletContext().setAttribute("siteDescription", settings.getSiteDescription());
		getServletContext().setAttribute("companyName", settings.getCompanyName());
		getServletContext().setAttribute("phone", settings.getPhone());
		getServletContext().setAttribute("email", settings.getEmail());
		getServletContext().setAttribute("vatNumber", settings.getVatNumber());
		getServletContext().setAttribute("address", settings.getAddress());	
		
	}
	
	private void getCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Category> categories = beautyDAO.getCategories();
			request.setAttribute("categories", categories);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void getCategoryById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int categoryId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Category category = beautyDAO.getCategoryById(categoryId);
			request.setAttribute("category", category);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void getServicesByCategoryId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int categoryId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Service> services = beautyDAO.getServicesByCategoryId(categoryId);
			request.setAttribute("services", services);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void getEmployees(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Employee> employees = beautyDAO.getEmployees();
			request.setAttribute("employees", employees);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
}
