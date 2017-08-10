package servlet.student;

import dao.StudentDao;
import domine.Student;
import org.apache.commons.lang.StringUtils;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import static constants.Constants.VALID_MESSAGE;

/**
 * Created by Ник on 16.05.2016.
 */
@WebServlet(name = "StudentsModifyServlet",urlPatterns = {"/admin/students/smodify","/admin/students/smodify/","/student/students/smodify/","/student/students/smodify"})

public class StudentsModifyServlet extends AbstractServlet {

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        StudentDao sDao = new StudentDao();
        Student stud = sDao.getStudentById((Integer)request.getSession().getAttribute("studentsId"));
        request.setAttribute("mod_name", stud.getName());
        request.setAttribute("mod_surname", stud.getSurname());
        request.setAttribute("mod_groupe", stud.getGroupe());
        request.setAttribute("mod_date", stud.getDate());

        request.setAttribute("title", "Изменение Студента");
            goToJSP("Students/studentsModify.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String groupe = request.getParameter("groupe");
        String date = request.getParameter("date");

        request.setAttribute("title", "Изменение Студента");
        if (StringUtils.isBlank(request.getParameter("surname")) || StringUtils.isBlank(request.getParameter("name")) || StringUtils.isBlank(request.getParameter("groupe")) || StringUtils.isBlank(request.getParameter("date"))) {
            request.setAttribute("mod_name", name);
            request.setAttribute("mod_surname", surname);
            request.setAttribute("mod_groupe", groupe);
            request.setAttribute("mod_date", date);
            request.setAttribute(VALID_MESSAGE, "true");
            goToJSP("Students/studentsModify.jsp", request, response);
        } else {
            Integer id_stud = (Integer)request.getSession().getAttribute("studentsId");

            StudentDao sDao = new StudentDao();
            Student newStud = sDao.getStudentById(id_stud);
            Date sqlDate = newStud.formatDate(date);
            if (newStud != null) {
                newStud.setSurname(surname);
                newStud.setName(name);
                newStud.setGroupe(groupe);
                newStud.setDate(sqlDate);

                sDao.updateStudent(newStud);
            }
            request.setAttribute("mod_name", newStud.getName());
            request.setAttribute("mod_surname", newStud.getSurname());
            request.setAttribute("mod_groupe", newStud.getGroupe());
            request.setAttribute("mod_date", newStud.getDate());
            this.doGetHandler(request, response);
        }
    }
}