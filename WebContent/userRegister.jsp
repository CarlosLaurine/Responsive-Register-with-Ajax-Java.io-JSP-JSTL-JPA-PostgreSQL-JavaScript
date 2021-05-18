<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Register</title>
<link href="Resources/CSS/register.css" rel="stylesheet" type="text/css">
<link rel="icon" href="Resources/Imgs/phone-icon.png">	

<!-- Adding JS Library JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

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
	<!-- Using enctype="multipart/form-data" HTML Event to define this Form as an Upload-Content Form to send the Uploaded File for further Treatment at the Server-Side-->

	<form action="saveUser" method="post" id="registerForm"
		onsubmit=" return validateFields(); " enctype="multipart/form-data">


		<ul class="form-style-1">
			<li>

				<table class="form-style-1">
					<tr>
						<td><input type="text" id="id" name="id"
							value="${selectedUser.id}" readonly></td>
						<!-- Using "onblur" event to call JQuery/Ajax Function zipQuery() as soon as its input box's focus is blurred -->

						<td><input type="text" id="zip" name="zip"
							onblur="zipQuery()" value="${selectedUser.zip}"
							placeholder="Insert Brazilian Zipcode"></td>
					</tr>

					<tr>

						<td><input type="text" id="login" name="login"
							placeholder="Insert Username" value="${selectedUser.name}"></td>

						<td><input type="text" id="street" name="street"
							value="${selectedUser.street}" placeholder="Insert Street"></td>
					</tr>

					<tr>


						<td><input type="password" id="password" name="password"
							placeholder="Insert Password" value="${selectedUser.password}"></td>

						<td><input type="text" id="neighborhood" name="neighborhood"
							value="${selectedUser.neighborhood}"
							placeholder="Insert current Neighborhood"></td>
					</tr>

					<tr>

						<td><input type="text" id="realName" name="realName"
							placeholder="Insert Complete Name"
							value="${selectedUser.realName}"></td>

						<td><input type="text" id="city" name="city"
							value="${selectedUser.city}" placeholder="Insert City"></td>
					</tr>

					<tr>

						<td><input type="text" id="phone" name="phone"
							placeholder="Insert A Contact Phone"
							value="${selectedUser.phone}"></td>

						<td><input type="text" id="state" name="state"
							value="${selectedUser.state}" placeholder="Insert Current State"></td>
					</tr>

					<tr>
						<td><input type="text" id="ibge" name="ibge" readonly
							value="${selectedUser.ibgeCode}"
							placeholder="IBGE ZIPCODE(LOCKED)"></td>
						<td><input type="file" name="photo" id="photo"></td>
					</tr>


					<tr>
						<center>
							<td><input type="submit" value="Submit"
								class="header table-header"
								style="float: right; margin-right: 2em; margin-top: 2.5em;">
							</td>



							<td>
								<!-- Using JavaScript Feature "onclick" to use DOM Concept to change the Form Action and Parameters submitted to the User Servlet -->
								<input type="submit" value="Reset" class="header table-header"
								style="margin-left: 1.5em; margin-top: 2.5em;"
								onclick="document.getElementById('registerForm').action='saveUser?reset=reset'">
							</td>
						</center>



					</tr>

				</table>

			</li>

		</ul>
	</form>

	<center>
		<h1 class="header table-header" style="margin-bottom: 3em;">Registered
			Users</h1>
	</center>

	<div class="container2">
		<ul class="responsive-table2">


			<li class="table-header">

				<div class="col col-1">ID</div>
				<div class="col col-1">USERNAME</div>
				<div class="col col-1">NAME</div>
				<div class="col col-1">PHONE</div>
				<div class="col col-1">DELETE</div>
				<div class="col col-1">EDIT</div>
				<div class="col col-1">PHONES</div>

			</li>
		</ul>
	</div>

	<div class="container1">
		<table class="responsive-table1">

			<c:forEach items="${users}" var="user">

				<tr>
					<td class="firstCol"><c:out value="${user.id}"></c:out></td>
					<td class="firstCol"><c:out value="${user.name}"></c:out></td>
					<td class="firstCol"><c:out value="${user.realName}"></c:out></td>
					<td class="firstCol"><c:out value="${user.phone}"></c:out></td>
					<!-- "alt" is used to put a text in the place of a image in case it do not load -->
					<!-- "title" is used to pop up a message whenever the cursor is upon the image -->
					<td class="firstCol"><a href="deleteUser?user=${user.id}"><img
							src="Resources/Imgs/delete-icon.png" alt="Delete" title="Delete"
							width="20px" height="20px"></a></td>
					<td class="firstCol"><a href="editUser?user=${user.id}"><img
							src="Resources/Imgs/edit-icon.png" alt="Edit" title="Edit"
							width="20px" height="20px"></a></td>
					<td class="firstCol"><a
						href="savePhone?action=addPhone&user=${user.id}"><img
							src="Resources/Imgs/phones-icon.png" alt="Phones" title="Phones"
							width="20px" height="20px"></a></td>
				</tr>

			</c:forEach>

		</table>
	</div>

	<!-- Inserting JS Function to Validate Obligatory Fields -->
	<script type="text/javascript">
		function validateFields() {

			if (document.getElementById("login").value == "") {
				alert("Insert Login!");
				return false
			}
			if (document.getElementById("password").value == "") {
				alert("Insert Password!");
				return false
			}
			if (document.getElementById("realName").value == "") {
				alert("Insert Real Name!");
				return false
			}
			if (document.getElementById("phone").value == "") {
				alert("Insert Phone!");
				return false
			}
			return true

		}

		//JS (JQuery+Ajax) Function to use webservices to search for a chosen Zip Code and auto-complete the information input boxes with the respective location data
		function zipQuery() {

			//Catching the ZIPCODE input by id with JQuery ($("#x") and .val())		
			var zip = $("#zip").val()

			//Consulting viacep.com.br webservice/
			$.getJSON("https://viacep.com.br/ws/" + zip + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Updating the respective Input Fields with the webservice returned values for the ZIP input 
							$("#street").val(dados.logradouro);
							$("#neighborhood").val(dados.bairro);
							$("#city").val(dados.localidade);
							$("#state").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} //end if.
						else {
							//Searched ZIP was not Found (Cleaning Form)
							$("#zip").val('');
							$("#street").val('');
							$("#neighborhood").val('');
							$("#city").val('');
							$("#state").val('');
							$("#ibge").val('');
							alert("ZIP CODE NOT FOUND!");
						}
					})
		}
	</script>

</body>
</html>