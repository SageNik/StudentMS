<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 12.05.2016
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="white-space: nowrap" id="term-div_top">
    <div style="display: inline-block" class="top-link_back-string" >
        <table >
            <tr>
                <td class="back"><a href="../home"> На главную </a></td>
                <td class="back"><a href="../terms"> Назад </a></td>
            </tr>
        </table>
    </div>
    <div style="display: inline-block" id="term-top">
        <p id="string-top">Для модификации семестра отредактируйте данные и нажмите кнопку "Применить"</p>
    </div>
</div>

<div class="table-term_create">
    <form action="tmodify" method="post">
        <table align="left">
            <c:if test="${VALID_MESSAGE == true}"><tr>
                <td colspan="2" class="error_mess"><p style="color: red">Поля не должны быть пустыми!</p></td>
            </tr></c:if>
            <tr>
                <td class="term-td_cr1">Длительность (в неделях)</td>
                <td class="term-td_cr2"><input class="input-string" type="text" size="20" name="duration" maxlength="2" value="${term_dur}"></td>
            </tr>
            <tr>
                <td class="term-td_cr4">Дисциплины в семестре</td>
                <td class="term-td_cr3">
                    <select multiple class="select-disc" name="mod_terms">
                        <c:forEach items="${allDisc}" var="disc">
                            <option class="option_term" value="${disc.id}"> ${disc.discipline} </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="term-td_cr1"></td>
                <td class="disc-td_cr2"><input class="button_termM" type="submit" value="Применить" name="modi_term" id="button_m"></td>
            </tr>
        </table>

    </form>

</div>
