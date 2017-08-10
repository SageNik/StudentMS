package servlet.student;

import dao.StudentDao;
import domine.Student;
import org.apache.commons.lang.StringUtils;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.Constants.VALID_MESSAGE;

/**
 * Created by Ник on 14.05.2016.
 */
@WebServlet(name = "StudentCreateServlet", urlPatterns = {"/admin/students/screate", "/admin/students/screate/", "/student/students/screate/", "/student/students/screate"})

public class StudentCreateServlet extends AbstractServlet {

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        request.setAttribute("title", "Создание Студента");
        goToJSP("Students/studentsCreate.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        if (StringUtils.isBlank(request.getParameter("surname")) || StringUtils.isBlank(request.getParameter("name")) || StringUtils.isBlank(request.getParameter("groupe")) || StringUtils.isBlank(request.getParameter("date"))) {
            request.setAttribute(VALID_MESSAGE,"true");
            request.setAttribute("title", "Создание Студента");
            goToJSP("Students/studentsCreate.jsp", request, response);
        } else {
            String surname = request.getParameter("surname");
            String name = request.getParameter("name");
            String groupe = request.getParameter("groupe");
            String date = request.getParameter("date");
            Student newStud = new Student(name, surname, date, groupe);
            StudentDao sDao = new StudentDao();

            sDao.addNewStudent(newStud);

            this.doGetHandler(request, response);
        }
    }
}