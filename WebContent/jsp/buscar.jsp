<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.UsuarioModel"%>
<%@page import="Models.CiudadModel"%>
<jsp:useBean id="usuario" class="Models.UsuarioModel" scope="session"></jsp:useBean>
<jsp:useBean id="ciudad" class="Models.CiudadModel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
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
							<li><a href="/CityWeatherWebApp/index">Home<span class="sr-only">(current)</span></a></li>
							<li><a href="/CityWeatherWebApp/html/doxygen/index.html" target="_blank">RESTFul API</a></li>
							<li><a href="/CityWeatherWebApp/favoritos?email=<jsp:getProperty property="email" name="usuario"/>">My Favorite Cities</a></li>
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
							<li><a href="/CityWeatherWebApp/index">Home<span class="sr-only">(current)</span></a></li>
							<li><a href="/CityWeatherWebApp/html/doxygen/index.html" target="_blank">RESTFul API</a></li>
						</ul>
						<div class="nav navbar-nav navbar-right">
							<div class="btn-group">
								<a type="button" class="btn btn-success navbar-btn" href="/CityWeatherWebApp/entrar">Sign In</a>
							</div>
							<div class="btn-group">
								<a type="button" class="btn btn-danger navbar-btn" href="/CityWeatherWebApp/registro">Sign Up</a>
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
						<h1>About <jsp:getProperty property="nombre" name="ciudad"/></h1>
						<div class="table-responsive">
							<table class="table table-bordered">
								<thead>
									<tr>
										<td><strong>Information</strong></td>
										<td><strong>Link</strong></td>
									</tr>
								</thead>
								<tbody>
									<tr class="info">
										<td>Information about the city</td>
										<td><a href="https://es.wikipedia.org/wiki/<jsp:getProperty property="nombre" name="ciudad"/>" target="_blank">URL</a></td>
									</tr>
									<tr class="success">
										<td>News related with the city</td>
										<td><a href="http://edition.cnn.com/search/?text=<jsp:getProperty property="nombre" name="ciudad"/>" target="_blank">URL</a></td>
									</tr>
									<tr class="warning">
										<td>General search</td>
										<td><a href="https://www.google.es/#q=<jsp:getProperty property="nombre" name="ciudad"/>" target="_blank">URL</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<h1>About the current weather situation</h1>
						<ul class="list-group">
							<li class="list-group-item list-group-item-success">
								<h4 class="list-group-item-heading">Weather</h4>
								<ul>
									<%
									CiudadModel cm = (CiudadModel) session.getAttribute("ciudad");
									String [] descTiempo = cm.getTiempo().split("#");
									int i = 0;
									while (i < descTiempo.length) {
										%><li><strong><%= descTiempo[i] %></strong>: <%= descTiempo[i+1] %>.</li><%
										i += 2;
									}
									%>		
								</ul>
							</li>
							<li class="list-group-item list-group-item-info">
								<h4 class="list-group-item-heading">Current temperature</h4>
								<p class="list-group-item-text"><jsp:getProperty property="temperatura" name="ciudad"/> Cº</p>
							</li>
							<li class="list-group-item list-group-item-info">
								<h4 class="list-group-item-heading">Maximum temperature</h4>
								<p class="list-group-item-text"><jsp:getProperty property="temperaturaMaxima" name="ciudad"/> Cº</p>
							</li>
							<li class="list-group-item list-group-item-info">
								<h4 class="list-group-item-heading">Minimum temperature</h4>
								<p class="list-group-item-text"><jsp:getProperty property="temperaturaMinima" name="ciudad"/> Cº</p>
							</li>
							<li class="list-group-item list-group-item-danger">
								<h4 class="list-group-item-heading">Wind speed</h4>
								<p class="list-group-item-text"><jsp:getProperty property="velocidadViento" name="ciudad"/> Km/h</p>
							</li>
							<li class="list-group-item list-group-item-warning">
								<h4 class="list-group-item-heading">Humidity</h4>
								<p class="list-group-item-text"><jsp:getProperty property="humedad" name="ciudad"/> %</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<h1>Favorites</h1>
						<%
						if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContraseña().isEmpty()) {
							
							if (cm.isFavoritos()) {
								%>
								<h3>Click the following button to remove the city '<jsp:getProperty property="nombre" name="ciudad"/>' from your favorite cities list.</h3>
								<form action="quitarfavoritos" method="post">
									<input type="hidden" name="nombreCiudad" value="<jsp:getProperty property="nombre" name="ciudad"/>">
									<input type="hidden" name="emailUsuario" value="<jsp:getProperty property="email" name="usuario"/>">
									<button type="submit" class="btn btn-default">Remove city</button>
								</form>
								<%
							} else {
								%>
								<h3>Click the following button to add the city '<jsp:getProperty property="nombre" name="ciudad"/>' to your favorite cities list.</h3>
								<form action="añadirfavoritos" method="post">
									<input type="hidden" name="email" value="<jsp:getProperty property="email" name="usuario"/>">
									<input type="hidden" name="nombre" value="<jsp:getProperty property="nombre" name="ciudad"/>">
									<input type="hidden" name="tiempo" value="<jsp:getProperty property="tiempo" name="ciudad"/>">
									<input type="hidden" name="temperatura" value="<jsp:getProperty property="temperatura" name="ciudad"/>">
									<input type="hidden" name="temperaturaMaxima" value="<jsp:getProperty property="temperaturaMaxima" name="ciudad"/>">
									<input type="hidden" name="temperaturaMinima" value="<jsp:getProperty property="temperaturaMinima" name="ciudad"/>">
									<input type="hidden" name="velocidadViento" value="<jsp:getProperty property="velocidadViento" name="ciudad"/>">
									<input type="hidden" name="humedad" value="<jsp:getProperty property="humedad" name="ciudad"/>">
									<button type="submit" class="btn btn-default">Add city</button>
								</form>							
								<%
							}	
						} else {
							%>
							<h3>Sign In to see this section.</h3>
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
						<li><p class="navbar-text">Created by Endika Salgueiro Barquin</p></li>
					</ul>
				</div>
			</nav>
		</div>
	</body>
</html>