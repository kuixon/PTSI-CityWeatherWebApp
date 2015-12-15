<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.UsuarioModel"%>
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
					<%
					UsuarioModel um = (UsuarioModel) session.getAttribute("usuario");
					
					if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContraseña().isEmpty()) {
						%>
						<ul class="nav navbar-nav navbar-left">
							<li><a class="navbar-brand" href="/CityWeatherWebApp/index">City Weather Web App</a></li>
							<li class="active"><a href="/CityWeatherWebApp/index">Inicio<span class="sr-only">(current)</span></a></li>
							<li><a href="/CityWeatherWebApp/favoritos?email=<jsp:getProperty property="email" name="usuario"/>">Ciudades favoritas</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a><jsp:getProperty property="nombreUsuario" name="usuario"/></a></li>
							<li><a href="entrar?action=logout">Logout</a></li>
						</ul>
						<%
					} else {
						%>
						<ul class="nav navbar-nav navbar-left">
							<li><a class="navbar-brand" href="/CityWeatherWebApp/index">City Weather Web App</a></li>
							<li class="active"><a href="/CityWeatherWebApp/index">Inicio<span class="sr-only">(current)</span></a></li>
						</ul>
						<div class="nav navbar-nav navbar-right">
							<div class="btn-group">
								<a type="button" class="btn btn-success navbar-btn" href="/CityWeatherWebApp/entrar">Entrar</a>
							</div>
							<div class="btn-group">
								<a type="button" class="btn btn-danger navbar-btn" href="/CityWeatherWebApp/registro">Registrarse</a>
							</div>
						</div>
						<%
					}
					%>
				</div>
			</nav>
		</div>
		<div class="contenedor_contenido">
			<div class="row">
				<div class="col-xs-12">
					<div class="well_transparente">
						<div class="panel panel-primary">
							<div class="panel-heading">Buscador</div>
							<div class="panel-body">
								<h2 align="center">Busca la ciudad que desees para obtener su situaci&oacute;n meteorol&oacute;gica actual</h2>
								<%
								if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContraseña().isEmpty()) {
									%>
									<h3 align="center">Adem&aacute;s, podr&aacute;s añadir ciudades a tu lista de favoritos en caso de que dispongas ya de una cuenta.</h3>
									<%
								} else {
									%>	
									<h3 align="justify">Adem&aacute;s, podr&aacute;s añadir ciudades a tu lista de favoritos en caso de que dispongas ya de una cuenta. Si no es as&iacute;, <a href="/CityWeatherWebApp/registro">¡Reg&iacute;strate!</a></h3>
									<%
								}
								%>
								<form action="buscar" method="post">
									<div class="input-group">
										<input type="text" name="nombreCiudad" class="form-control" placeholder="Introduce el nombre de la ciudad, por ejemplo: Bilbao">
										<%
										if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContraseña().isEmpty()) {
											%>
											<input type="hidden" name="emailUsuarioLogin" value="<jsp:getProperty property="email" name="usuario"/>">
											<%
										} else {
											%>
											<input type="hidden" name="emailUsuarioLogin" value="">
											<%
										}
										%>
										<span class="input-group-btn">
									    	<button type="submit" class="btn btn-default">Buscar</button>
										</span>
									</div>
								</form>
							</div>
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