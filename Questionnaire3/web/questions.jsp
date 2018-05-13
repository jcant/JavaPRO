<%@ page import="java.util.Map" %>
<%@ page import="com.gmail.gm.jcant.javaPro.Question" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: jcant
  Date: 30.04.18
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>
<form action="/count" method="post">

    <p>Please, introduсe yourself:</p>
    <p><input type="text" name="name" placeholder="Name"/></p>
    <p><input type="text" name="surname" placeholder="Surname"/></p>
    <p><input type="text" name="age" placeholder="Age" type="number"/></p>
    <p></p>
    <% Map<String, Question> questions = (Map<String, Question>) session.getAttribute("questions"); %>
    <% Set<String> ids = questions.keySet(); %>
    <% for (String id : ids) { %>
    <% Question q = questions.get(id);%>
    <%=q.getText()%>
    <ul>
        <li><input type="radio" name="<%=id%>" value="YES" checked="checked"/>YES</li>
        <li><input type="radio" name="<%=id%>" value="NO"/>NO</li>
    </ul>
    <% } %>

    <input type="submit" value="submit"/>
</form>
</body>
</html>