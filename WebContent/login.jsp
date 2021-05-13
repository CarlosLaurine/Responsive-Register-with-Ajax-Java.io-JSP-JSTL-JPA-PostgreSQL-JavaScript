
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="Resources/CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class='bold-line'></div>
	<div class='container'>
		<div class='window'>
			<div class='overlay'></div>
			<div class='content'>
				<div class='input-fields'>
				<img alt="User Icon" src="Resources/Imgs/user-icon.png" class="icon">
				
				<% if(request.getAttribute("denied") != null){ %>
					
					<h1 class="header"><%=request.getAttribute("denied") %></h1>
					
					
				<%} else{%>
					
					<h1 class="header">Login</h1>
					
				 <%}%>

					<form action="login" method="post">

						<input type="text" id="name" name="name" placeholder='Username'
							class='input-line full-width'> 
						<input type="password"
							id="password" name="password" placeholder='Password'
							class='input-line full-width bottom'>
						<div>
							<input type="submit" value="Submit"
								class='ghost-round full-width'>
						</div>


					</form>
				</div>



			</div>
		</div>
	</div>
</body>
</html>