<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Update</title>
</head>
<body>

	<h1>User Update:</h1>


	<form action="editedUser" method="post">

		<table>
			<tr>
				<td>Id:</td>
				<td><input type="text" id="id" name="id"
					value="${selectedUser.id }" readonly></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input type="text" id="login" name="login"
					value="${selectedUser.name }"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" id="password" name="password"
					value="${selectedUser.password}"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>

		</table>


	</form>



</body>
</html>