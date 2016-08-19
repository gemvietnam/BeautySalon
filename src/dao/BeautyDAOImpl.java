package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import models.Booking;
import models.Category;
import models.Employee;
import models.Image;
import models.Service;
import models.Setting;
import models.User;

public class BeautyDAOImpl implements BeautyDAO {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/beautySalonDB?useSSL=false",
				"root", "root");
	}

	private void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		} catch (SQLException ex) {
		}
	}
	
	public List<Category> getCategories() {
		
		List<Category> result = new ArrayList<Category>();
		String sql = "select * from Categories";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				category.setDescription(resultSet.getString("description"));
				category.setPicture(resultSet.getString("picture"));
				result.add(category);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public Category getCategoryById(int id) {
			
			Category category = new Category();
			Connection connection = null;
			try {
				connection = getConnection();
				
				PreparedStatement statement = connection.prepareStatement(
						"select * from Categories where id=?",
						Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					category.setId(resultSet.getInt("id"));
					category.setName(resultSet.getString("name"));
					category.setDescription(resultSet.getString("description"));
					category.setPicture(resultSet.getString("picture"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				closeConnection(connection);
			}
			return category;		
	}

	public List<Category> searchCategories(String keyword) {		
		String betterKeyword = "%" + keyword + "%";
		System.out.println("We are searching for tis: " + betterKeyword);	
		List<Category> result = new ArrayList<Category>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from Categories where name like ? or description like ?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, betterKeyword);
			statement.setString(2, betterKeyword);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				category.setDescription(resultSet.getString("description"));
				category.setPicture(resultSet.getString("picture"));
				result.add(category);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}	
	
	public void addCategory(Category category) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Categories (name, description, picture) values (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			statement.setString(3, category.getPicture());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				category.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void updateCategory(Category category) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Categories set name=?, description=?, picture=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			statement.setString(3, category.getPicture());
			statement.setInt(4, category.getId());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				category.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public List<Service> getServices() {
		
		List<Service> result = new ArrayList<Service>();
		String sql = "select * from Services inner join Categories on Services.categoryId=Categories.id";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Service service = new Service();
				service.setId(resultSet.getInt("id"));
				service.setName(resultSet.getString("name"));
				service.setDescription(resultSet.getString("description"));
				service.setPrice(resultSet.getInt("price"));
				service.setTime(resultSet.getTime("time"));
				service.setCategoryName(resultSet.getString("Categories.name"));
				result.add(service);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public Service getServiceById(int id) {
			
			Service service = new Service();
			Connection connection = null;
			try {
				connection = getConnection();
				
				PreparedStatement statement = connection.prepareStatement(
						"select * from Services where id=?",
						Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					service.setId(resultSet.getInt("id"));
					service.setName(resultSet.getString("name"));
					service.setPrice(resultSet.getInt("price"));
					service.setDescription(resultSet.getString("description"));
					service.setTime(resultSet.getTime("time"));
					service.setCategoryId(resultSet.getInt("categoryId"));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				closeConnection(connection);
			}
			return service;		
	}

	public List<Service> searchServices(String keyword) {		
		String betterKeyword = "%" + keyword + "%";
		System.out.println("We are searching for tis: " + betterKeyword);	
		List<Service> result = new ArrayList<Service>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from Services inner join Categories on Services.categoryId=Categories.id where Services.name like ? or Categories.name like ? or Services.description like ?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, betterKeyword);
			statement.setString(2, betterKeyword);
			statement.setString(3, betterKeyword);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Service service = new Service();
				service.setId(resultSet.getInt("id"));
				service.setName(resultSet.getString("name"));
				service.setDescription(resultSet.getString("description"));
				service.setPrice(resultSet.getInt("price"));
				service.setTime(resultSet.getTime("time"));
				service.setCategoryName(resultSet.getString("Categories.name"));
				result.add(service);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public void addService(Service service) {		
		Connection connection = null;
		try {
			connection = getConnection();
			
			System.out.println("Jak wyglada nasz czas teraz: " + service.getTime());
			
			PreparedStatement statement = connection.prepareStatement(
					"insert into Services (name, price, description, categoryId, time) values (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, service.getName());
			statement.setInt(2, service.getPrice());
			statement.setString(3, service.getDescription());
			statement.setInt(4, service.getCategoryId());
			statement.setTime(5, service.getTime());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				service.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void updateService(Service service) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Services set name=?, price=?, time=?, description=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, service.getName());
			statement.setInt(2, service.getPrice());
			statement.setTime(3, service.getTime());
			statement.setString(4, service.getDescription());
			statement.setInt(5, service.getId());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				service.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public List<Service> getServicesByCategoryId(int id) {
		
		List<Service> result = new ArrayList<Service>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from Services where categoryId=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Service service = new Service();
				service.setId(resultSet.getInt("id"));
				service.setName(resultSet.getString("name"));
				service.setPrice(resultSet.getInt("price"));
				service.setDescription(resultSet.getString("description"));
				service.setTime(resultSet.getTime("time"));
				result.add(service);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public Setting getSettings() {
		
		Setting setting = new Setting();
		Connection connection = null;
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement(
					"select * from Settings where id=1",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				setting.setSiteTitle(resultSet.getString("siteTitle"));
				setting.setSiteDescription(resultSet.getString("siteDescription"));
				setting.setCompanyName(resultSet.getString("companyName"));
				setting.setVatNumber(resultSet.getString("vatNumber"));
				setting.setAddress(resultSet.getString("address"));
				setting.setPhone(resultSet.getString("phone"));
				setting.setEmail(resultSet.getString("email"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return setting;		
	}	
	
	public void saveSettings(Setting settings) {
		
		System.out.println("INSIDE SAVE SETTINGS FUNCTION");
		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Settings set siteTitle=?, siteDescription=?, companyName=?, vatNumber=?, address=?, phone=?, email=? where id=1",
					Statement.RETURN_GENERATED_KEYS);
			System.out.println("Update sitetitle with: " + settings.getSiteTitle());
			statement.setString(1, settings.getSiteTitle());
			statement.setString(2, settings.getSiteDescription());
			statement.setString(3, settings.getCompanyName());
			statement.setString(4, settings.getVatNumber());
			statement.setString(5, settings.getAddress());
			statement.setString(6, settings.getPhone());
			statement.setString(7, settings.getEmail());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				settings.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}	
	}		
	
	public List<Employee> getEmployees() {
		
		List<Employee> result = new ArrayList<Employee>();
		String sql = "select * from Employees";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setEmail(resultSet.getString("email"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				employee.setTitle(resultSet.getString("title"));
				employee.setDescription(resultSet.getString("description"));
				employee.setProfilePicture(resultSet.getString("profilePicture"));
				result.add(employee);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	
	public List<Booking> getBookings() {
		
		List<Booking> result = new ArrayList<Booking>();
		String sql = "select * from Bookings inner join Services on Bookings.serviceId=Services.id inner join Employees on Bookings.employeeId=Employees.id";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Booking booking = new Booking();
				booking.setId(resultSet.getInt("Bookings.id"));
				booking.setDate(resultSet.getDate("date"));
				booking.setHour(resultSet.getTime("hour"));
				booking.setFirstName(resultSet.getString("firstName"));
				booking.setLastName(resultSet.getString("lastName"));
				booking.setServiceName(resultSet.getString("Services.name"));
				booking.setServiceDuration(resultSet.getTime("Services.time"));
				booking.setServicePrice(resultSet.getInt("Services.price"));
				booking.setEmployeeName(resultSet.getString("Employees.firstName") + " " + resultSet.getString("Employees.lastName"));
				
				result.add(booking);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	
	public Employee getEmployeeById(int id) {
		
		Employee employee = new Employee();
		Connection connection = null;
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement(
					"select * from Employees where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setEmail(resultSet.getString("email"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				employee.setTitle(resultSet.getString("title"));
				employee.setDescription(resultSet.getString("description"));
				employee.setProfilePicture(resultSet.getString("profilePicture"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return employee;		
}	
	
	
	public List<Integer> getServicesByEmployeeId(int employeeId) {		
		Connection connection = null;
		List<Integer> result = new ArrayList<Integer>();
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from EmployeeServices where employeeId=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, employeeId);
			statement.execute();
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				result.add(resultSet.getInt("serviceId"));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}	
	
	
	public List<Employee> searchEmployees(String keyword) {		
		String betterKeyword = "%" + keyword + "%";
		List<Employee> result = new ArrayList<Employee>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from Employees where firstName like ? or lastName like ? or title like ? or description like ?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, betterKeyword);
			statement.setString(2, betterKeyword);
			statement.setString(3, betterKeyword);
			statement.setString(4, betterKeyword);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setEmail(resultSet.getString("email"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				employee.setTitle(resultSet.getString("title"));
				employee.setDescription(resultSet.getString("description"));
				employee.setProfilePicture(resultSet.getString("profilePicture"));
				result.add(employee);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}	
	
	
	public List<Employee> getEmployeesByServiceId(int id) {
		
		List<Employee> result = new ArrayList<Employee>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from EmployeeServices inner join Employees on Employees.id = employeeId where serviceId=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				result.add(employee);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}	
	
	
	public void addEmployee(Employee employee) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Employees (email, firstName, lastName, title, description, profilePicture) values (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, employee.getEmail());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getTitle());
			statement.setString(5, employee.getDescription());
			statement.setString(6, employee.getProfilePicture());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				employee.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void updateEmployee(Employee employee) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Employees set email=?, firstName=?, lastName=?, title=?, description=?, profilePicture=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, employee.getEmail());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getTitle());
			statement.setString(5, employee.getDescription());
			statement.setString(6, employee.getProfilePicture());
			statement.setInt(7, employee.getId());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				employee.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

		
	
//	public boolean login(String email, String password) {
//		
//		boolean result = false;
//		Connection connection = null;
//		try {
//			connection = getConnection();
//			PreparedStatement statement = connection.prepareStatement(
//					"select * from Users where email=? and password=?",
//					Statement.RETURN_GENERATED_KEYS);
//			statement.setString(1, email);
//			statement.setString(2, password);
//			ResultSet resultSet = statement.executeQuery();
//			
//			while (resultSet.next()) {
//				result = true;
//			}
//			
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			closeConnection(connection);
//		}
//		return result;
//	}		
	
	
	public void addEmployeeService(int employeeId, int serviceId) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into EmployeeServices (employeeId, serviceId) values (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, employeeId);
			statement.setInt(2, serviceId);
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
//				employee.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}	
	
	public void deleteEmployeeServiceByEmployeeId(int employeeId) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"delete from EmployeeServices where employeeId=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, employeeId);
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
//				employee.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}	
	
	public void deleteRecord(int id, String tableName) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"delete from " + tableName + " where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			statement.execute();
			
			System.out.println("Sucessfuly deleted the record.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}		

	public void addBooking(Booking booking) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Bookings (date, hour, employeeId, serviceId, firstName, lastName, email, phone) values (?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, booking.getDate());
			statement.setTime(2, booking.getHour());
			statement.setInt(3, booking.getEmployeeId());
			statement.setInt(4, booking.getServiceId());
			statement.setString(5, booking.getFirstName());
			statement.setString(6, booking.getLastName());
			statement.setString(7, booking.getEmail());
			statement.setString(8, booking.getPhone());
			statement.execute();
			
			System.out.println("Statement executed");
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				booking.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	
	public List<Time[]> getAvailableHoursByDateAndEmployee(Date date, int employeeId) {		
		
		System.out.println("Inside the function in DAO " + date + " employee id " + employeeId);
		List<Time[]> result = new ArrayList<Time[]>();

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from Bookings inner join Services on Bookings.serviceId=Services.id where date=? and employeeId=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, date);
			statement.setInt(2, employeeId);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("Found booking at time: " + resultSet.getTime("hour"));
				System.out.println("For service: " + resultSet.getString("services.name"));
				System.out.println("With duration of: " + resultSet.getTime("services.time"));
				
				Time[] record = {resultSet.getTime("hour"), resultSet.getTime("services.time")};
				result.add(record);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public void addGalleryImage(Image image) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Images (path) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, image.getPath());

			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				image.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	
	public void deleteImage(int id) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"delete from Images where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			statement.execute();
			
			System.out.println("Sucessfuly deleted the image.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}	
	
	public List<Image> getImages() {
		
		List<Image> result = new ArrayList<Image>();
		String sql = "select * from Images ORDER BY id DESC";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Image image = new Image();
				image.setId(resultSet.getInt("id"));
				image.setPath(resultSet.getString("path"));
				result.add(image);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public int getLastImageId() {
		int result = 0;
		String sql = "SELECT id FROM Images ORDER BY id DESC LIMIT 1;";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getInt("id");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	//	public boolean login(String email, String password) {
	//		
	//		boolean result = false;
	//		Connection connection = null;
	//		try {
	//			connection = getConnection();
	//			PreparedStatement statement = connection.prepareStatement(
	//					"select * from Users where email=? and password=?",
	//					Statement.RETURN_GENERATED_KEYS);
	//			statement.setString(1, email);
	//			statement.setString(2, password);
	//			ResultSet resultSet = statement.executeQuery();
	//			
	//			while (resultSet.next()) {
	//				result = true;
	//			}
	//			
	//		} catch (SQLException ex) {
	//			ex.printStackTrace();
	//		} finally {
	//			closeConnection(connection);
	//		}
	//		return result;
	//	}		
		
		
		public User login(String email, String password) {
			
			User user = new User();
			Connection connection = null;
			try {
				connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"select * from Users where email=? and password=?",
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, email);
				statement.setString(2, password);
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					user.setEmail(resultSet.getString("email"));
					user.setFirstName(resultSet.getString("firstName"));
					user.setLastName(resultSet.getString("lastName"));
					System.out.println("Got the user: " + user.getEmail() + " " + user.getFirstName() + " " + user.getLastName());
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				closeConnection(connection);
			}
			return user;
		}

		public User getUserById(int id) {
			
			User user = new User();
			Connection connection = null;
			try {
				connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"select * from Users where id=?",
						Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setFirstName(resultSet.getString("firstName"));
					user.setLastName(resultSet.getString("lastName"));
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				closeConnection(connection);
			}
			return user;
		}		
	
}