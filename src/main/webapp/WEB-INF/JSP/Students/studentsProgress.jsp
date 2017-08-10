<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 16.05.2016
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="white-space: nowrap" id="term-div_top">
    <div style="display: inline-block" class="top-link_back-string" >
        <table >
            <tr>
                <td class="back"><a href="../home"> На главную </a></td>
                <td class="back"><a href="../students"> Назад </a></td>
            </tr>
        </table>
    </div>
    <div style="display: inline-block" id="term-top">
        <p id="string-topS">Отображена успеваемость для следующего студента: </p>
    </div>
</div>
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
              <td class="tdS_disc">Дисциплина</td>
              <td class="tdS_mark">Оценка</td>
          </tr>
          <c:forEach items="${pro_termDisc}" var="termDis">
          <tr >
              <td class="tdS_disc"><c:out value="${termDis.discipline}"></c:out></td>
              <td class="tdS_mark">
                  <c:forEach items="${pro_markStud}" var="marks">
                      <c:if test="${marks.id_discipline == termDis.id}">
                      <c:out value="${marks.mark}"></c:out></c:if>
                  </c:forEach></td>
          </tr>
          </c:forEach>
      </table>
  </div>
    <div style="display: inline-block" id="prog_term" class="Pro">
        <form action="progress" method="post" >
            <table id="term-tableS" >
                <tr class="tr-term">
                    <td class="td-term_1">Выбрать семестр</td>
                    <td class="td-term_2"><select name="termP" id="opening_term">
                        <c:forEach items="${pro_terms}" var="term">
                            <option class="option_term" value="${term.id}">Семестр ${term.id}</option>
                        </c:forEach>
                    </select></td>
                    <td class="td-term_3"><input class="button_term" type="submit" value="выбрать" name="enter" ></td>
                </tr>
       </table>
            <p align="center" id="tr-prog">Средняя оценка за семестр: ${aver_mark} балла</p>
            <c:if test="${CURRENT_ROLE == 1}"><p align="center"> <input type="submit" class="mark_but" value="Редактировать оценки" name="redact" onclick="return selectTerm(bools)"> </p>
            </c:if>
</form>
        </div>
</div>