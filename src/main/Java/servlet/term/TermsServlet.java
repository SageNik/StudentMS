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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 10.05.2016.
 */
@WebServlet(name = "TermsServlet", urlPatterns = {"/admin/terms", "/admin/terms/", "/student/terms/", "/student/terms"})
public class TermsServlet extends AbstractServlet {
   private Integer idTerm;

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        TermDao termDao = new TermDao();
        List<Term> terms = new ArrayList<Term>();
        terms = termDao.getAllTerms();
        Term term = terms.get(0);
        idTerm = term.getId();

        DisciplineDao dDao = new DisciplineDao();
        List<Discipline> termDisc = dDao.getAllTermDiscipline(idTerm);

        request.getSession().setAttribute("termId", idTerm);
        request.setAttribute("terms", terms);
        request.setAttribute("title", "Список Семестров");
        request.setAttribute("termDisc", termDisc);
        request.setAttribute("term", term);
        goToJSP("Terms/termsList.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        TermDao tDao = new TermDao();
        if (!StringUtils.isEmpty(request.getParameter("gcreate"))) {
            redirectRequest("terms/tcreate", request, response);
        } else {
            if (!StringUtils.isEmpty(request.getParameter("gmodify"))) {
                Term t = tDao.getTermById(idTerm);
                request.getSession().setAttribute("term_dur",t.getDuration());
                redirectRequest("terms/tmodify", request, response);
            } else {
                if (!StringUtils.isEmpty(request.getParameter("gdelete"))) {
                    tDao.deleteTermById(idTerm);

                    TermDao termDao = new TermDao();
                    Term term = termDao.getAllTerms().get(0);
                    idTerm = term.getId();

                    this.doGetHandler(request, response);
                } else {
                    idTerm = Integer.parseInt(request.getParameter("termZ"));
                    this.doGetHandler(request, response);
                }
            }
        }
    }
}



