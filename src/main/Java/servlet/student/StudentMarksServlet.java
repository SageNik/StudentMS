package servlet.student;

import dao.DisciplineDao;
import dao.MarkDao;
import dao.TermDao;
import domine.Discipline;
import domine.Mark;
import domine.Term;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Ник on 03.06.2016.
 */
@WebServlet (name="StudentMarksServlet", urlPatterns= {"/admin/students/marks","/admin/students/marks/","/student/students/marks/","/student/students/marks"} )
public class StudentMarksServlet extends AbstractServlet {


    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {


        request.setAttribute("title", "Оценки Студента");
        goToJSP("Students/studentsMarks.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        Integer id_stud = (Integer)request.getSession().getAttribute("studentsId");
        Integer id_term = (Integer)request.getSession().getAttribute("mark_idTerm");
       List<Discipline> dT = (List<Discipline>) request.getSession().getAttribute("pro_termDisc");
        String[] markss = request.getParameterValues("mark_dis");

        Integer[] discT = new Integer[dT.size()];
        int i=0;
        for(Discipline dis : dT){
            discT[i]=dis.getId();
            i++;
        }
        MarkDao mDao = new MarkDao();
        Mark mark=null;
        for(int j=0;j<discT.length;j++) {
            mark = mDao.getMark(id_stud, id_term, discT[j]);

            if (mark != null) {
                mark.setMark(Integer.parseInt(markss[j]));
                mDao.updateMark(mark);
            } else {
                mDao.addNewMark(id_stud, id_term, discT[j], Integer.parseInt(markss[j]));
            }
        }
        DisciplineDao dDao = new DisciplineDao();
        List<Discipline> disList = dDao.getAllTermDiscipline(id_term);
        request.setAttribute("mark_studId", id_stud);
        request.setAttribute("pro_termDisc", disList);
        TermDao tDao = new TermDao();
        Term term = tDao.getTermById(id_term);

        List<Mark> markList = mDao.getAllMarksStud(id_stud, term);
        request.setAttribute("pro_markStud",markList);

            this.doGetHandler(request, response);

    }
}
