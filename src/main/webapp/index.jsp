<html>
<head>
    <meta charset="utf-8">
</head>

<body>

<h2>All users</h2><br />

<c:forEach var="user" items="${requestScope.users}">
    <ul>

        <li>Имя: <c:out value="${user.name}"/></li>

        <li>Возраст: <c:out value="${user.age}"/></li>
    </ul>
    <hr />

</c:forEach>

</body>
</html>
