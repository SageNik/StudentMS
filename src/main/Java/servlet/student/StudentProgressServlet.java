package servlet.student;

import dao.DisciplineDao;
import dao.MarkDao;
import dao.StudentDao;
import dao.TermDao;
import domine.Discipline;
import domine.Mark;
import domine.Student;
import domine.Term;
import org.apache.commons.lang.StringUtils;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Ник on 16.05.2016.
 */
@WebServlet(name = "StudentProgressServlet",urlPatterns = {"/admin/students/progress","/admin/students/progress/","/student/students/progress/","/student/students/progress"})

public class StudentProgressServlet extends AbstractServlet {

   private Integer idTerm ;
    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Integer proSt_id = (Integer)request.getSession().getAttribute("studentsId");
        DisciplineDao dDao = new DisciplineDao();

        TermDao termDao = new TermDao();
        Term term = termDao.getAllTerms().get(0);
        idTerm = term.getId();
        List<Discipline> disList = dDao.getAllTermDiscipline(idTerm);

        MarkDao mDao = new MarkDao();
        List<Mark> markList = mDao.getAllMarksStud(proSt_id, term);

        StudentDao sDao = new StudentDao();
        Student stud = sDao.getStudentById(proSt_id);
        List<Term> termList = termDao.getAllTerms();
        Integer aMark = 0;
        Double averMark = null;
        for (Mark mark : markList) {
            aMark += mark.getMark();
        }
        if (aMark > 0)
            averMark = ((double) aMark / markList.size());

        request.getSession().setAttribute("pro_surname", stud.getSurname());
        request.getSession().setAttribute("pro_name", stud.getName());
        request.getSession().setAttribute("pro_groupe", stud.getGroupe());
        request.getSession().setAttribute("pro_date", stud.getDate());
        request.getSession().setAttribute("pro_termDisc", disList);
        request.getSession().setAttribute("pro_markStud", markList);
        request.getSession().setAttribute("pro_terms", termList);
        request.getSession().setAttribute("aver_mark", averMark);
        request.getSession().setAttribute("mark_idTerm", idTerm);
        request.getSession().setAttribute("pro_termDisc", disList);


        request.setAttribute("title", "Успеваемость Студента");
        goToJSP("Students/studentsProgress.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        if (!StringUtils.isEmpty(request.getParameter("redact"))) {

            DisciplineDao dDao = new DisciplineDao();
            List<Discipline> disList = dDao.getAllTermDiscipline(idTerm);

            request.getSession().setAttribute("pro_termDisc",disList);
            redirectRequest("marks", request, response);
        } else {
            idTerm = Integer.parseInt(request.getParameter("termP"));
            this.doGetHandler(request, response);
        }
    }
}