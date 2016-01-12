<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#contraseñaBoton").click(function() {
					$("#contraseñaTexto").slideToggle("slow",function () {
				    });
					
					if ($("#contraseñaBoton").val() == 'Show password') {
						$("#contraseñaBoton").prop('value', 'Hide password');
					} else {
						$("#contraseñaBoton").prop('value', 'Show password');
					}
			    });
			});
		</script>
	</head>
	<body>
		<div>
			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container-fluid">
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
				</div>
			</nav>
		</div>
		<div class="contenedor_contenido">
			<div class="row">
				<div class="col-xs-4"></div>
				<div class="col-xs-4">
					<div class="well_transparente">
						<div class="alert alert-success" role="alert">
							<h1>Congratulations <jsp:getProperty property="nombreUsuario" name="usuario"/>!</h1>
							<h3>You have successfully signed up with the following information:</h3>
							<ul class="list-group">
								<li class="list-group-item list-group-item-warning"><strong>Username</strong>: <jsp:getProperty property="nombreUsuario" name="usuario"/></li>
								<li class="list-group-item list-group-item-info"><strong>Email</strong>: <jsp:getProperty property="email" name="usuario"/></li>
								<li class="list-group-item list-group-item-danger"><strong>Password</strong>: <input type="button" value="Show password" class="btn btn-default" id="contraseñaBoton" /> </li>
								<li class="list-group-item" id="contraseñaTexto" style="display: none;">The password chosen in the Sign Up form has been: <strong><jsp:getProperty property="contraseña" name="usuario"/></strong></li>
							</ul>
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
						<li><p class="navbar-text">Created by Endika Salgueiro Barquin</p></li>
					</ul>
				</div>
			</nav>
		</div>
	</body>
</html>