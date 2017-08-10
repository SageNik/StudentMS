package servlet;

import dao.AccountDao;
import exception.InvalidDataException;
import dao.RoleDao;
import domine.Account;
import domine.Role;
import org.apache.commons.lang.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.Constants.*;

/**
 * Created by Ник on 05.05.2016.
 */
@WebServlet(name = "LoginServlet",urlPatterns = {"/login/","/login"})
public class LoginServlet extends AbstractServlet {

private final Map<Integer, String> mappings = new HashMap<Integer, String>();
public LoginServlet(){
    mappings.put(ROLE_ADMIN,"/admin");
    mappings.put(ROLE_STUDENT,"/student");
}

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        RoleDao rDao = new RoleDao();
        List<Role> roles = rDao.getAllRoles();
        request.setAttribute("role", roles);
        request.setAttribute("title" , "Вход в систему");
        goToJSP("/login.jsp", request, response);
    }

    protected void validateRequest(String username, String password)
            throws InvalidDataException {
        if (StringUtils.isBlank(username)) {
            throw new InvalidDataException("username");
        }
        if (StringUtils.isBlank(password)) {
            throw new InvalidDataException("password");
        }
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        Integer idRole = Integer.parseInt(request.getParameter("role"));
        try {
            validateRequest(login, password);
            AccountDao aDao = new AccountDao();
            Account a = aDao.aLogin(login, password, idRole);
            if(a!=null) {
                String mapping = mappings.get(idRole);
                if (!StringUtils.isBlank(mapping)) {
                    request.getSession().setAttribute(CURRENT_SESSION_ACCOUNT, a);
                    request.getSession().setAttribute(CURRENT_ROLE, idRole);

                    redirectRequest(mapping + "/home", request, response);
                } else {
                    throw new InvalidDataException("role");
                }
            }else{
                throw new InvalidDataException("account");
            }
        } catch (Exception e) {
            request.setAttribute(VALID_MESSAGE, "true");
            doGetHandler(request,response);
        }

    }
}
