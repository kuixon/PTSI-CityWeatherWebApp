package RESTFul;

import javax.ws.rs.core.MediaType;

import Utilities.JSONException;
import Utilities.JSONObject;
import Utilities.JSONReader;

import java.io.IOException;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/api")
public class RESTFulAPI {
	
	@Path("/currentWeatherXML/{city}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public CityWeather obtenerTiempoActualXML(@PathParam("city") String city) throws JSONException, IOException {
		CityWeather cw = new CityWeather();
		
		String ciudadQuery = city.replaceAll("\\s","");
		JSONObject json = JSONReader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + ciudadQuery + "&units=metric&APPID=c62dd1a67db43c63b19e933e51028163");
		
		for (int i = 0; i < json.getJSONArray("weather").length(); i++) {
			String tiempo = ((JSONObject) json.getJSONArray("weather").get(i)).getString("main")
					+ ": " + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description");
			cw.getWeather().add(tiempo);
		}
		
		cw.setName(city);
		cw.setTemperature(json.getJSONObject("main").getDouble("temp"));
		cw.setMaximumTemperature(json.getJSONObject("main").getDouble("temp_max"));
		cw.setMinimumTemperature(json.getJSONObject("main").getDouble("temp_min"));
		cw.setWindSpeed((json.getJSONObject("wind").getDouble("speed") * 3600) / 1000);
		cw.setHumidity(json.getJSONObject("main").getDouble("humidity"));
		
		return cw;
	}
	
	@Path("/currentWeatherJSON/{city}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String obtenerTiempoActualJSON(@PathParam("city") String city) throws JSONException, IOException {
		
		String ciudadQuery = city.replaceAll("\\s","");
		JSONObject json = JSONReader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + ciudadQuery + "&units=metric&APPID=c62dd1a67db43c63b19e933e51028163");
		
		JSONObject jsonResult = new JSONObject();
		
		Vector<String> vs = new Vector<String>();
		for (int i = 0; i < json.getJSONArray("weather").length(); i++) {
			String tiempo = ((JSONObject) json.getJSONArray("weather").get(i)).getString("main")
					+ ": " + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description");
			vs.add(tiempo);
		}
		
		jsonResult.put("name", city);
		jsonResult.put("weather", vs);
		jsonResult.put("temperature", json.getJSONObject("main").getDouble("temp"));
		jsonResult.put("maximum temperature", json.getJSONObject("main").getDouble("temp_max"));
		jsonResult.put("minimum temperature", json.getJSONObject("main").getDouble("temp_min"));
		jsonResult.put("wind speed", (json.getJSONObject("wind").getDouble("speed") * 3600) / 1000);
		jsonResult.put("humidity", json.getJSONObject("main").getDouble("humidity"));
		
		return jsonResult.toString();
	}
	
	@Path("/currentWeatherHTML/{city}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String obtenerTiempoActualHTML(@PathParam("city") String city) throws JSONException, IOException {
		
		String ciudadQuery = city.replaceAll("\\s","");
		JSONObject json = JSONReader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + ciudadQuery + "&units=metric&APPID=c62dd1a67db43c63b19e933e51028163");
		
		Vector<String> vs = new Vector<String>();
		for (int i = 0; i < json.getJSONArray("weather").length(); i++) {
			String tiempo = ((JSONObject) json.getJSONArray("weather").get(i)).getString("main")
					+ ": " + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description");
			vs.add(tiempo);
		}
		
		String weather = "<h2>Weather:</h2><ul>";
		for (String s : vs) {
			weather += "<li><p>" + s + "</p></li>";
		}
		weather += "</ul>";
		
		String temperature = Double.toString(json.getJSONObject("main").getDouble("temp"));
		String maximumTemperatre = Double.toString(json.getJSONObject("main").getDouble("temp_max"));
		String minumumTemperature = Double.toString(json.getJSONObject("main").getDouble("temp_min"));
		String windSpeed = Double.toString((json.getJSONObject("wind").getDouble("speed") * 3600) / 1000);
		String humidity = Double.toString(json.getJSONObject("main").getDouble("humidity"));
		
		String response = "<h1>Current Weather Situation in " + city + "</h1>"
							+ weather
							+ "<h2>Temperature:</h2><p>" + temperature + " C&#176;</p>"
							+ "<h2>Maximum temperature:</h2><p>" + maximumTemperatre + " C&#176;</p>"
							+ "<h2>Minimum temperature:</h2><p>" + minumumTemperature + " C&#176;</p>"
							+ "<h2>Wind speed:</h2><p>" + windSpeed + " Km/h</p>"
							+ "<h2>Humidity:</h2><p>" + humidity + " %</p>";
		
		return response;
	}
}
