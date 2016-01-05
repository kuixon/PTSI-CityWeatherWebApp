<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
					<ul class="nav navbar-nav navbar-left">
						<li><a class="navbar-brand" href="/CityWeatherWebApp/index">City Weather Web App</a></li>
						<li><a href="/CityWeatherWebApp/index">Home<span class="sr-only">(current)</span></a></li>
						<li><a href="/CityWeatherWebApp/favoritos?email=<jsp:getProperty property="email" name="usuario"/>">My Favorite Cities</a></li>
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
						<div class="alert alert-success" role="alert">
							<h1>Congratulations <jsp:getProperty property="nombreUsuario" name="usuario"/>!</h1>
							<h3>You have successfully added the city '<jsp:getProperty property="nombre" name="ciudad"/>' to your favorite cities list with the following information:</h3>
							<ul class="list-group">
								<li class="list-group-item list-group-item-info"><strong>Current temperature</strong>: <jsp:getProperty property="temperatura" name="ciudad"/> Cº</li>
								<li class="list-group-item list-group-item-info"><strong>Maximum temperature</strong>: <jsp:getProperty property="temperaturaMaxima" name="ciudad"/> Cº</li>
								<li class="list-group-item list-group-item-info"><strong>Minimum temperature</strong>: <jsp:getProperty property="temperaturaMinima" name="ciudad"/> Cº</li>
								<li class="list-group-item list-group-item-danger"><strong>Wind speed</strong>: <jsp:getProperty property="velocidadViento" name="ciudad"/> Km/h</li>
								<li class="list-group-item list-group-item-warning"><strong>Humidity</strong>: <jsp:getProperty property="humedad" name="ciudad"/> %</li>
							</ul>
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