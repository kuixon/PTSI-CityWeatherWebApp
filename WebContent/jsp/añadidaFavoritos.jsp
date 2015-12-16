<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
					<ul class="nav navbar-nav navbar-left">
						<li><a class="navbar-brand" href="/CityWeatherWebApp/index">City Weather Web App</a></li>
						<li><a href="/CityWeatherWebApp/index">Inicio<span class="sr-only">(current)</span></a></li>
						<li><a href="/CityWeatherWebApp/favoritos?email=<jsp:getProperty property="email" name="usuario"/>">Ciudades favoritas</a></li>
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
							<h1>¡Enhorabuena <jsp:getProperty property="nombreUsuario" name="usuario"/>!</h1>
							<h3>Has añadido correctamente la ciudad '<jsp:getProperty property="nombre" name="ciudad"/>' a tu lista de favoritos con los siguientes datos:</h3>
							<ul class="list-group">
								<li class="list-group-item list-group-item-info"><strong>Temperatura</strong>: <jsp:getProperty property="temperatura" name="ciudad"/> Cº</li>
								<li class="list-group-item list-group-item-info"><strong>Temperatura m&aacute;xima</strong>: <jsp:getProperty property="temperaturaMaxima" name="ciudad"/> Cº</li>
								<li class="list-group-item list-group-item-info"><strong>Temperatura m&iacute;nima</strong>: <jsp:getProperty property="temperaturaMinima" name="ciudad"/> Cº</li>
								<li class="list-group-item list-group-item-danger"><strong>Velocidad del viento</strong>: <jsp:getProperty property="velocidadViento" name="ciudad"/> Km/h</li>
								<li class="list-group-item list-group-item-warning"><strong>Humedad</strong>: <jsp:getProperty property="humedad" name="ciudad"/> %</li>
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
						<li><p class="navbar-text">Creado por Endika Salgueiro Barqu&iacute;n</p></li>
					</ul>
				</div>
			</nav>
		</div>
	</body>
</html>