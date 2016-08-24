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
@WebServlet("/language")
public class LanguageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LanguageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/language.jsp";
		request.setAttribute("languageText", "Hello world!");
		
		LanguageTranslation service = new LanguageTranslation();
	    service.setUsernameAndPassword("552acf01-fdfd-45de-8353-ad7a965c24bd", "7fG1WKLcg1eP");

	    TranslationResult translationResult = service.translate("hello", Language.ENGLISH, Language.SPANISH).execute();
		
	    System.out.println("Total characters: " + translationResult.getCharacterCount());
	    System.out.println("Total words: " + translationResult.getWordCount());
	    System.out.println("Translation result: " + translationResult.getFirstTranslation());
	    
	    request.setAttribute("translation", translationResult.getFirstTranslation());
	    
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
