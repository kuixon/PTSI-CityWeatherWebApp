package RESTFul;

import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CityWeather {
	
	private String name;
	private Vector<String> weather;
	private double temperature;
	private double maximumTemperature;
	private double minimumTemperature;
	private double windSpeed;
	private double humidity;
	
	public CityWeather() {
		name = "";
		weather = new Vector<String>();
		temperature = 0;
		maximumTemperature = 0;
		minimumTemperature = 0;
		windSpeed = 0;
		humidity = 0;
	}

	public CityWeather(String name, Vector<String> weather, double temperature, double maximumTemperature,
			double minimumTemperature, double windSpeed, double humidity) {
		super();
		this.name = name;
		this.weather = weather;
		this.temperature = temperature;
		this.maximumTemperature = maximumTemperature;
		this.minimumTemperature = minimumTemperature;
		this.windSpeed = windSpeed;
		this.humidity = humidity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<String> getWeather() {
		return weather;
	}

	public void setWeather(Vector<String> weather) {
		this.weather = weather;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getMaximumTemperature() {
		return maximumTemperature;
	}

	public void setMaximumTemperature(double maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}

	public double getMinimumTemperature() {
		return minimumTemperature;
	}

	public void setMinimumTemperature(double minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
}
