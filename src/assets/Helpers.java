package assets;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

import dao.BeautyDAO;
import dao.BeautyDAOImpl;

/**
* <h1>Helpers</h1>
* Contains helper methods for displaying the data throughout application.
*
*/

public class Helpers {
	
	public static String DateToString(Date date) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String stringDate = dateFormat.format(date);
		return stringDate;
	}
	
	public static String TimestampToString(Timestamp date) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String stringDate = dateFormat.format(date);
		return stringDate;
	}
	
	public static String TimeToHumanHour(Timestamp time) {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String stringDate = dateFormat.format(time);
		return stringDate;
	}
	
	public static String TimeToHour(Time time) {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String stringDate = dateFormat.format(time);
		return stringDate;
	}
	
	public static boolean ServiceHasEmployees(int serviceId) {
		
		BeautyDAO beautyDAO = new BeautyDAOImpl();
		return beautyDAO.serviceHasEmployees(serviceId);
	}
	
	public static String getBaseUrl( HttpServletRequest request ) {
	    if ( ( request.getServerPort() == 80 ) ||
	         ( request.getServerPort() == 443 ) )
	      return request.getScheme() + "://" +
	             request.getServerName() +
	             request.getContextPath();
	    else
	      return request.getScheme() + "://" +
	             request.getServerName() + ":" + request.getServerPort() +
	             request.getContextPath();
	}
	
	public static String DisplayIfNotNull(String string) {
		if (string != null) {
			return string;
		}
		else {
			return "";
		}
	}
	
//	public static String Translate(String inputText, String language) {
//		Language chosenLanguage = Language.SPANISH;
//		LanguageTranslation service = new LanguageTranslation();
//	    service.setUsernameAndPassword("552acf01-fdfd-45de-8353-ad7a965c24bd", "7fG1WKLcg1eP");
//	    TranslationResult translationResult = service.translate(inputText, Language.ENGLISH, chosenLanguage).execute();
//	    return translationResult.getFirstTranslation();
//	}

	
}
