<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Phone Register</title>
<link href="Resources/CSS/register.css" rel="stylesheet" type="text/css">

</head>
<body>

	<a href="index.jsp">Exit</a>
	<a href="accessPermitted.jsp">Home</a>

	<!-- Using Tag "center" to centralize the Header -->
	<center>
		<h1 class="header table-header" style="margin-bottom: -15px">User
			Register</h1>
		<h3 class="header table-header msg" style="color: #DC143C">
			<c:out value="${msg}"></c:out>
		</h3>
	</center>

	<!-- Setting Form's Id for further use of JavaScript -->
	<!-- Using JS "onsubmit" event returning a boolean value based on an inner JS Function to decide whether the action will be submitted to User Servlet or be previously interrupted at View Layer  -->
	<form action="savePhone" method="post" id="registerForm2"
		onsubmit=" return validateFields() ">


		<ul class="form-style-1">
			<li>

				<table class="form-style-1">
					<tr>

						<td><input type="text" id="id" name="id" readonly
							placeholder="USER ID (READ-ONLY VALUE)" style="float:both;"
							value="${selectedUser.id}">

						   <input type="text" id="name" name="name" readonly
							placeholder="USER NAME (READ-ONLY VALUE)" style="float:both;"
							value="${selectedUser.name}"></td>
					</tr>
					<tr>

						<td><input type="text" id="number" name="number"
							placeholder="Phone Number">


						<select id="type" name="type">
								<option></option>
								<option>Landline</option>
								<option>Contact</option>
								<option>Cellphone</option>

						</select></td>

					</tr>

					<tr><td><br/></td></tr>

					<tr>
						
						<td><input type="submit" value="Submit"
							class="header table-header" style="float: both; margin-left: 4.8em;"></td>
					</tr>

				</table>

			</li>

		</ul>
	</form>

	<center>
		<h1 class="header table-header">Registered Users</h1>
	</center>

	<div class="container2">
		<ul class="responsive-table2">


			<li class="table-header">

				<div class="col col-1">ID</div>
				<div class="col col-1">NUMBER</div>
				<div class="col col-1">TYPE</div>
				<div class="col col-1">DELETE</div>

			</li>
		</ul>
	</div>

	<div class="container1">
		<table class="responsive-table1">

			<c:forEach items="${phones}" var="phone">

				<tr>
					<td class="firstCol"><c:out value="${phone.id}"></c:out></td>
					<td class="secondCol"><c:out value="${phone.number}"></c:out></td>
					<td class="secondCol"><c:out value="${phone.type}"></c:out></td>

					<!-- "alt" is used to put a text in the place of a image in case it do not load -->
					<!-- "title" is used to pop up a message whenever the cursor is upon the image -->
					<td class="secondCol"><a
						href="deletePhone?action=deletePhone&phoneId=${phone.id}"><img
							src="Resources/Imgs/delete-icon.png" alt="Delete" title="Delete"
							width="20px" height="20px"></a></td>
				</tr>

			</c:forEach>

		</table>
	</div>
	<!-- Inserting JS Function to Validate Obligatory Fields -->
	<script type="text/javascript">
		function validateFields() {

			if (document.getElementById("number").value == "") {
				alert("Insert Number!");
				return false
			} else if (document.getElementById("type").value == "") {
				alert("Insert Type!");
				return false
			}
			return true

		}
	</script>

</body>
</html>