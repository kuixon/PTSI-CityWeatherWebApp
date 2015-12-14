<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.UsuarioModel"%>
<%@page import="Models.CiudadModel"%>
<jsp:useBean id="usuario" class="Models.UsuarioModel" scope="session"></jsp:useBean>
<jsp:useBean id="ciudad" class="Models.CiudadModel" scope="session"></jsp:useBean>
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
							<li><a href="/CityWeatherWebApp/index">Inicio<span class="sr-only">(current)</span></a></li>
							<li><a href="#">Ciudades favoritas</a></li>
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
						<%
					}
					%>
				</div>
			</nav>
		</div>
		<div class="contenedor_contenido">
			<div class="row">
				<div class="col-xs-4">
					<div class="well_transparente">
						<h1>Sobre <jsp:getProperty property="nombreCiudad" name="ciudad"/></h1>
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<td><strong>Informaci&oacute;n</strong></td>
										<td><strong>Enlace</strong></td>
									</tr>
								</thead>
								<tbody>
									<tr class="info">
										<td>Informaci&oacute;n de la ciudad</td>
										<td><a href="https://es.wikipedia.org/wiki/<jsp:getProperty property="nombreCiudad" name="ciudad"/>" target="_blank">Link</a></td>
									</tr>
									<tr class="success">
										<td>Noticias de la ciudad</td>
										<td><a href="http://edition.cnn.com/search/?text=<jsp:getProperty property="nombreCiudad" name="ciudad"/>" target="_blank">Link</a></td>
									</tr>
									<tr class="warning">
										<td>Busqueda general</td>
										<td><a href="https://www.google.es/#q=<jsp:getProperty property="nombreCiudad" name="ciudad"/>" target="_blank">Link</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<h1>Sobre la situaci&oacute;n metereol&oacute;gica actual</h1>
						<ul class="list-group">
							<li class="list-group-item list-group-item-success">
								<h4 class="list-group-item-heading">Tiempo</h4>
								<p class="list-group-item-text"><jsp:getProperty property="situacionMetActual" name="ciudad"/></p>
							</li>
							<li class="list-group-item list-group-item-info">
								<h4 class="list-group-item-heading">Otro dato</h4>
								<p class="list-group-item-text">Valor de prueba.</p>
							</li>
							<li class="list-group-item list-group-item-warning">
								<h4 class="list-group-item-heading">Otro dato</h4>
								<p class="list-group-item-text">Valor de prueba.</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<h1>Favoritos</h1>
						<%
						
						if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContraseña().isEmpty()) {
							CiudadModel cm = (CiudadModel) session.getAttribute("ciudad");
							
							if (cm.isFavoritos()) {
								%>
								<h3>Pulsa el siguiente bot&oacute;n para quitar la ciudad '<jsp:getProperty property="nombreCiudad" name="ciudad"/>' de tu lista de favoritos.</h3>
								<a href="#" class="btn btn-default">Quitar ciudad</a>
								<%
							} else {
								%>
								<h3>Pulsa el siguiente bot&oacute;n para añadir la ciudad '<jsp:getProperty property="nombreCiudad" name="ciudad"/>' a tu lista de favoritos.</h3>
								<a href="#" class="btn btn-default">Añadir ciudad</a>							
								<%
							}	
						} else {
							%>
							<h3>Inicia sesi&oacute;n para poder ver el apartado de favoritos.</h3>
							<%
						}
						%>
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