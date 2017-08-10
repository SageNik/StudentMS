<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 13.05.2016
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="white-space: nowrap" id="term-div_top">
    <div style="display: inline-block" class="top-link_back-string" >
        <table >
            <tr>
                <td class="back"><a href="../home"> На главную </a></td>
                <td class="back"><a href="../disciplines"> Назад </a></td>
            </tr>
        </table>
    </div>
    <div style="display: inline-block" id="term-top">
        <p id="string-top">Для того чтобы создать дисциплину заполните все поля и нажмите кнопку "Создать"</p>
    </div>
</div>
<div class="table-disc_create">
<form action="dcreate" method="post">
    <table align="left">
        <c:if test="${VALID_MESSAGE == true}"><tr>
            <td colspan="2" class="error_mess2"><p style="color: red">Поля не должны быть пустыми!</p></td>
        </tr></c:if>
        <tr>
            <td class="disc-td_cr1">Название</td>
            <td class="disc-td_cr2"><input class="input-string" type="text" size="10" name="ddiscipline" maxlength="30"></td>
        </tr>
        <tr>
            <td class="disc-td_cr1"></td>
            <td class="disc-td_cr2"><input class="button_term" type="submit" value="Создать" name="create_d" id="button_cr"></td>
        </tr>
    </table>

</form>
</div>