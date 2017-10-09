<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Employee</title>
</head>
<body>
<h2>updatalala</h2>
<form action = "EmployeeControllerServlet" method="GET">
	<input type = "hidden" name = "command" value="UPDATE"/>
	<input type ="hidden" name= "employeeid" value = "${currentEmployee.id}"/>
	<table>
		<tr>
			<td>First Name: </td>
			<td><input type = "text" name = "firstName" value="${currentEmployee.firstName}"/></td>			
		</tr>
		<tr>
			<td>Last Name: </td>
			<td><input type = "text" name = "lastName" value="${currentEmployee.lastName}"/></td>			
		</tr>
		<tr>
			<td>Company: </td>
			<td><input type = "text" name = "company" value="${currentEmployee.company}"/></td>			
		</tr>
	</table>
	<br/>
	<input type = "submit" value = "Update"/>

</form>
</body>
</html>