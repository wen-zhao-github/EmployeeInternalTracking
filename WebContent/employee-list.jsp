<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
</head>
<body>
<h2>lalalalalala</h2>
<form>
	<table border = "1px">
		<tr>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Company</td>
			<td>Operation</td>
		</tr>
		<c:forEach var = "temp" items = "${employees}">
			<c:url var = "updateLink" value = "EmployeeControllerServlet" >
				<c:param name = "command" value="LOAD"/>
				<c:param name = "employeeid" value = "${temp.id}"/>
			</c:url>
			<c:url var = "delteLink" value= "EmployeeControllerServlet">
				<c:param name = "command" value = "DELETE"/>
				<c:param name = "employeeid" value="${temp.id}"/>
			</c:url>
		<tr>
			<td>${temp.firstName}</td>
			<td>${temp.lastName}</td>
			<td>${temp.company}</td>
			<td><a href = "${updateLink}">UPDATE</a> | <a href="${delteLink}">DELETE</a></td>
		</tr>
		</c:forEach>
	</table>
</form>

<br/><br/>
<a href = "add-new-employee.html">Add New Employee</a>
</body>
</html>