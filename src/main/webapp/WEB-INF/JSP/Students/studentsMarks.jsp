
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="white-space: nowrap" id="term-div_top">
    <div style="display: inline-block" class="top-link_back-string" >
        <table >
            <tr>
                <td class="back"><a href="../home"> На главную </a></td>
                <td class="back"><a href="progress"> Назад </a></td>
            </tr>
        </table>
    </div>
    <div style="display: inline-block" id="term-top">
        <p id="string-topS">Отображена успеваемость для следующего студента: </p>
    </div>
</div>
<form action="marks" method="post" name ="f2" id="mark_f" >
<div class="stud-pro" align="center">
    <table id="table-term-dis" width="64%">
        <tr class="tr-proSt">
            <td class="tdS_surname">Фамилия</td>
            <td class="tdS_name">Имя</td>
            <td class="tdS_groupe">Группа</td>
            <td class="tdS_date">Дата поступления</td>
        </tr>
        <tr>
            <td class="tdS_surname">${pro_surname}</td>
            <td class="tdS_name">${pro_name}</td>
            <td class="tdS_groupe">${pro_groupe}</td>
            <td class="tdS_date">${pro_date}</td>
        </tr>
    </table>
</div >
<div style="white-space: nowrap" align="center" id="ac_dc" >
    <div style=" display: inline-block" class="Pro" >
        <table id="stPro_tab" >
            <tr class="tr-proSt" valign="middle">
                <td class="tdS_mark2">Дисциплина</td>
                <td class="tdS_mark3">Оценка</td>
            </tr>
            <c:forEach items="${pro_termDisc}" var="termDis">
                <tr >
                    <td class="tdS_mark2"><c:out value="${termDis.discipline}"></c:out></td>
                    <td class="tdS_mark3"> <input class="input-stringM" type="text" size="4" name="mark_dis" maxlength="2"
                        <c:forEach items="${pro_markStud}" var="marks">
                            <c:if test="${marks.id_discipline == termDis.id}">
                                 value="${marks.mark}"</c:if>
                        </c:forEach>>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div style="display: inline-block" id="prog_term" class="Pro">

            <p align="center" id="tr-prog">Внесите оценки в ячейки и нажмите "Сохранить"</p>
            <p align="center"> <input type="submit" class="mark_but" value="Сохранить" name="redact" onclick="return markTest(mark_dis)"> </p>

    </div>
</div>
</form>
