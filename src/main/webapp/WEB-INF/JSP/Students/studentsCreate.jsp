<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 09.05.2016
  Time: 13:45
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
        <p id="string-top">Для создания студента заполните все поля и нажмите кнопку "Создать"</p>
    </div>
</div>
<div class="table-stud_create">
    <form action="screate" method="post">
        <table align="left">
            <c:if test="${VALID_MESSAGE == true}"><tr>
                <td colspan="2" class="error_mess"><p style="color: red">Поля не должны быть пустыми!</p></td>
            </tr></c:if>
            <tr>
                <td class="stud-td_cr1">Фамилия</td>
                <td class="term-td_cr2"><input class="input-string" type="text" size="20" name="surname" maxlength="30"></td>
            </tr>
            <tr>
                <td class="stud-td_cr1">Имя</td>
                <td class="term-td_cr2"><input class="input-string" type="text" size="20" name="name" maxlength="30"></td>
            </tr>
            <tr>
                <td class="stud-td_cr1">Группа</td>
                <td class="term-td_cr2"><input class="input-string" type="text" size="20" name="groupe" maxlength="30"></td>
            </tr>
            <tr>
                <td class="stud-td_cr1">Дата поступления</td>
                <td class="term-td_cr2"><input class="jdpicker" type="text" name="date"></td>
            </tr>
            <tr>
                <td class="stud-td_cr1"></td>
                <td class="term-td_cr2"><input class="button_stud" type="submit" value="Создать" name="create_term" ></td>
            </tr>
        </table>

    </form>
</div>