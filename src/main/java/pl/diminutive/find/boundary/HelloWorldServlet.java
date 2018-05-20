package pl.diminutive.find.boundary;

import pl.diminutive.find.control.TextHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)	throws IOException {

        TextHandler tH= new TextHandler();
        request.setCharacterEncoding("UTF-8");
        tH.processText(request.getParameter("textForSearch"));

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Set<String> diminutives = tH.getDiminutives();
        Set<String> wordWithoutDiminutives = tH.getWordWithoutDiminutives();

        out.println("<br>Wyrazy zdrobniałe " + diminutives.size() + ":<br>" + String.join(",", diminutives));
        out.println("<br>Wyrazy niezdrobniałe " + wordWithoutDiminutives.size() + ":<br>" + String.join(",", wordWithoutDiminutives));
    }
}
