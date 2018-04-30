<%--
  Created by IntelliJ IDEA.
  User: jcant
  Date: 30.04.18
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Questionnaire</title>
</head>
<body>
<form action="/count" method="post">
    Do you like Java?
    <ul>
        <li><input type="radio" name="question1" value="YES"/>YES</li>
        <li><input type="radio" name="question1" value="NO"/>NO</li>
    </ul>
    Do you like .NET?
    <ul>
        <li><input type="radio" name="question2" value="YES"/>YES</li>
        <li><input type="radio" name="question2" value="NO"/>NO</li>
    </ul>
    <input type="submit" value="submit"/>
</form>
</body>
</html>