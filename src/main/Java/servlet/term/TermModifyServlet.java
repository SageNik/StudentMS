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
 * Created by Ник on 12.05.2016.
 */
@WebServlet(name = "TermModifyServlet", urlPatterns = {"/admin/terms/tmodify", "/admin/terms/tmodify/", "/student/terms/tmodify/", "/student/terms/tmodify"})

public class TermModifyServlet extends AbstractServlet {

    DisciplineDao dDao = new DisciplineDao();

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        List<Discipline> allDisc = dDao.getAllDisciplines();

        request.setAttribute("allDisc", allDisc);
        request.setAttribute("title", "Изменение Семестра");

            goToJSP("Terms/termsModify.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        if (StringUtils.isBlank(request.getParameter("duration")) || request.getParameterValues("mod_terms") == null) {

            Integer duration = (Integer)request.getSession().getAttribute("term_dur");
            List<Discipline> allDisc = dDao.getAllDisciplines();
            request.setAttribute("duration",duration);
            request.setAttribute(VALID_MESSAGE, "true");
            request.setAttribute("allDisc", allDisc);
            request.setAttribute("title", "Изменение Семестра");
            goToJSP("Terms/termsModify.jsp", request, response);
        } else {
            Integer term_id = (Integer)request.getSession().getAttribute("termId");
            Integer durat = Integer.parseInt(request.getParameter("duration"));
            String[] disciplines = request.getParameterValues("mod_terms");
            Integer[] discId = new Integer[disciplines.length];
            int i = 0;
            for (String s : disciplines) {
                discId[i] = Integer.parseInt(s);
                i++;
            }
            TermDao tDao = new TermDao();
            Term newTerm = tDao.getTermById(term_id);
            newTerm.setDuration(durat);
            tDao.updateTerm(newTerm, discId);
            request.setAttribute("term_dur", durat);
            this.doGetHandler(request, response);
        }
    }

}