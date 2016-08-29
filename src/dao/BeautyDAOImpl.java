package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import models.Booking;
import models.Category;
import models.Employee;
import models.Image;
import models.Page;
import models.Service;
import models.Setting;
import models.User;

/**
* <h1>BeautyDAOImpl</h1>
* Connects with the database, provides data access object for the whole application.
* All the important data operations are performed here.
*
*/

public class BeautyDAOImpl implements BeautyDAO {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/ad_2902760f7e32034?user=b3d2a5634c6c8f&password=38f962a7");
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
		String sql = "select * from Categories inner join Users on Categories.author=Users.id";
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
				category.setAuthor(resultSet.getInt("author"));
				category.setAuthorName(resultSet.getString("Users.firstName") + " " + resultSet.getString("Users.lastName"));
				category.setLastUpdated(resultSet.getTimestamp("lastUpdated"));
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
					"insert into Categories (name, description, picture, author, lastUpdated) values (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			statement.setString(3, category.getPicture());
			statement.setInt(4, category.getAuthor());
			statement.setTimestamp(5, category.getLastUpdated());
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
					"update Categories set name=?, description=?, picture=?, author=?, lastUpdated=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, category.getName());
			statement.setString(2, category.getDescription());
			statement.setString(3, category.getPicture());
			statement.setInt(4, category.getAuthor());
			statement.setTimestamp(5, category.getLastUpdated());
			statement.setInt(6, category.getId());
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

	public List<Page> getPages() {
		
		List<Page> result = new ArrayList<Page>();
		String sql = "select * from Pages inner join Users on Pages.author=Users.id";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Page page = new Page();
				page.setId(resultSet.getInt("Pages.id"));
				Timestamp dateCreated = new Timestamp(resultSet.getTimestamp("created").getTime());
				page.setCreated(dateCreated);
				page.setSlug(resultSet.getString("slug"));
				page.setTitle(resultSet.getString("title"));
				page.setContent(resultSet.getString("content"));
				page.setIsPublished(resultSet.getInt("isPublished"));
				page.setAuthor(resultSet.getInt("Users.id"));
				page.setAuthorName(resultSet.getString("Users.firstName") + " " + resultSet.getString("Users.lastName"));
				result.add(page);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public void addPage(Page page) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Pages (title, slug, content, isPublished, created, template, heading, subheading, author) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getSlug());
			statement.setString(3, page.getContent());
			statement.setInt(4, page.getIsPublished());
			statement.setTimestamp(5, page.getCreated());
			statement.setString(6, page.getTemplate());
			statement.setString(7, page.getHeading());
			statement.setString(8, page.getSubheading());
			statement.setInt(9, page.getAuthor());
			statement.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	public void updatePage(Page page) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Pages set title=?, slug=?, content=?, isPublished=?, template=?, heading=?, subheading=?, author=?, created=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getSlug());
			statement.setString(3, page.getContent());
			statement.setInt(4, page.getIsPublished());
			statement.setString(5, page.getTemplate());
			statement.setString(6, page.getHeading());
			statement.setString(7, page.getSubheading());	
			statement.setInt(8, page.getAuthor());
			statement.setTimestamp(9, page.getCreated());
			statement.setInt(10, page.getId());
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	
	public void updatePageOrder(int pageId, int order) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Pages set pageOrder=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, order);
			statement.setInt(2, pageId);
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	
	
	public void deletePage(int pageId) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"delete from Pages where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, pageId);
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}	
	
	public Page getPageById(int id) {
		Page page = new Page();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from Pages where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				page.setId(resultSet.getInt("id"));
				page.setTitle(resultSet.getString("title"));
				page.setSlug(resultSet.getString("slug"));
				page.setContent(resultSet.getString("content"));
				page.setIsPublished(resultSet.getInt("isPublished"));
				page.setTemplate(resultSet.getString("template"));
				page.setHeading(resultSet.getString("heading"));
				page.setSubheading(resultSet.getString("subheading"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return page;		
	}
	
	public List<Service> getServices() {
		
		List<Service> result = new ArrayList<Service>();
		String sql = "select * from Services inner join Categories on Services.categoryId=Categories.id inner join Users on Services.author=Users.id where Services.isActive=1";
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
				service.setLastUpdated(resultSet.getTimestamp("lastUpdated"));
				service.setAuthor(resultSet.getInt("Users.id"));
				service.setAuthorName(resultSet.getString("Users.firstName") + " " + resultSet.getString("Users.lastName"));
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
	
	
	public boolean serviceHasEmployees(int id) {
		
		System.out.println("Checking is service has empkoyees");
		boolean result = false;
		Connection connection = null;
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement(
					"select count(*) from EmployeeServices where serviceId=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getInt(1) >= 1) {
					result = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;		
	}	

	public List<Service> searchServices(String keyword) {		
		String betterKeyword = "%" + keyword + "%";
		System.out.println("We are searching for tis: " + betterKeyword);	
		List<Service> result = new ArrayList<Service>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select * from Services inner join Categories on Services.categoryId=Categories.id where Services.isActive=1 and Services.name like ? or Categories.name like ? or Services.description like ?",
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
					"insert into Services (name, price, description, categoryId, time, author, lastUpdated) values (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, service.getName());
			statement.setInt(2, service.getPrice());
			statement.setString(3, service.getDescription());
			statement.setInt(4, service.getCategoryId());
			statement.setTime(5, service.getTime());
			statement.setInt(6, service.getAuthor());
			statement.setTimestamp(7, service.getLastUpdated());
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
					"update Services set name=?, price=?, time=?, description=?, author=?, lastUpdated=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, service.getName());
			statement.setInt(2, service.getPrice());
			statement.setTime(3, service.getTime());
			statement.setString(4, service.getDescription());
			statement.setInt(5, service.getAuthor());
			statement.setTimestamp(6, service.getLastUpdated());
			statement.setInt(7, service.getId());
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
	
	
	public boolean hasEmployees(int serviceId) {
		int result = 0;
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select count(*) from EmployeeServices inner join Employees on Employees.id = employeeId where serviceId=? and Employees.isActive=1",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, serviceId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		if (result >= 1) {
			return true;
		}
		else {
			return false;
		}
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
				setting.setFacebook(resultSet.getString("facebook"));
				setting.setTwitter(resultSet.getString("twitter"));
				setting.setGooglePlus(resultSet.getString("googlePlus"));
				setting.setInstagram(resultSet.getString("instagram"));
				setting.setPinterest(resultSet.getString("pinterest"));	
				setting.setLogo(resultSet.getString("logo"));	
				setting.setFavicon(resultSet.getString("favicon"));	
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return setting;		
	}	
	
	public List<String[]> getMenu() {
		
		List<String[]> pages = new ArrayList<String[]>();
		Connection connection = null;
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement(
					"select * from Pages where isPublished=1 order by pageOrder asc",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String id = String.valueOf(resultSet.getString("id"));
				String[] pageDetails = {id, title};
				pages.add(pageDetails);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return pages;		
	}		
	
	
	public void saveSettings(Setting settings) {
		
		System.out.println("INSIDE SAVE SETTINGS FUNCTION");
		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Settings set siteTitle=?, siteDescription=?, companyName=?, vatNumber=?, address=?, phone=?, email=?, facebook=?, twitter=?, googlePlus=?, instagram=?, pinterest=?, logo=?, favicon=? where id=1",
					Statement.RETURN_GENERATED_KEYS);
			System.out.println("Update sitetitle with: " + settings.getSiteTitle());
			statement.setString(1, settings.getSiteTitle());
			statement.setString(2, settings.getSiteDescription());
			statement.setString(3, settings.getCompanyName());
			statement.setString(4, settings.getVatNumber());
			statement.setString(5, settings.getAddress());
			statement.setString(6, settings.getPhone());
			statement.setString(7, settings.getEmail());
			statement.setString(8, settings.getFacebook());
			statement.setString(9, settings.getTwitter());
			statement.setString(10, settings.getGooglePlus());
			statement.setString(11, settings.getInstagram());
			statement.setString(12, settings.getPinterest());
			statement.setString(13, settings.getLogo());
			statement.setString(14, settings.getFavicon());
			
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
		String sql = "select * from Employees inner join Users on Employees.author=Users.id where isActive=1";
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("Employees.id"));
				employee.setEmail(resultSet.getString("Employees.email"));
				employee.setFirstName(resultSet.getString("Employees.firstName"));
				employee.setLastName(resultSet.getString("Employees.lastName"));
				employee.setTitle(resultSet.getString("Employees.title"));
				employee.setDescription(resultSet.getString("Employees.description"));
				employee.setProfilePicture(resultSet.getString("Employees.profilePicture"));
				employee.setAuthor(resultSet.getInt("Employees.author"));
				employee.setAuthorName(resultSet.getString("Users.firstName") + " " + resultSet.getString("Users.lastName"));
				employee.setLastUpdated(resultSet.getTimestamp("Employees.lastUpdated"));
				result.add(employee);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	
	public int getTotalProfit() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		int totalProfit = 0;

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("select Services.price from Bookings inner join Services on Bookings.serviceId=Services.id where Bookings.isActive=1 and Bookings.date<?", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, date);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int price = resultSet.getInt("price");
				totalProfit = totalProfit + price;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return totalProfit;
	}
	
	
	public int getTotal(String table) {
		int result = 0;
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM " + table);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public List<Booking> getBookings(int employeeId) {
		
		Calendar today = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todaysDate = dateFormat.format(today.getTime());
		System.out.println("Today is: " + todaysDate);
		
		Calendar weekFromNow = Calendar.getInstance();
		weekFromNow.add(Calendar.HOUR, 168);
		String weekFromNowDate = dateFormat.format(weekFromNow.getTime());
		System.out.println("Week from now is: " + weekFromNowDate);
		
		List<Booking> result = new ArrayList<Booking>();
		String sql = "select * from Bookings inner join Services on Bookings.serviceId=Services.id where employeeId=" + employeeId + " and date>='" + todaysDate + "' and date<='" + weekFromNowDate + "'";
		System.out.println("SQL: " + sql);
		
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
				booking.setServiceName(resultSet.getString("Services.name"));
				booking.setServiceDuration(resultSet.getTime("Services.time"));
				booking.setServicePrice(resultSet.getInt("Services.price"));

				result.add(booking);
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
				booking.setEmail(resultSet.getString("email"));
				booking.setPhone(resultSet.getString("phone"));
				booking.setServiceName(resultSet.getString("Services.name"));
				booking.setServiceDuration(resultSet.getTime("Services.time"));
				booking.setServicePrice(resultSet.getInt("Services.price"));
				booking.setEmployeeName(resultSet.getString("Employees.firstName") + " " + resultSet.getString("Employees.lastName"));
				booking.setIsActive(resultSet.getInt("Bookings.isActive"));
				booking.setCreated(resultSet.getTimestamp("Bookings.created"));
				
				result.add(booking);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	
	public Booking getBookingById(int id) {
		
		Booking booking = new Booking();
		String sql = "select * from Bookings inner join Services on Bookings.serviceId=Services.id inner join Employees on Bookings.employeeId=Employees.id where Bookings.id=" + id;
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				booking.setId(resultSet.getInt("Bookings.id"));
				booking.setDate(resultSet.getDate("date"));
				booking.setHour(resultSet.getTime("hour"));
				booking.setFirstName(resultSet.getString("firstName"));
				booking.setLastName(resultSet.getString("lastName"));
				booking.setEmail(resultSet.getString("email"));
				booking.setPhone(resultSet.getString("phone"));
				booking.setServiceName(resultSet.getString("Services.name"));
				booking.setServiceDuration(resultSet.getTime("Services.time"));
				booking.setServicePrice(resultSet.getInt("Services.price"));
				booking.setEmployeeName(resultSet.getString("Employees.firstName") + " " + resultSet.getString("Employees.lastName"));
				booking.setIsActive(resultSet.getInt("Bookings.isActive"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return booking;
	}
	

	public void cancelBooking(int bookingId) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Bookings set isActive=0 where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, bookingId);
			statement.execute();
//			ResultSet generatedKeys = statement.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				employee.setId(generatedKeys.getInt(1));
//			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}	
	
	
	public Employee getEmployeeById(int id) {
		
		Employee employee = new Employee();
		Connection connection = null;
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement(
					"select * from Employees where id=? and isActive=1",
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
					"select * from Employees where isActive=1 and firstName like ? or lastName like ? or title like ? or description like ? and isActive=1",
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
					"select * from EmployeeServices inner join Employees on Employees.id = employeeId where serviceId=? and Employees.isActive=1",
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
					"insert into Employees (email, firstName, lastName, title, description, profilePicture, author, lastUpdated) values (?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, employee.getEmail());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getTitle());
			statement.setString(5, employee.getDescription());
			statement.setString(6, employee.getProfilePicture());
			statement.setInt(7, employee.getAuthor());
			statement.setTimestamp(8, employee.getLastUpdated());
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
					"update Employees set email=?, firstName=?, lastName=?, title=?, description=?, profilePicture=?, author=?, lastUpdated=? where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, employee.getEmail());
			statement.setString(2, employee.getFirstName());
			statement.setString(3, employee.getLastName());
			statement.setString(4, employee.getTitle());
			statement.setString(5, employee.getDescription());
			statement.setString(6, employee.getProfilePicture());
			statement.setInt(7, employee.getAuthor());
			statement.setTimestamp(8, employee.getLastUpdated());
			statement.setInt(9, employee.getId());
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
	
	
	public void deleteEmployee(int id) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Employees set isActive=0 where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
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
	
	
	public void deleteService(int id) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"update Services set isActive=0 where id=?",
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			statement.execute();
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

	public Booking addBooking(Booking booking) {		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Bookings (date, hour, employeeId, serviceId, firstName, lastName, email, phone, created) values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, booking.getDate());
			statement.setTime(2, booking.getHour());
			statement.setInt(3, booking.getEmployeeId());
			statement.setInt(4, booking.getServiceId());
			statement.setString(5, booking.getFirstName());
			statement.setString(6, booking.getLastName());
			statement.setString(7, booking.getEmail());
			statement.setString(8, booking.getPhone());
			statement.setTimestamp(9, booking.getCreated());
			statement.execute();
			
			System.out.println("Statement executed");
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				booking.setId(generatedKeys.getInt(1));
				System.out.println("The booking id is: " + generatedKeys.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		return booking;
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
			System.out.println("Trying to add record to gallery db");
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Images (path) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, image.getPath());

			statement.execute();
			
			System.out.println("Done");
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
					user.setId(resultSet.getInt("id"));
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
		
		public void updateUser(User user) {		
			Connection connection = null;
			try {
				connection = getConnection();
				System.out.println("USER PASSWORD IS: " + user.getPassword());
				String password = user.getPassword();
				if (password.length() != 0) {
					PreparedStatement statement = connection.prepareStatement(
							"update Users set firstName=?, lastName=?, email=?, password=? where id=?",
							Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, user.getFirstName());
					statement.setString(2, user.getLastName());
					statement.setString(3, user.getEmail());
					statement.setString(4, user.getPassword());
					statement.setInt(5, user.getId());
					statement.execute();
				}
				else {
					System.out.println("Executing second position");
					PreparedStatement statement = connection.prepareStatement(
							"update Users set firstName=?, lastName=?, email=? where id=?",
							Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, user.getFirstName());
					statement.setString(2, user.getLastName());
					statement.setString(3, user.getEmail());
					statement.setInt(4, user.getId());
					statement.execute();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				closeConnection(connection);
			}
		}
	
}
