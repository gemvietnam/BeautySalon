package controller;

import java.io.IOException;
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
import models.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/translate")
public class TranslatorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TranslatorController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String input = request.getParameter("input");
		String inputlanguage = request.getParameter("inputLanguage");
		String language = request.getParameter("language");
		System.out.println("Perform a translation of: " + input + " to a language: " + language);
		
		Language outputLanguage;
		switch(language) {
			case "french":
				outputLanguage = Language.FRENCH;
				break;
			case "arabic":
				outputLanguage = Language.ARABIC;
				break;
			case "italian":
				outputLanguage = Language.ITALIAN;
				break;
			case "portuguese":
				outputLanguage = Language.PORTUGUESE;
				break;
			case "spanish":
				outputLanguage = Language.SPANISH;
				break;
			default:
				outputLanguage = Language.ENGLISH;
				break;
		}
		
		Language inputLanguage;
		switch(inputlanguage) {
		case "french":
			inputLanguage = Language.FRENCH;
			break;
		case "arabic":
			inputLanguage = Language.ARABIC;
			break;
		case "italian":
			inputLanguage = Language.ITALIAN;
			break;
		case "portuguese":
			inputLanguage = Language.PORTUGUESE;
			break;
		case "spanish":
			inputLanguage = Language.SPANISH;
			break;
		default:
			inputLanguage = Language.ENGLISH;
			break;
	}
		
		LanguageTranslation service = new LanguageTranslation();
	    service.setUsernameAndPassword("552acf01-fdfd-45de-8353-ad7a965c24bd", "7fG1WKLcg1eP");

	    TranslationResult translationResult = service.translate(input, inputLanguage, outputLanguage).execute();
		
	    System.out.println("Total characters: " + translationResult.getCharacterCount());
	    System.out.println("Total words: " + translationResult.getWordCount());
	    System.out.println("Translation result: " + translationResult.getFirstTranslation());
	    
		response.getWriter().append(translationResult.getFirstTranslation());
	}

}
