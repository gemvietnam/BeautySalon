package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.Category;
import models.Employee;
import models.Image;
import models.Page;
import models.Service;
import models.Setting;


@WebServlet("")
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
		
		
		HttpSession session = request.getSession();
		session.setAttribute("website", "hejka");
		
		setupApplication();
		BeautyDAO beautyDAO = new BeautyDAOImpl();
		
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
				List<Image> images = beautyDAO.getImages();
				request.setAttribute("images", images);
				break;
			default:
				int pageId = Integer.parseInt(request.getParameter("page"));
				Page page = beautyDAO.getPageById(pageId);
				if (page.isPublished()) {
					url = "/jsp/custom-page.jsp";
					request.setAttribute("customPage", page);
					getPageData(request, response);
				}
				else {
					url = "/jsp/404.jsp";
				}
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
		List<String[]> pages = beautyDAO.getMenu();
		
		getServletContext().setAttribute("siteTitle", settings.getSiteTitle());
		getServletContext().setAttribute("siteDescription", settings.getSiteDescription());
		getServletContext().setAttribute("companyName", settings.getCompanyName());
		getServletContext().setAttribute("phone", settings.getPhone());
		getServletContext().setAttribute("email", settings.getEmail());
		getServletContext().setAttribute("vatNumber", settings.getVatNumber());
		getServletContext().setAttribute("address", settings.getAddress());	
		getServletContext().setAttribute("facebook", settings.getFacebook());	
		getServletContext().setAttribute("twitter", settings.getTwitter());	
		getServletContext().setAttribute("instagram", settings.getInstagram());	
		getServletContext().setAttribute("pinterest", settings.getPinterest());	
		getServletContext().setAttribute("googlePlus", settings.getGooglePlus());	
		getServletContext().setAttribute("menu", pages);
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
	
	private void getPageData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			int pageId = Integer.parseInt(request.getParameter("page"));
			Page page = beautyDAO.getPageById(pageId);
			
			if (page.getTemplate().equals("employees")) {
				List<Employee> employees = beautyDAO.getEmployees();
				request.setAttribute("employees", employees);
			}
			else if (page.getTemplate().equals("gallery")) {
				List<Image> images = beautyDAO.getImages();
				request.setAttribute("images", images);
			}
			else if (page.getTemplate().equals("treatments")) {
				List<Category> categories = beautyDAO.getCategories();
				request.setAttribute("categories", categories);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	public static String Translate(String inputText) {	
		LanguageTranslation service = new LanguageTranslation();
	    service.setUsernameAndPassword("552acf01-fdfd-45de-8353-ad7a965c24bd", "7fG1WKLcg1eP");
	    TranslationResult translationResult = service.translate(inputText, Language.ENGLISH, Language.SPANISH).execute();
	    return translationResult.getFirstTranslation();
	}
	
}
