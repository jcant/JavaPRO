<%@ page import="com.gmail.gm.jcant.javaPro.Question" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: jcant
  Date: 30.04.18
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Answers</title>
</head>
<body>
    <% String name = (String)session.getAttribute("name");
        String surname = (String)session.getAttribute("surname");
        String age = (String)session.getAttribute("age"); %>

    Thank You, <%=name%> <%=surname%>(age=<%=age%>)!

    There are results:

    <% Map<String, Question> questions = (Map<String, Question>) session.getAttribute("questions"); %>
    <% Set<String> ids = questions.keySet(); %>
    <% for (String id : ids) { %>
        <% Question q = questions.get(id);%>
    <p>
        <div><%=id%>: "<%=q.getText()%>"</div>
        <div> YES = <%=q.getCountYes()%> NO = <%=q.getCountNo()%></div>
    </p>
    <% } %>
</body>
</html>
