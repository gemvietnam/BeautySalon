package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;
import models.Booking;
import models.Category;
import models.Employee;
import models.Image;
import models.Page;
import models.Service;
import models.Setting;


@WebServlet("/admin")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DashboardServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("page");
		String url = "/jsp/admin/index.jsp";	
		
		System.out.println("Check if logged in");
		
		HttpSession session = request.getSession();
		boolean isLoggedIn = false;
		
		if (session.getAttribute("isLoggedIn") != null) {
			isLoggedIn = (boolean) session.getAttribute("isLoggedIn");	
		}
		
		System.out.println("Our booolean is: " + session.getAttribute("isLoggedIn"));
		
		if (isLoggedIn == false) {
			System.out.println("The user is not logged in, redirect to login screen");
			request.setAttribute("error", false);
			url = "/jsp/login.jsp";
		}
		else {
			System.out.println("The user is logged in");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			if (action != null) {
				switch (action) {
				case "categories":
					String type = (String) request.getParameter("type");
					System.out.println("The type of category action is: " + type);
					if (type != null) {
						switch (type) {
						case "add":
							System.out.println("Add category");
							addCategory(request, response);
							break;
						case "update":
							System.out.println("Update category");
							updateCategory(request, response);
							break;
						}
					}
					url = "/jsp/admin/categories.jsp";
					getCategories(request, response);
					break;
				case "searchCategories":
					url = "/jsp/admin/categories.jsp";
					searchCategories(request, response);
					break;
				case "addCategory":
					url = "/jsp/admin/add-category.jsp";
					break;
				case "editCategory":
					url = "/jsp/admin/edit-category.jsp";
					getCategoryById(request, response);
					break;
				case "delete":
					System.out.println("Wer are deleting the record now");
					deleteRecord(request, response);
					String page = request.getParameter("table");
					url = "/jsp/admin/" + page.toLowerCase() + ".jsp";
					switch (page) {
					case "Services":
						getServices(request, response);
						break;
					case "Categories":
						getCategories(request, response);
						break;
					case "Employees":
						getEmployees(request, response);
						break;
					}
					break;	
				case "deleteEmployee":
					url = "/jsp/admin/employees.jsp";
					int employeeId = Integer.parseInt(request.getParameter("id"));
					beautyDAO.deleteEmployee(employeeId);
					getEmployees(request, response);
					break;
				case "treatments":
					String typeAction = (String) request.getParameter("type");
					System.out.println("The type of service action is: " + typeAction);
					if (typeAction != null) {
						switch (typeAction) {
						case "add":
							System.out.println("Add service");
							addService(request, response);
							break;
						case "update":
							System.out.println("Update service");
							updateService(request, response);
							break;
						}
					}
					url = "/jsp/admin/services.jsp";
					getServices(request, response);
					break;
				case "searchTreatments":
					url = "/jsp/admin/services.jsp";
					searchServices(request, response);
					break;
				case "addTreatment":
					url = "/jsp/admin/add-service.jsp";
					getCategories(request, response);
					break;
				case "editTreatment":
					url = "/jsp/admin/edit-service.jsp";
					getServiceById(request, response);
					getCategories(request, response);
					break;
				case "deleteService":
					int serviceId = Integer.parseInt(request.getParameter("id"));
					beautyDAO.deleteService(serviceId);
					url = "/jsp/admin/services.jsp";
					getServices(request, response);
					break;
				case "bookings":
					url = "/jsp/admin/bookings.jsp";
					getBookings(request, response);
					break;
				case "cancelBooking":
					int bookingId = Integer.parseInt(request.getParameter("id"));
					beautyDAO.cancelBooking(bookingId);
					url = "/jsp/admin/bookings.jsp";
					getBookings(request, response);
					break;
				case "pages":
					String pageType = (String) request.getParameter("type");
					System.out.println("The type of category action is: " + pageType);
					if (pageType != null) {
						switch (pageType) {
						case "add":
							System.out.println("Add page");
							addPage(request, response);
							break;
						case "update":
							System.out.println("Update page");
							updatePage(request, response);
							break;
						}
					}
					url = "/jsp/admin/pages.jsp";
					getPages(request, response);
					break;
				case "addPage":
					url = "/jsp/admin/add-page.jsp";
					break;
				case "editPage":
					url = "/jsp/admin/edit-page.jsp";
					getPageById(request, response);
					break;
				case "employees":
					String typeEmployee = (String) request.getParameter("type");
					System.out.println("The type of service action is: " + typeEmployee);
					if (typeEmployee != null) {
						switch (typeEmployee) {
						case "add":
							System.out.println("Add employee");
							addEmployee(request, response);
							break;
						case "update":
							System.out.println("Update employee");
							updateEmployee(request, response);
							break;
						}
					}
					url = "/jsp/admin/employees.jsp";
					getEmployees(request, response);
					break;
				case "searchEmployees":
					url = "/jsp/admin/employees.jsp";
					searchEmployees(request, response);
					break;
				case "addEmployee":
					getServices(request, response);
					url = "/jsp/admin/add-employee.jsp";
					break;
				case "editEmployee":
					url = "/jsp/admin/edit-employee.jsp";
					getEmployeeById(request, response);
					getServices(request, response);
					getServicesByEmployeeId(request, response);
					break;
				case "settings":
					url = "/jsp/admin/settings.jsp";
					getSettings(request, response);
					break;
				case "saveSettings":
					url = "/jsp/admin/settings.jsp";
					System.out.println("Attempting to save settings");
					saveSettings(request, response);
					System.out.println("Getting the settings back");
					getSettings(request, response);
					break;
				case "schedule":
					url = "/jsp/admin/schedule.jsp";
					getEmployees(request, response);
					if (request.getParameter("employeeId") != null) {
						getSchedule(request, response);
						url = "/jsp/admin/render-schedule.jsp";
					}
					break;
				case "images":
					url = "/jsp/admin/fileupload.jsp";
					List<Image> images = beautyDAO.getImages();
					request.setAttribute("images", images);
					String akcja = (String) request.getParameter("action");
					String delete = (String) request.getParameter("delete");
					System.out.println("ackaj is: " + akcja);
					
					if (akcja != null) {
						System.out.println("Add image to database");
						addImageToDb(request, response);
						System.out.println("IMAGE ADDED");
						List<Image> imagesUpdated = beautyDAO.getImages();
						request.setAttribute("images", imagesUpdated);
					}
					
					if (delete != null) {
						System.out.println("Delete the image from database");
						deleteImage(request, response);
					}
					
					break;
				}
			}
			else {
				getDashboardStats(request, response);
				getCategories(request, response);
			}			
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	private void getCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Trying to get the categories");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Category> categories = beautyDAO.getCategories();
			request.setAttribute("categories", categories);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void getSchedule(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Trying to get the schedule");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			List<Booking> bookings = beautyDAO.getBookings(employeeId);
			request.setAttribute("bookings", bookings);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void getDashboardStats(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			int totalProfit = beautyDAO.getTotalProfit();
			request.setAttribute("totalProfit", totalProfit);
			int totalCategories = beautyDAO.getTotal("Categories");
			request.setAttribute("totalCategories", totalCategories);
			int totalEmployees = beautyDAO.getTotal("Employees");
			request.setAttribute("totalEmployees", totalEmployees);
			int totalServices = beautyDAO.getTotal("Services");
			request.setAttribute("totalServices", totalServices);
			int totalBookings = beautyDAO.getTotal("Bookings");
			request.setAttribute("totalBookings", totalBookings);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	private void addImageToDb(HttpServletRequest request,
			HttpServletResponse response) {
		// Commons file upload classes are specifically instantiated
				FileItemFactory factory = new DiskFileItemFactory();
			 
				ServletFileUpload upload = new ServletFileUpload(factory);
				ServletOutputStream out = null;
				
				System.out.println("Attempting to try to upload the file.");
			 
				try {
					// Parse the incoming HTTP request
					// Commons takes over incoming request at this point
					// Get an iterator for all the data that was sent
					List items = upload.parseRequest(request);
					Iterator iter = items.iterator();
			 
//					// Set a response content type
//					response.setContentType("text/html");
//			 
//					// Setup the output stream for the return XML data
//					out = response.getOutputStream();
			 
					// Iterate through the incoming request data
					while (iter.hasNext()) {
						// Get the current item in the iteration
						FileItem item = (FileItem) iter.next();
			 
						// If the current item is an HTML form field
						if (item.isFormField()) {
							// Return an XML node with the field name and value
//							out.println("this is a form data " + item.getFieldName() + "<br>");
//			 
							// If the current item is file data
						} else {
							// Specify where on disk to write the file
							// Using a servlet init param to specify location on disk
							// Write the file data to disk
							// TODO: Place restrictions on upload data
							
							BeautyDAO beautyDAO = new BeautyDAOImpl();
							int lastId = beautyDAO.getLastImageId();
							int thisId = lastId + 1;
							
							String uploadPath = getServletContext().getRealPath("") + "uploads";
							System.out.println("UPLOAD PATH IS: " + uploadPath);
							String fileName = new File(item.getName()).getName();
							fileName = String.valueOf(thisId) + fileName;
							String filePath = uploadPath + File.separator + fileName;
							System.out.println("FILE PATH IS: " + filePath);
							File disk = new File(filePath);
							item.write(disk);
			 
							Image uploadedImage = new Image();
							uploadedImage.setPath(fileName);
							
							beautyDAO.addGalleryImage(uploadedImage);
						}
					}
			 
					// Close off the response XML data and stream
			 
//					out.close();
					// Rudimentary handling of any exceptions
					// TODO: Something useful if an error occurs
				} catch (FileUploadException fue) {
					fue.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	

	private void deleteImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String idString = request.getParameter("imageId");
			int id = Integer.parseInt(idString);
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.deleteImage(id);

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
	
	private void getServiceById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int serviceId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Service service = beautyDAO.getServiceById(serviceId);
			request.setAttribute("service", service);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getServices(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Service> services = beautyDAO.getServices();
			request.setAttribute("services", services);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void searchServices(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Service> services = beautyDAO.searchServices(keyword);
			request.setAttribute("services", services);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void searchCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Category> categories = beautyDAO.searchCategories(keyword);
			request.setAttribute("categories", categories);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void searchEmployees(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyword = request.getParameter("keyword");
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Employee> employees = beautyDAO.searchEmployees(keyword);
			request.setAttribute("employees", employees);

		} catch (Exception e) {
			System.out.println(e);
		}
	}		
	
	private void getSettings(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Setting setting = (Setting) beautyDAO.getSettings();
			request.setAttribute("settings", setting);
			getServletContext().setAttribute("settings", setting);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void saveSettings(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Setting s = new Setting();
			s.setSiteTitle(request.getParameter("siteTitle"));
			s.setSiteDescription(request.getParameter("siteDescription"));
			s.setCompanyName(request.getParameter("companyName"));
			s.setAddress(request.getParameter("address"));
			s.setVatNumber(request.getParameter("vatNumber"));
			s.setPhone(request.getParameter("phone"));
			s.setEmail(request.getParameter("email"));
			s.setFacebook(request.getParameter("facebook"));
			s.setTwitter(request.getParameter("twitter"));
			s.setGooglePlus(request.getParameter("googlePlus"));
			s.setInstagram(request.getParameter("instagram"));
			s.setPinterest(request.getParameter("pinterest"));
			
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.saveSettings(s);
//			request.setAttribute("settings", setting);
//			getServletContext().setAttribute("settings", setting);
			
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
	
	private void getServicesByEmployeeId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int employeeId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Integer> serviceIds = beautyDAO.getServicesByEmployeeId(employeeId);
			request.setAttribute("serviceIds", serviceIds);

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

	
	private void getBookings(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Booking> bookings = beautyDAO.getBookings();
			request.setAttribute("bookings", bookings);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getPages(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			List<Page> pages = beautyDAO.getPages();
			request.setAttribute("pages", pages);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getEmployeeById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int employeeId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Employee employee = beautyDAO.getEmployeeById(employeeId);
			request.setAttribute("employee", employee);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Category c = new Category();
			c.setName(request.getParameter("name"));
			c.setDescription(request.getParameter("description"));
			c.setPicture(request.getParameter("picture"));
			
			System.out.println("Trying to add the category");
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addCategory(c);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void updateCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Category c = new Category();
			c.setName(request.getParameter("name"));
			c.setDescription(request.getParameter("description"));
			c.setPicture(request.getParameter("picture"));
			c.setId(Integer.parseInt(request.getParameter("id")));
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updateCategory(c);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	private void addPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Page p = new Page();
			p.setTitle(request.getParameter("title"));
			p.setSlug(request.getParameter("slug"));
			p.setContent(request.getParameter("content"));
			p.setIsPublished(Integer.parseInt(request.getParameter("isPublished")));
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addPage(p);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Page p = new Page();
			p.setId(Integer.parseInt(request.getParameter("id")));
			p.setTitle(request.getParameter("title"));
			p.setSlug(request.getParameter("slug"));
			p.setContent(request.getParameter("content"));
			p.setIsPublished(Integer.parseInt(request.getParameter("isPublished")));
			
			System.out.println("Trying to update the page");
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updatePage(p);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void getPageById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int pageId = Integer.parseInt(id);
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			Page page = beautyDAO.getPageById(pageId);
			request.setAttribute("page", page);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	
	private void updateService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Service s = new Service();
			s.setId(Integer.parseInt(request.getParameter("id")));
			s.setName(request.getParameter("name"));
			s.setDescription(request.getParameter("description"));
			String price = request.getParameter("price");
			int priceInt = Integer.parseInt(price);
			s.setPrice(priceInt);
			String catId = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(catId);
			s.setCategoryId(categoryId);
			String timeString = request.getParameter("time");
			System.out.println("Nasz czas teraz: " + timeString);
			String betterString = timeString + ":00";
			System.out.println("Better string: " + betterString);
			Time mytime = Time.valueOf(betterString);
			System.out.println("Final czas: " + mytime);
			s.setTime(mytime);
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updateService(s);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	
	private void addService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Service s = new Service();
			s.setName(request.getParameter("name"));
			s.setDescription(request.getParameter("description"));
			String price = request.getParameter("price");
			int priceInt = Integer.parseInt(price);
			s.setPrice(priceInt);
			String catId = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(catId);
			s.setCategoryId(categoryId);
			String timeString = request.getParameter("time");
			String betterString = timeString + ":00";
			Time mytime = Time.valueOf(betterString);
			s.setTime(mytime);
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addService(s);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void addEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Employee e = new Employee();
			e.setFirstName(request.getParameter("firstName"));
			e.setLastName(request.getParameter("lastName"));
			e.setEmail(request.getParameter("email"));
			e.setTitle(request.getParameter("title"));
			e.setDescription(request.getParameter("description"));
			e.setProfilePicture(request.getParameter("profilePicture"));
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.addEmployee(e);
			
			System.out.println("New created employee ID is: " + e.getId());
			int employeeId = e.getId();
			
			String[] ids = request.getParameterValues("service");
		    for (String id:ids) {
		    	System.out.println("Selected ID is: " + id);
		    	int serviceId = Integer.parseInt(id);
				beautyDAO.addEmployeeService(employeeId, serviceId);
		    }

		} catch (Exception e) {
			System.out.println(e);
		}
	}	
	
	private void updateEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Employee e = new Employee();
			e.setFirstName(request.getParameter("firstName"));
			e.setLastName(request.getParameter("lastName"));
			e.setEmail(request.getParameter("email"));
			e.setTitle(request.getParameter("title"));
			e.setDescription(request.getParameter("description"));
			e.setProfilePicture(request.getParameter("profilePicture"));
			e.setId(Integer.parseInt(request.getParameter("id")));
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.updateEmployee(e);
			
			int employeeId = e.getId();
			beautyDAO.deleteEmployeeServiceByEmployeeId(employeeId);
			
			String[] ids = request.getParameterValues("service");
		    for (String id:ids) {
		    	System.out.println("Selected ID is: " + id);
		    	int serviceId = Integer.parseInt(id);
				beautyDAO.addEmployeeService(employeeId, serviceId);
		    }
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}		
	
	private void deleteRecord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			
			String tableName = request.getParameter("table");
			
			BeautyDAO beautyDAO = new BeautyDAOImpl();
			beautyDAO.deleteRecord(id, tableName);

		} catch (Exception e) {
			System.out.println(e);
		}
	}	


}
