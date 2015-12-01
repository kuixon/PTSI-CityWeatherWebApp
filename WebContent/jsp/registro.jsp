<%@page import="Models.UsuarioModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="usuario" class="Models.UsuarioModel" scope="request"></jsp:useBean>
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
						<li><a href="#">Ciudades favoritas</a></li>
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
						<div class="panel panel-default">
							<div class="panel-body" align="left">
								<h2>Formulario de registro</h2>
								<form action="registro" method="post">
									<div class="form-group">
								    	<label for="nombreUsuario">Nombre de usuario</label>
								    	<input type="text" name="nombreUsuario" class="form-control" id="nombreUsuario" placeholder="Nombre de usuario" value="<jsp:getProperty property="nombreUsuario" name="usuario"/>" required>
								  	</div>
									<div class="form-group">
								    	<label for="email">Email</label>
								    	<input type="email" name="email" class="form-control" id="email" placeholder="Email" value="<jsp:getProperty property="email" name="usuario"/>" required>
								  	</div>
									<div class="form-group">
								    	<label for="contrase�a">Contrase�a</label>
								    	<input type="password" name="contrase�a" class="form-control" id="contrase�a" placeholder="Contrase�a" value="<jsp:getProperty property="contrase�a" name="usuario"/>" required>
								  	</div>
									<button type="submit" class="btn btn-default">Registrarse</button>
								</form>
							</div>
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