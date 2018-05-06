<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL Sample - Results</title>
</head>
<body>
    <c:if test="${brand ne null}">
        <h1><c:out value="${brand}"/> orders list:</h1>
    </c:if>
    <c:if test="${price ne null}">
        <h1>Cheap orders list:</h1>
    </c:if>
    <c:if test="${brand eq null && price eq null}">
        <h1>All orders list:</h1>
    </c:if>

    <table border="1">
        <c:forEach items="${ordersList}" var="o">
            <tr>
                <td><c:out value="${o.name}"/></td>
                <td><c:out value="${o.price}"/></td>
            </tr>
        </c:forEach>
    </table>

    <br/><a href="/">Go Back</a>
</body>
</html>