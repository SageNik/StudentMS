package servlet.discipline;

import dao.DisciplineDao;
import domine.Discipline;
import org.apache.commons.lang.StringUtils;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Ник on 10.05.2016.
 */
    @WebServlet(name = "DisciplineServlet",urlPatterns = {"/admin/disciplines","/admin/disciplines/","/student/disciplines/","/student/disciplines"})
    public class DisciplineServlet extends AbstractServlet {

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        DisciplineDao dDao = new DisciplineDao();
        List<Discipline> disciplines = dDao.getAllDisciplines();
        request.setAttribute("disciplines", disciplines);
        request.setAttribute("title", "Список Дисциплин");
        goToJSP("Disciplines/disciplinesList.jsp", request, response);
    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        DisciplineDao dDao = new DisciplineDao();
        if (!StringUtils.isEmpty(request.getParameter("dcreate"))) {
            redirectRequest("disciplines/dcreate", request, response);
        } else {
            if (!StringUtils.isEmpty(request.getParameter("dmodify"))) {
                Discipline disc = dDao.getDisciplineById(Integer.parseInt(request.getParameter("disciplineId")));
                request.getSession().setAttribute("dis_name", disc.getDiscipline());
                request.getSession().setAttribute("modDs", request.getParameter("disciplineId"));
                redirectRequest("disciplines/dmodify", request, response);
            } else {
                if (!StringUtils.isEmpty(request.getParameter("ddelete"))) {
                    Integer delStudId = Integer.parseInt(request.getParameter("disciplineId"));
                    dDao.deleteDisciplineById(delStudId);
                    }
                    this.doGetHandler(request, response);
                }

            }
        }

    }
