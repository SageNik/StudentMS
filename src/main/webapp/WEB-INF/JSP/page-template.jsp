<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8"/>

        <link href="/css/style.css" rel="stylesheet" type="text/css"  />
    <script src="/Scripts/scripts.js"></script>

    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

    <script type ="text/javascript" src="/Scripts/jquery.jdpicker.js"></script>
    <link rel="stylesheet" href="/css/jdpicker.css" type="text/css"  />

    <title>${title}</title>



</head>
<header>
    <div id="block-header" >
        <div  >
            <table align = "center" >
                <tr >
                    <td id="block-top" >
                        <p><h2> Система управления студентами и их успеваемостью</h2></p>
                    </td>
                    <td id="block-top-link">  <c:if test="${CURRENT_SESSION_ACCOUNT ne null}">
                        <a href="/logout"> Logout</a>
                    </c:if>
                    </td>
                </tr>
            </table>

        </div>


    </div>
</header>
<body>

<jsp:include page="${link}" />
</body>
</html>
