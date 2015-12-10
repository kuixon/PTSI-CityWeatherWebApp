<%@page import="Database.DatabaseManager"%>
<%@page import="Models.UsuarioModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="usuario" class="Models.UsuarioModel" scope="session"></jsp:useBean>
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
					</ul>
					<div class="nav navbar-nav navbar-right">
						<div class="btn-group">
							<a type="button" class="btn btn-success navbar-btn" href="/CityWeatherWebApp/entrar">Entrar</a>
						</div>
						<div class="btn-group">
							<a type="button" class="btn btn-danger navbar-btn" href="/CityWeatherWebApp/registro">Registrarse</a>
						</div>
					</div>
				</div>
			</nav>
		</div>
		<div class="contenedor_contenido">
			<div class="row">
				<div class="col-xs-4"></div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<div class="alert alert-danger" role="alert">
							<h1 align="center">¡Error!</h1>
							<form action="entrar" method="get">
								<%
								UsuarioModel um = (UsuarioModel) session.getAttribute("usuario");
								
								if (!um.getNombreUsuario().equals("")) {
									%>
									<h3 align="justify">La contraseña introducida para el usuario con email '<jsp:getProperty property="email" name="usuario"/>' es incorrecta.</h3>
									<input type="hidden" name="email" value="<jsp:getProperty property="email" name="usuario"/>">
									<p>Pulsa el siguiente bot&oacute;n para volver al formulario:</p>
							  		<button type="submit" class="btn btn-default">Formulario entrada</button>
									<%
								} else {
									%>
									<h3 align="justify">No existe nig&uacute;n usuario en el sistema asociado al email '<jsp:getProperty property="email" name="usuario"/>'.</h3>
									<p>Pulsa el siguiente bot&oacute;n para volver al formulario:</p>
							  		<button type="submit" class="btn btn-default">Formulario entrada</button>
									<%
								}
								%>
							</form>
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