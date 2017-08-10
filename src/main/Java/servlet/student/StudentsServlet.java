package servlet.student;

import dao.StudentDao;
import domine.Student;
import org.apache.commons.lang.StringUtils;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Ник on 09.05.2016.
 */
@WebServlet(name = "StudentsServlet", urlPatterns = {"/admin/students", "/admin/students/", "/student/students/", "/student/students"})
public class StudentsServlet extends AbstractServlet {

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        StudentDao sDao = new StudentDao();
        List<Student> students = sDao.getAllStudents();
        request.setAttribute("studentList", students);
        request.setAttribute("title", "Список Студентов");
        goToJSP("Students/studentsList.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        StudentDao sDao = new StudentDao();

            if (!StringUtils.isEmpty(request.getParameter("create"))) {
                redirectRequest("students/screate", request, response);
            } else {
                Integer proSt_id = Integer.parseInt(request.getParameter("studentsId"));
                request.getSession().setAttribute("studentsId", proSt_id);

                if (!StringUtils.isEmpty(request.getParameter("progress"))) {

                    redirectRequest("students/progress", request, response);
                } else {
                if (!StringUtils.isEmpty(request.getParameter("modify"))) {

                    redirectRequest("students/smodify", request, response);
                } else {
                    if (!StringUtils.isEmpty(request.getParameter("deleted"))) {
                        String[] delStudId = request.getParameterValues("studentsId");
                        for (String s : delStudId) {
                            sDao.deleteStudentById(Integer.parseInt(s));
                        }
                        this.doGetHandler(request, response);
                    }

                }
            }


        }
    }
}