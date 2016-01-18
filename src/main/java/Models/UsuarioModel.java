package Models;

public class UsuarioModel {

	private String nombreUsuario;
	private String email;
	private String contraseña;
	
	public UsuarioModel() {
		
	}
	
	public UsuarioModel(String nombreUsuario, String email, String contraseña) {
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contraseña = contraseña;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
}
