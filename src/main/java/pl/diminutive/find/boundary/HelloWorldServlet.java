package pl.diminutive.find.boundary;
import pl.diminutive.find.control.TextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final TextHandler tH= new TextHandler();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        tH.clear();
        request.setCharacterEncoding("UTF-8");
        tH.processText(request.getParameter("textForSearch"));

        request.setAttribute("diminutives",  tH.getDiminutives());
        request.setAttribute("nonDiminutives", tH.getWordWithoutDiminutives());
        request.setAttribute("diminutivesCount", tH.getDiminutives().size());
        request.setAttribute("nonDiminutivesCount", tH.getWordWithoutDiminutives().size());

        request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);

    }
}
