package Models;

public class CiudadModel {
	
	private String nombre;
	private String tiempo;
	private double temperatura;
	private double temperaturaMaxima;
	private double temperaturaMinima;
	private double velocidadViento;
	private double humedad;
	
	private boolean favoritos;
	
	public CiudadModel() {

	}

	public CiudadModel(String nombre, String tiempo, double temperatura, double temperaturaMaxima,
			double temperaturaMinima, double velocidadViento, double humedad, boolean favoritos) {
		super();
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.temperatura = temperatura;
		this.temperaturaMaxima = temperaturaMaxima;
		this.temperaturaMinima = temperaturaMinima;
		this.velocidadViento = velocidadViento;
		this.humedad = humedad;
		this.favoritos = favoritos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public double getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(double temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public double getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(double temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public double getVelocidadViento() {
		return velocidadViento;
	}

	public void setVelocidadViento(double velocidadViento) {
		this.velocidadViento = velocidadViento;
	}

	public double getHumedad() {
		return humedad;
	}

	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}

	public boolean isFavoritos() {
		return favoritos;
	}

	public void setFavoritos(boolean favoritos) {
		this.favoritos = favoritos;
	}
}
