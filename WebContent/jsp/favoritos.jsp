<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Models.UsuarioModel"%>
<%@page import="Models.CiudadModel"%>
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
					<ul class="nav navbar-nav navbar-left">
						<li><a class="navbar-brand" href="/CityWeatherWebApp/index">City Weather Web App</a></li>
						<li><a href="/CityWeatherWebApp/index">Inicio<span class="sr-only">(current)</span></a></li>
						<li class="active"><a href="/CityWeatherWebApp/favoritos?email=<jsp:getProperty property="email" name="usuario"/>">Ciudades favoritas</a></li>
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
				<div class="well_transparente">
					<div class="col-xs-12">
						<h1>Estas son tus ciudades favoritas <jsp:getProperty property="nombreUsuario" name="usuario"/></h1>
						<div class="pull-right">
							<button class="boton_reload"></button>
						</div>
						<div class="pull-right">
							<h3>Actualizar los datos: &nbsp;</h3>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<td align="center"><strong>Ciudad</strong></td>
											<td align="center"><strong>Tiempo</strong></td>
											<td align="center"><strong>Temperatura actual</strong></td>
											<td align="center"><strong>Temperatura m&aacute;xima</strong></td>
											<td align="center"><strong>Temperatura m&iacute;nima</strong></td>
											<td align="center"><strong>Velocidad del viento</strong></td>
											<td align="center"><strong>Humedad</strong></td>
											<td align="center"><strong>Quitar de favoritos</strong></td>
										</tr>
									</thead>
									<tbody>
										<%
										ArrayList<CiudadModel> alcm = (ArrayList<CiudadModel>) session.getAttribute("ciudades");
										if (alcm.size() > 0) {
											for (CiudadModel cm : alcm) {
												%>
												<tr>
													<td align="center"><%= cm.getNombre() %></td>
													<td align="center"><%= cm.getTiempo() %></td>
													<td align="center"><%= Double.toString(cm.getTemperatura()) %> Cº</td>
													<td align="center"><%= Double.toString(cm.getTemperaturaMaxima()) %> Cº</td>
													<td align="center"><%= Double.toString(cm.getTemperaturaMinima()) %> Cº</td>
													<td align="center"><%= Double.toString(cm.getVelocidadViento()) %> Km/h</td>
													<td align="center"><%= Double.toString(cm.getHumedad()) %> %</td>
													<td align="center">
														<form action="quitarfavoritos" method="post">
															<input type="hidden" name="nombreCiudad" value="<%= cm.getNombre() %>">
															<input type="hidden" name="emailUsuario" value="<jsp:getProperty property="email" name="usuario"/>">
															<button class="boton_quitar" type="submit"></button>
														</form>
													</td>
												</tr>
												<%
											}
										}
										%>
									</tbody>
								</table>
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