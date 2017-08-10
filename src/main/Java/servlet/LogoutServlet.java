package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ник on 09.05.2016.
 */
@WebServlet(name = "LogoutServlet",urlPatterns = {"/student/logout/","/student/logout","/admin/logout","/admin/logout/","/logout/","/logout"})
public class LogoutServlet extends AbstractServlet{

    protected void doGetHandler (HttpServletRequest request, HttpServletResponse response) throws Exception{
    request.getSession().invalidate();
        redirectRequest("/login", request, response);


    }
}
