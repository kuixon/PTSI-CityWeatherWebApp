<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.UsuarioModel"%>
<jsp:useBean id="usuario" class="Models.UsuarioModel" scope="session"></jsp:useBean>
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
							<li class="active"><a href="/CityWeatherWebApp/index">Home<span class="sr-only">(current)</span></a></li>
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
							<li class="active"><a href="/CityWeatherWebApp/index">Home<span class="sr-only">(current)</span></a></li>
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
				<div class="col-xs-12">
					<div class="well_transparente">
						<div class="panel panel-primary">
							<div class="panel-heading">Searcher</div>
							<div class="panel-body">
								<h2 align="center">Search the city which you wish to obtain its current weather situation.</h2>
								<%
								if (!um.getNombreUsuario().isEmpty() && !um.getEmail().isEmpty() && !um.getContraseña().isEmpty()) {
									%>
									<h3 align="center">Also, you will be able to add cities to your favorite cities list when you have signed in.</h3>
									<%
								} else {
									%>	
									<h3 align="justify">Also, you will be able to add cities to your favorite cities list when you have signed in. If you do not have an account yet, <a href="/CityWeatherWebApp/registro">Sign Up!</a></h3>
									<%
								}
								%>
								<form action="buscar" method="post">
									<div class="input-group">
										<input type="text" name="nombreCiudad" class="form-control" placeholder="Put the name of the city you wish, for example: London">
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
									    	<button type="submit" class="btn btn-default">Search</button>
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
						<li><p class="navbar-text">Created by Endika Salgueiro Barquin</p></li>
					</ul>
				</div>
			</nav>
		</div>
	</body>
</html>