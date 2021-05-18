<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="A layout example that shows off a responsive product landing page.">
<link rel="icon" href="Resources/Imgs/phone-icon.png">	
<title>Responsive JSP/Ajax/JPA Table Project (Home)</title>
<link rel="stylesheet" href="Resources/CSS/homePageStyle.css">
<link rel="stylesheet" href="/css/pure/pure-min.css">
<link rel="stylesheet" href="/css/pure/grids-responsive-min.css">
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="/layouts/marketing/styles.css">
</head>
<body>


	<div class="splash-container">
		<div class="splash">
			<h1 class="splash-head" style="margin-top: -3.3em;">Complete Web
				Registration (CRUD) with JSP/PostgreSQL DataBase and Ajax
				Webservices</h1>
			<p class="splash-subhead">As you scroll this Page down, some
				further Information on this Project's Development will appear</p>
			<p>
				<a href="login.jsp"
					class="pure-button pure-button-primary homeButton"
					style="text-decoration: none; border: 2px dotted black"> Go to
					the Project </a>
			</p>
			<p class="splash-subhead" style="text-transform: none;'">
				OBS: For Login Filter Verification purposes, The First-Access Login
				Account is => <br /> User: adm | Password: adm
			</p>
			<br />
		</div>
	</div>

	<div class="content-wrapper">
		<div class="content">
			<h2 class="content-head is-center">Technologies Covered</h2>

			<div class="pure-g">
				<div class="l-box pure-u-1 pure-u-md-1-2 pure-u-lg-1-4">

					<h3 class="content-subhead">
						<i class="fa fa-rocket"></i> Java Servlet Specification
					</h3>
					<p>Subtopics such as HTTP Protocol (Request/Response), Session
						Scopes, Parameters, Attributes and Web Filters (Specially the
						Login ones) were explored</p>
				</div>
				<div class="l-box pure-u-1 pure-u-md-1-2 pure-u-lg-1-4">
					<h3 class="content-subhead">
						<i class="fa fa-th-large"></i> JDBC Connection with PostgreSQL
						Database
					</h3>
					<p>Subtopics such as Singleton Architecture (Connection Class),
						JPA API Class Management (Dao Classes), Custom Exceptions (For
						Inappropriate DataBase Transactions), JUnit Tests and SQL Language
						were applied</p>
				</div>
				<div class="l-box pure-u-1 pure-u-md-1-2 pure-u-lg-1-4">
					<h3 class="content-subhead">
						<i class="fa fa-th-large"></i> Web Services
					</h3>
					<p>A ZIPCode API was consumed with AJAX inside the Application
						in order to Auto-Fulfill the Register's Address Fields once the
						ZIP Code is entered</p>
				</div>
				<div class="l-box pure-u-1 pure-u-md-1-2 pure-u-lg-1-4">
					<h3 class="content-subhead">
						<i class="fa fa-th-large"></i> Java.IO API Package
					</h3>
					<p>Its Applications were explored in a Variety of Features such
						as: Image/PDF Base64 Conversion, File Upload/Download and Database
						Storage/Query</p>
				</div>

				<p>
					<a
						href="https://github.com/CarlosLaurine/Responsive-Register-with-Ajax-Java.io-JSP-JSTL-JPA-PostgreSQL-JavaScript"
						class="pure-button pure-button-primary homeButton " target="_blank"
						style="text-decoration: none; text-align: center; background-color: black; color: white;">
						Go to the Project's GitHub Repository </a>
				</p>

				<br /> <br />

			</div>
		</div>

		<div class="footer l-box is-center" style="color: #FFF;">
			Developed By Carlos Laurine Dev || <a
				href="https://github.com/CarlosLaurine" style="color: #FFF;">Developer's
				Github</a>
		</div>

	</div>

</body>
</html>