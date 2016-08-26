package dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import models.Booking;
import models.Category;
import models.Employee;
import models.EmployeeServices;
import models.Image;
import models.Page;
import models.Service;
import models.Setting;
import models.User;

public interface BeautyDAO {
	
	
	// OPERATIONS ON CATEGORIES
	public List<Category> getCategories();
	public Category getCategoryById(int id);
	public List<Category> searchCategories(String keyword);
	public void addCategory(Category category);
	public void updateCategory(Category category);

	// OPERATIONS ON SERVICES
	public List<Service> getServices();
	public Service getServiceById(int id);
	public List<Service> searchServices(String keyword);
	public void addService(Service service);
	public void updateService(Service service);
	public void deleteService(int id);
	public List<Service> getServicesByCategoryId(int id);
	public boolean serviceHasEmployees(int id);

	// OPERATIONS ON EMPLOYEES
	public List<Employee> getEmployees();
	public List<Employee> getEmployeesByServiceId(int id);
	public List<Employee> searchEmployees(String keyword);
	public void addEmployee(Employee employee);
	public void deleteEmployee(int id);
	public void addEmployeeService(int employeeId, int serviceId);
	public void deleteEmployeeServiceByEmployeeId(int employeeId);
	public void updateEmployee(Employee employee);
	public Employee getEmployeeById(int employeeId);
	public List<Integer> getServicesByEmployeeId(int employeeId);
	
	// OPERATIONS ON BOOKINGS
	public List<Booking> getBookings();
	public List<Booking> getBookings(int employeeId);
	public Booking getBookingById(int bookingId);	
	public Booking addBooking(Booking booking);
	public void cancelBooking(int bookingId);
	public List<Time[]> getAvailableHoursByDateAndEmployee(Date date, int employeeId);
	public void deleteRecord(int id, String tableName);
	
	// LOGIN OPERATIONS
	public User getUserById(int id);
	public User login(String email, String password);
	
	// OPERATIONS ON SETTINGS
	public Setting getSettings();
	public void saveSettings(Setting settings);
	public List<String[]> getMenu();
	
	// OPERATIONS ON GALLERY
	public List<Image> getImages();
	public void addGalleryImage(Image image);
	public void deleteImage(int id);
	public int getLastImageId();
	
	// DASHBOARD OPERATIONS
	public int getTotalProfit();
	public int getTotal(String table);

	// OPERATIONS ON PAGES
	public List<Page> getPages();
	public void addPage(Page page);
	public void updatePage(Page page);
	public void deletePage(int pageId);
	public Page getPageById(int id);
	public void updatePageOrder(int pageId, int order);
	
}
