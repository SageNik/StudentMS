<%--
  Created by IntelliJ IDEA.
  User: Ник
  Date: 05.05.2016
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.apache.commons.lang.exception.ExceptionUtils"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<style type="text/css">
    body {
        color:#222;
        background:#fff  left top repeat-x;}
    h1 {
        font-size:300%;font-family:'Trebuchet MS', Verdana, sans-serif; color:#000}
    #page {font-size:122%;width:720px; margin:144px auto 0 auto;text-align:left;line-height:1.2;}
    .data_container_for_not_found {min-height:360px;background:transparent right top no-repeat;}
</style>
<%
    String status = String.valueOf(request
            .getAttribute("javax.servlet.error.status_code"));

    String errorMessage = null;
    String fullStackTrace = null;
    Exception exception = null;

    if ("404".equals(status.trim()) || "true".equals(request.getAttribute("notfound"))) {
        errorMessage = " Page not found.";
    } else {
        errorMessage = String.valueOf(request
                .getAttribute("javax.servlet.error.message"));
        exception = (Exception) request
                .getAttribute("javax.servlet.error.exception");
        if (exception != null) {
            errorMessage = exception.getMessage();
            fullStackTrace = ExceptionUtils
                    .getFullStackTrace(exception).replace('\t', ' ')
                    .trim();
        }
    }
%>

<div class="data_container_for_not_found">
    Error<br /> Message :
    <%=errorMessage%>
    <br />
    <%
        if (fullStackTrace != null) {
    %>
    Full stack trace :
    <%=fullStackTrace%>
    <br />
    <%
        }
    %>
</div>

