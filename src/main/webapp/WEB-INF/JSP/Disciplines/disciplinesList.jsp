<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 10.05.2016
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <form action="disciplines" method="post">
<div style="white-space: nowrap" >
<div style="display: inline-block" class="top-link_back" >
    <table>
        <tr>
            <td ><a href="home"> На главную </a></td>
        </tr>
    </table>
</div>


<div style="display: inline-block" class="discipline_list" align="center" >
           <table id="table-disc" >
            <th colspan="2" align="left" ><h3>Список дисциплин</h3></th>
            <tr id="tr-top_disc" >
                <c:if test="${CURRENT_ROLE == 1}"> <td class="td-checkbox_dis"></td></c:if>
                <td class="td-discipline">Наименование дисциплины</td>
            </tr>
            <c:forEach items="${disciplines}" var="disc">
                <tr>
                    <c:if test="${CURRENT_ROLE == 1}"> <td class="td-checkbox_dis"><input type="checkbox" name="disciplineId" value="${disc.id}"></td></c:if>
                    <td class="td-discipline"><c:out value="${disc.discipline}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
</div>

<div style="display: inline-block" id="disc_button" align="center" >
    <table cellspacing="25%"  >
        <c:if test="${CURRENT_ROLE == 1}"> <tr>
            <td ><input class="td3_top-button"type="submit" name="dcreate" value="Создать дисциплину..."></td>
        </tr>
        <tr>
            <td > <input class="td3_top-button"type="submit" name="dmodify" value="Модифицировать выбранную дисциплину..."onclick="return discTest(disciplineId)"></td>
        </tr>
        <tr>
            <td ><input class="td3_top-button"type="submit" name="ddelete" value="Удалить выбранную дисциплину"onclick="return discTest(disciplineId)"></td>
        </tr></c:if>
    </table>

</div >
</div>
   </form> </div>