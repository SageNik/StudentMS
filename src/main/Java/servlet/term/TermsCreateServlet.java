package servlet.term;

import dao.DisciplineDao;
import dao.TermDao;
import domine.Discipline;
import domine.Term;
import org.apache.commons.lang.StringUtils;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static constants.Constants.VALID_MESSAGE;

/**
 * Created by Ник on 11.05.2016.
 */
@WebServlet(name = "TermsCreateServlet", urlPatterns = {"/admin/terms/tcreate", "/admin/terms/tcreate/", "/student/terms/tcreate/", "/student/terms/tcreate"})

public class TermsCreateServlet extends AbstractServlet {

    DisciplineDao dDao = new DisciplineDao();

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {


        List<Discipline> allDisc = dDao.getAllDisciplines();

        request.setAttribute("allDisc", allDisc);
        request.setAttribute("title", "Создание Семестра");
            goToJSP("Terms/termsCreate.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        if (StringUtils.isBlank(request.getParameter("duration")) || request.getParameterValues("cre-term")==null) {
            List<Discipline> allDisc = dDao.getAllDisciplines();

            request.setAttribute(VALID_MESSAGE, "true");
            request.setAttribute("allDisc", allDisc);
            request.setAttribute("title", "Создание Семестра");
            goToJSP("Terms/termsCreate.jsp", request, response);
        } else {
            Integer duration = Integer.parseInt(request.getParameter("duration"));
            String[] disciplines = request.getParameterValues("cre-term");
            Integer[] discId = new Integer[disciplines.length];
            int i = 0;
            for (String s : disciplines) {
                discId[i] = Integer.parseInt(s);
                i++;
            }

            Term newTerm = new Term(duration);
            TermDao tDao = new TermDao();
            tDao.addNewTerm(newTerm, discId);

            this.doGetHandler(request, response);
        }
    }
}