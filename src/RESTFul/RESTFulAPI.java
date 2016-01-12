package RESTFul;

import javax.ws.rs.core.MediaType;

import Utilities.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/api")
public class RESTFulAPI {
	
	@Path("/currentWeatherXML/{city}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public CityWeather obtenerTiempoActualXML(@PathParam("city") String city) {
		CityWeather cw = new CityWeather();
		
		//Inicializamos la ciudad
		cw.setName(city);
		cw.setWeather("Sunny");
		cw.setTemperature(20.2);
		cw.setMaximumTemperature(24.5);
		cw.setMinimumTemperature(15.3);
		cw.setWindSpeed(30.1);
		cw.setHumidity(55);
		
		return cw;
	}
	
	@Path("/currentWeatherJSON/{city}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String obtenerTiempoActualJSON(@PathParam("city") String city) {
		JSONObject json = new JSONObject();
		
		//Inicializamos el Objeto json
		json.put("name", city);
		json.put("weather", "Sunny");
		json.put("temperature", 20.2);
		json.put("maximum temperature", 24.5);
		json.put("minimum temperature", 15.3);
		json.put("wind speed", 30.1);
		json.put("humidity", 55);
		
		return json.toString();
	}
	
	@Path("/currentWeatherHTML/{city}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String obtenerTiempoActualHTML(@PathParam("city") String city) {
		
		//Inicializamos los valores del html
		String weather = "Sunny";
		String temperature = "20.2";
		String maximumTemperatre = "24.5";
		String minumumTemperature = "15.3";
		String windSpeed = "30.1";
		String humidity = "55";
		
		String response = "<h1 align='center'>Current Weather Situation in " + city + "</h1>"
							+ "<h2 align='center'>Weather:</h2><p align='center'>" + weather + "</p>"
							+ "<h2 align='center'>Temperature:</h2><p align='center'>" + temperature + " C&#176;</p>"
							+ "<h2 align='center'>Maximum temperature:</h2><p align='center'>" + maximumTemperatre + " C&#176;</p>"
							+ "<h2 align='center'>Minimum temperature:</h2><p align='center'>" + minumumTemperature + " C&#176;</p>"
							+ "<h2 align='center'>Wind speed:</h2><p align='center'>" + windSpeed + " Km/h</p>"
							+ "<h2 align='center'>Humidity:</h2><p align='center'>" + humidity + " %</p>";
		
		return response;
	}
}
