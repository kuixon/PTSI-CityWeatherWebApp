<%@page import="Models.UsuarioModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
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
					
					if ($("#contraseñaBoton").val() == 'Mostrar contraseña') {
						$("#contraseñaBoton").prop('value', 'Ocultar contraseña');
					} else {
						$("#contraseñaBoton").prop('value', 'Mostrar contraseña');
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
						<div class="alert alert-success" role="alert">
							<%
							UsuarioModel um = (UsuarioModel) request.getAttribute("usuario");
							%>
							<h1>¡Enhorabuena <%= um.getNombreUsuario() %>!</h1>
							<h3>Te has registrado correctamente con los siguientes datos:</h3>
							<ul class="list-group">
								<li class="list-group-item list-group-item-warning"><strong>Nombre de usuario</strong>: <%= um.getNombreUsuario() %></li>
								<li class="list-group-item list-group-item-info"><strong>Email</strong>: <%= um.getEmail() %></li>
								<li class="list-group-item list-group-item-danger"><strong>Contraseña</strong>: <input type="button" value="Mostrar contraseña" class="btn btn-default" id="contraseñaBoton" /> </li>
								<li class="list-group-item" id="contraseñaTexto" style="display: none;">La contraseña elegida en el registro ha sido: <strong><%= um.getContraseña() %></strong></li>
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
						<li><p class="navbar-text">Creado por Endika Salgueiro Barqu&iacute;n</p></li>
					</ul>
				</div>
			</nav>
		</div>
	</body>
</html>