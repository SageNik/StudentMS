package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ник on 06.05.2016.
 */
@WebServlet(name = "HomeServlet",urlPatterns = {"/student/home/","/student/home","/admin/home","/admin/home/"})
public class HomeServlet extends AbstractServlet{

    protected void doGetHandler (HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setAttribute("title" , "Главная");
        goToJSP("/home.jsp",request,response);
    }
}
