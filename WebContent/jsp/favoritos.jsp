<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.UsuarioModel"%>
<%@page import="Models.CiudadModel"%>
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
						<li class="active"><a href="/CityWeatherWebApp/favoritos?email=<jsp:getProperty property="email" name="usuario"/>">Ciudades favoritas</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a><jsp:getProperty property="nombreUsuario" name="usuario"/></a></li>
						<li><a href="entrar?action=logout">Logout</a></li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="contenedor_contenido">
			<div class="row">
				<div class="col-xs-12">
					<div class="well_transparente">
						<h1>Estas son tus ciudades favoritas <jsp:getProperty property="nombreUsuario" name="usuario"/></h1>
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<td><strong>Ciudad</strong></td>
										<td><strong>Tiempo</strong></td>
										<td><strong>Temperatura actual</strong></td>
										<td><strong>Temperatura m&aacute;xima</strong></td>
										<td><strong>Temperatura m&iacute;nima</strong></td>
										<td><strong>Velocidad del viento</strong></td>
										<td><strong>Humedad</strong></td>
										<td><strong>Quitar de favoritos</strong></td>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
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