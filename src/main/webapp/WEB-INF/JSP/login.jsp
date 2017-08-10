<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 05.05.2016
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div align="right" style="position: fixed; right: 20px; bottom: 0px;">
    <p>Тестовые аккаунты: "admin" , "student"</p>
    <p>Пароли смотрите в скрипте базы</p>
</div>

<div class="login_form_container" align="center">
    <form action="${CONTEXT }/login" method="post">
<p align="center"font-weight="bold" font-size="30pt">Вход</p>
        <table id="login_table">

            <c:if test="${VALID_MESSAGE == true}"><tr>
                <td colspan="2" class="log_error_mess"><p style="color: red">Не верное имя, пароль или роль!</p></td>
            </tr></c:if>
            <tr>
                <td>Имя:</td>
                <td><input  type="text" name="username" class="text" value="" /></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><input  type="password" name="password" class="text" /></td>
            </tr>
            <tr>
                <td>Выбрать роль:</td>

                <td><select name="role" id="opening_list">
                    <c:forEach items="${role}" var="rol">
                        <option value="${rol.id}">${rol.id}${rol.role}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr align="center">

                <td colspan="2"><input class="button_login" type="submit" value="войти" id="button" onclick="return loginTest(username, password)"></td>
            </tr>
        </table>

    </form>
</div>