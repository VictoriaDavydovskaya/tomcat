<html>
<body>

<h2>All users</h2><br />

<c:forEach var="user" items="${requestScope.users}">
    <ul>

        <li>Name<c:out value="${user.name}"/></li>
        <li>Surname<c:out value="${user.surname}"/></li>
        <li>Age<c:out value="${user.age}"/></li>
    </ul>
    <hr />

</c:forEach>

</body>
</html>
