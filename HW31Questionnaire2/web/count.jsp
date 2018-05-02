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
    <% String name = (String)request.getParameter("name");
        String surname = (String)request.getParameter("surname");
        String age = (String)request.getParameter("age"); %>

    Thank You, <%=name%> <%=surname%>(age=<%=age%>)!

    There are results:

    <% Map<String, Question> questions = (Map<String, Question>) request.getAttribute("questions"); %>
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
