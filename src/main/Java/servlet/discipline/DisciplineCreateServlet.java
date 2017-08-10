package servlet.discipline;

import dao.DisciplineDao;
import domine.Discipline;
import org.apache.commons.lang.StringUtils;
import servlet.AbstractServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.Constants.VALID_MESSAGE;

/**
 * Created by Ник on 13.05.2016.
 */
@WebServlet(name = "DisciplineCreateServlet", urlPatterns = {"/admin/disciplines/dcreate", "/admin/disciplines/dcreate/", "/student/disciplines/dcreate/", "/student/disciplines/dcreate"})

public class DisciplineCreateServlet extends AbstractServlet {

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        request.setAttribute("title", "Создание Дисциплины");
            goToJSP("Disciplines/disciplineCreate.jsp", request, response);

    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        if (StringUtils.isBlank(request.getParameter("ddiscipline"))) {

            request.setAttribute(VALID_MESSAGE, "true");
            request.setAttribute("title", "Создание Дисциплины");
            goToJSP("Disciplines/disciplineCreate.jsp", request, response);

        } else {
            String discipline = request.getParameter("ddiscipline");
            Discipline disc = new Discipline(discipline);
            DisciplineDao dDisc = new DisciplineDao();
            dDisc.addNewDiscipline(disc);

            this.doGetHandler(request, response);
        }
    }
}