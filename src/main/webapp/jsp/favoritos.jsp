<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="es.deusto.ptsi.Models.UsuarioModel"%>
<%@page import="es.deusto.ptsi.Models.CiudadModel"%>
<jsp:useBean id="usuario" class="es.deusto.ptsi.Models.UsuarioModel" scope="session"></jsp:useBean>
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
						<li><a class="navbar-brand" href="/index">City Weather Web App</a></li>
						<li><a href="/index">Home<span class="sr-only">(current)</span></a></li>
						<li><a href="/swagger/index.html" target="_blank">RESTful API</a></li>
						<li class="active"><a href="/favoritos?email=<jsp:getProperty property="email" name="usuario"/>">My Favorite Cities</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a><jsp:getProperty property="nombreUsuario" name="usuario"/></a></li>
						<li><a href="/entrar?action=logout">Logout</a></li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="contenedor_contenido">
			<div class="row">
				<div class="well_transparente">
					<div class="col-xs-12">
						<h1>Those are your favorite cities <jsp:getProperty property="nombreUsuario" name="usuario"/></h1>
						<div class="pull-right">
							<form action="favoritos" method="post">
								<% 
								ArrayList<CiudadModel> alcm = (ArrayList<CiudadModel>) session.getAttribute("ciudades");
								if (alcm.size() > 0) {
									int cont = 1;
									String nomCiudad = "";
									for (CiudadModel cm : alcm) {
										nomCiudad = "ciudad" + cont;
										%>
										<input type="hidden" name="<%= nomCiudad %>" value="<%= cm.getNombre() %>">
										<%
										cont++;
									}
								}
								%>
								<input type="hidden" name="emailUsuario" value="<jsp:getProperty property="email" name="usuario"/>">
								<button type="submit" class="boton_reload"></button>
							</form>
						</div>
						<div class="pull-right">
							<h3>Refresh data: &nbsp;</h3>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
										<tr>
											<td align="center"><strong>City</strong></td>
											<td align="center"><strong>Weather</strong></td>
											<td align="center"><strong>Current temperature</strong></td>
											<td align="center"><strong>Maximum temperature</strong></td>
											<td align="center"><strong>Minimum temperature</strong></td>
											<td align="center"><strong>Wind speed</strong></td>
											<td align="center"><strong>Humidity</strong></td>
											<td align="center"><strong>Remove from favorites</strong></td>
										</tr>
									</thead>
									<tbody>
										<%
										if (alcm.size() > 0) {
											for (CiudadModel cm : alcm) {
												%>
												<tr>
													<td align="center"><%= cm.getNombre() %></td>
													<td align="center">
														<ul>
															<%
															String [] descTiempo = cm.getTiempo().split("#");
															int i = 0;
															while (i < descTiempo.length) {
																%><li><strong><%= descTiempo[i] %></strong>: <%= descTiempo[i+1] %>.</li><%
																i += 2;
															}
															%>
														</ul>
													</td>
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
						<li><p class="navbar-text">Created by Endika Salgueiro Barquin</p></li>
					</ul>
				</div>
			</nav>
		</div>
	</body>
</html>