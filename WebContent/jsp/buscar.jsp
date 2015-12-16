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
					
					if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContrase�a().isEmpty()) {
						%>
						<ul class="nav navbar-nav navbar-left">
							<li><a class="navbar-brand" href="/CityWeatherWebApp/index">City Weather Web App</a></li>
							<li><a href="/CityWeatherWebApp/index">Inicio<span class="sr-only">(current)</span></a></li>
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
						<h1>Sobre <jsp:getProperty property="nombre" name="ciudad"/></h1>
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
										<td><a href="https://es.wikipedia.org/wiki/<jsp:getProperty property="nombre" name="ciudad"/>" target="_blank">Link</a></td>
									</tr>
									<tr class="success">
										<td>Noticias de la ciudad</td>
										<td><a href="http://edition.cnn.com/search/?text=<jsp:getProperty property="nombre" name="ciudad"/>" target="_blank">Link</a></td>
									</tr>
									<tr class="warning">
										<td>Busqueda general</td>
										<td><a href="https://www.google.es/#q=<jsp:getProperty property="nombre" name="ciudad"/>" target="_blank">Link</a></td>
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
								<p class="list-group-item-text"><jsp:getProperty property="tiempo" name="ciudad"/></p>
							</li>
							<li class="list-group-item list-group-item-info">
								<h4 class="list-group-item-heading">Temperatura actual</h4>
								<p class="list-group-item-text"><jsp:getProperty property="temperatura" name="ciudad"/> C�</p>
							</li>
							<li class="list-group-item list-group-item-info">
								<h4 class="list-group-item-heading">Temperatura m&aacute;xima</h4>
								<p class="list-group-item-text"><jsp:getProperty property="temperaturaMaxima" name="ciudad"/> C�</p>
							</li>
							<li class="list-group-item list-group-item-info">
								<h4 class="list-group-item-heading">Temperatura m&iacute;nima</h4>
								<p class="list-group-item-text"><jsp:getProperty property="temperaturaMinima" name="ciudad"/> C�</p>
							</li>
							<li class="list-group-item list-group-item-danger">
								<h4 class="list-group-item-heading">Velocidad del viento</h4>
								<p class="list-group-item-text"><jsp:getProperty property="velocidadViento" name="ciudad"/> Km/h</p>
							</li>
							<li class="list-group-item list-group-item-warning">
								<h4 class="list-group-item-heading">Humedad</h4>
								<p class="list-group-item-text"><jsp:getProperty property="humedad" name="ciudad"/> %</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<h1>Favoritos</h1>
						<%
						if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContrase�a().isEmpty()) {
							CiudadModel cm = (CiudadModel) session.getAttribute("ciudad");
							
							if (cm.isFavoritos()) {
								%>
								<h3>Pulsa el siguiente bot&oacute;n para quitar la ciudad '<jsp:getProperty property="nombre" name="ciudad"/>' de tu lista de favoritos.</h3>
								<form action="quitarfavoritos" method="post">
									<input type="hidden" name="nombreCiudad" value="<jsp:getProperty property="nombre" name="ciudad"/>">
									<input type="hidden" name="emailUsuario" value="<jsp:getProperty property="email" name="usuario"/>">
									<button type="submit" class="btn btn-default">Quitar ciudad</button>
								</form>
								<%
							} else {
								%>
								<h3>Pulsa el siguiente bot&oacute;n para a�adir la ciudad '<jsp:getProperty property="nombre" name="ciudad"/>' a tu lista de favoritos.</h3>
								<form action="a�adirfavoritos" method="post">
									<input type="hidden" name="email" value="<jsp:getProperty property="email" name="usuario"/>">
									<input type="hidden" name="nombre" value="<jsp:getProperty property="nombre" name="ciudad"/>">
									<input type="hidden" name="tiempo" value="<jsp:getProperty property="tiempo" name="ciudad"/>">
									<input type="hidden" name="temperatura" value="<jsp:getProperty property="temperatura" name="ciudad"/>">
									<input type="hidden" name="temperaturaMaxima" value="<jsp:getProperty property="temperaturaMaxima" name="ciudad"/>">
									<input type="hidden" name="temperaturaMinima" value="<jsp:getProperty property="temperaturaMinima" name="ciudad"/>">
									<input type="hidden" name="velocidadViento" value="<jsp:getProperty property="velocidadViento" name="ciudad"/>">
									<input type="hidden" name="humedad" value="<jsp:getProperty property="humedad" name="ciudad"/>">
									<button type="submit" class="btn btn-default">A�adir ciudad</button>
								</form>							
								<%
							}	
						} else {
							%>
							<h3>Entra en el sistema para poder ver el apartado de favoritos.</h3>
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