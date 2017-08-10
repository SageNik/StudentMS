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
 * Created by Ник on 18.05.2016.
 */
@WebServlet(name = "DisciplineModifyServlet", urlPatterns = {"/admin/disciplines/dmodify", "/admin/disciplines/dmodify/", "/student/disciplines/dmodify/", "/student/disciplines/dmodify"})

public class DisciplineModifyServlet extends AbstractServlet {

    protected void doGetHandler(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        request.setAttribute("title", "Изменение Дисциплины");
            goToJSP("Disciplines/disciplineModify.jsp", request, response);

    }

    protected void doPostHandler(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(request.getParameter("mdiscipline"))) {
            request.setAttribute(VALID_MESSAGE, "true");
            request.setAttribute("title", "Изменение Дисциплины");
            goToJSP("Disciplines/disciplineModify.jsp", request, response);
        } else {
            String discipline = request.getParameter("mdiscipline");
            Integer disId = Integer.parseInt(request.getParameter("mod_dis"));
            DisciplineDao dDisc = new DisciplineDao();
            Discipline discip = dDisc.getDisciplineById(disId);
            discip.setDiscipline(discipline);
            dDisc.updateDiscipline(discip);
            request.getSession().setAttribute("dis_name", discip.getDiscipline());

            this.doGetHandler(request, response);
        }
    }

}
