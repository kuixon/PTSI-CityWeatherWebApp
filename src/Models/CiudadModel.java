package Models;

public class CiudadModel {
	
	private String nombreCiudad;
	private String situacionMetActual;
	private boolean favoritos;
	
	public CiudadModel() {
		
	}

	public CiudadModel(String nombre, String situacionMetActual, boolean favoritos) {
		super();
		this.nombreCiudad = nombre;
		this.situacionMetActual = situacionMetActual;
		this.favoritos = favoritos;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombre) {
		this.nombreCiudad = nombre;
	}

	public String getSituacionMetActual() {
		return situacionMetActual;
	}

	public void setSituacionMetActual(String situacionMetActual) {
		this.situacionMetActual = situacionMetActual;
	}

	public boolean isFavoritos() {
		return favoritos;
	}

	public void setFavoritos(boolean favoritos) {
		this.favoritos = favoritos;
	}
	
}
