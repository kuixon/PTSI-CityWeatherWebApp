<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="usuario" class="Models.UsuarioModel" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>City Weather Web App</title>
		<meta charset="ISO-8859-1">
		<link rel="icon" type="image/png" href="./img/page-icon.png" />
		<link type="text/css" rel="stylesheet" href="./css/cityweatherwebapp.css" />
		<link type="text/css" rel="stylesheet" href="./css/bootstrap.min.css" />
		<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	</head>
	<body>
		<div>
			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container-fluid">
					<ul class="nav navbar-nav navbar-left">
						<li><a class="navbar-brand" href="/CityWeatherWebApp/index">City Weather Web App</a></li>
						<li><a href="/CityWeatherWebApp/index">Inicio<span class="sr-only">(current)</span></a></li>
						<li><a href="#">Ciudades favoritas</a></li>
					</ul>
					<%
					String nombreUsuario = (String) session.getAttribute("nombreUsuario");
					if (nombreUsuario != null) {
					%>
					<ul class="nav navbar-nav navbar-right">
						<li><a><%= nombreUsuario %></a></li>
						<li><a href="entrar?action=logout">Logout</a></li>
					</ul>
					<%	
					}
					%>
				</div>
			</nav>
		</div>
		<div class="contenedor_contenido">
			<div class="row">
				<div class="col-xs-4"></div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<div class="alert alert-success" role="alert">
							<h1>¡Enhorabuena <jsp:getProperty property="nombreUsuario" name="usuario"/>!</h1>
							<h3>Has realizado el login correctamente</h3>
						</div>
					</div>
				</div>
				<div class="col-xs-4"></div>
			</div>
		</div>
		<div>
			<nav class="navbar navbar-inverse navbar-fixed-bottom">
				<div class="container-fluid">
					<ul class="nav navbar-nav navbar-left">
						<li><a class="navbar-brand"><img alt="icono copyright" height="23" width="23" src="./img/copyright-icon.png"></a></li>
						<li><p class="navbar-text">Creado por Endika Salgueiro Barqu&iacute;n</p></li>
					</ul>
				</div>
			</nav>
		</div>
	</body>
</html>