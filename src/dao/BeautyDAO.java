package dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import models.Booking;
import models.Category;
import models.Employee;
import models.EmployeeServices;
import models.Image;
import models.Service;
import models.Setting;
import models.User;

public interface BeautyDAO {
	
	public List<Category> getCategories();
	public Category getCategoryById(int id);
	public List<Category> searchCategories(String keyword);
	public void updateCategory(Category category);
	
	public Service getServiceById(int id);
	public List<Service> getServicesByCategoryId(int id);
	public List<Service> getServices();
	public List<Service> searchServices(String keyword);
	public void updateService(Service service);
	
	public List<Employee> getEmployees();
	public List<Employee> getEmployeesByServiceId(int id);
	public List<Employee> searchEmployees(String keyword);

	public void addCategory(Category category);
	public void addService(Service service);
	public void addEmployee(Employee employee);
	public void deleteEmployee(int id);
	public void addEmployeeService(int employeeId, int serviceId);
	public void deleteEmployeeServiceByEmployeeId(int employeeId);
	public void updateEmployee(Employee employee);
	public Employee getEmployeeById(int employeeId);
	public List<Integer> getServicesByEmployeeId(int employeeId);
	
	public void deleteRecord(int id, String tableName);
	
	public void addBooking(Booking booking);
	
	public User getUserById(int id);
	public User login(String email, String password);
	
	public Setting getSettings();
	public void saveSettings(Setting settings);
	
	public List<Time[]> getAvailableHoursByDateAndEmployee(Date date, int employeeId);
	
	public void addGalleryImage(Image image);
	public void deleteImage(int id);
	public int getLastImageId();
	public List<Image> getImages();
	
	public List<Booking> getBookings();
	public List<Booking> getBookings(int employeeId);
	
	
//	public int getBookingsNumber();
//	public int getServicesNumber();
	public int getTotalProfit();
	public int getTotal(String table);
//	public int getLastMonthProfit();
	
}
