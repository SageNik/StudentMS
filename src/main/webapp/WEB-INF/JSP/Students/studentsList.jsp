<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 09.05.2016
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div>
    <form action="students" method="post" name="f1">
        <div style="white-space: nowrap">
            <div style="display: inline-block" class="top-link_back" align="left">
                <table id="stud-tab-top">
                    <tr>
                        <td><a href="home"> На главную </a></td>
                    </tr>
                </table>
            </div>
            <div style="display: inline-block" align="center">
                <table cellspacing="30%">
                    <tr>
                    <c:if test="${fn:length(studentList)>0}">
                        <td><input class="td1_top-button" type="submit" name="progress"
                                   value="Просмотреть успеваемость выбранных студентов"
                                   onclick="return progressTest(studentsId)"></td>
                    </c:if>
                    <c:if test="${CURRENT_ROLE == 1}">
                        <td><input class="td2_top-button" type="submit" name="create" value="Создать студента..."></td>
                    </c:if>
                    </tr>
                    <c:if test="${fn:length(studentList)>0}">
                        <c:if test="${CURRENT_ROLE == 1}">
                            <tr>
                                <td><input class="td1_top-button" type="submit" name="modify"
                                           value="Модифицировать выбранного студента..."
                                           onclick="return progressTest(studentsId)"></td>

                                <td><input class="td2_top-button" type="submit" name="deleted"
                                           value="Удалить выбранных студентов"
                                           onclick="return progresstDel(studentsId)">
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                </table>
            </div>
        </div>

        <div class="student_list" align="center">

            <table id="table-stud">
                <th colspan="2" align="left"><h3>Список студентов</h3></th>
                <tr id="tr-top">
                    <td class="td-checkbox"></td>
                    <td class="td-surname">Фамилия</td>
                    <td class="td-name">Имя</td>
                    <td class="td-groupe">Группа</td>
                    <td class="date">Дата поступления</td>
                </tr>
                <c:forEach items="${studentList}" var="stud">
                    <tr>
                        <td class="td-checkbox"><input type="checkbox" name="studentsId" value="${stud.id}"></td>
                        <td class="td-surname"><c:out value="${stud.surname}"></c:out></td>
                        <td class="td-name"><c:out value="${stud.name}"></c:out></td>
                        <td class="td-groupe"><c:out value="${stud.groupe}"></c:out></td>
                        <td class="td-date"><c:out value="${stud.date}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </form>
</div>
