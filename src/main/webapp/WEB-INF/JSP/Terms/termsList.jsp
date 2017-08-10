<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 10.05.2016
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <form action="terms" method="post" >
<div style="white-space: nowrap" id="term-div_top">
    <div style="display: inline-block" class="top-link_back">
        <table>
            <tr>
                <td><a href="home"> На главную </a></td>
            </tr>
        </table>
    </div>
    <div style="display: inline-block" id="term-top">
            <table id="term-table">
                <tr class="tr-term">
                    <td class="td-term_1">Выбрать семестр</td>
                    <td class="td-term_2"><select name="termZ" id="opening_term">
                        <c:forEach items="${terms}" var="term">
                            <option class="option_term" value="${term.id}">Семестр ${term.id}</option>
                        </c:forEach>
                    </select></td>
                    <td class="td-term_3"><input class="button_term" type="submit" value="выбрать" name="enter"
                                                 id="button"></td>
                </tr>
                <tr class="tr-term2">
                    <td colspan="3">
                        <p> Длительность семестра: ${term.duration} недели</p>
                    </td>
                </tr>
            </table>
    </div>
</div>
<div style="white-space: nowrap">
    <div style="display: inline-block" class="term_list" align="center">

        <table id="table-term-dis">
            <th colspan="2" align="left"><h3>Список дисциплин семестра</h3></th>
            <tr id="tr-top_disc">
                <td class="td-discipline" valign="middle">Наименование дисциплины</td>
            </tr>
            <c:forEach items="${termDisc}" var="disc">
                <tr>
                    <td class="td-discipline"><c:out value="${disc.discipline}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div style="display: inline-block" id="term_button" align="center">
        <table cellspacing="30%">
<c:if test="${CURRENT_ROLE == 1}"><tr>
                <td>
                    <input class="td3_top-button" type="submit" name="gcreate" value="Создать семестр...">
                </td>
            </tr>
            <tr>
                <td>
                        <input class="td3_top-button" type="submit" name="gmodify"value="Модифицировать текущий семестр...">
                </td>
            </tr>
            <tr>
                <td><input class="td3_top-button" type="submit" name="gdelete" value="Удалить текущий семестр"></td>
            </tr></c:if>
        </table>

    </div>
</div>
</form>
    </div>
