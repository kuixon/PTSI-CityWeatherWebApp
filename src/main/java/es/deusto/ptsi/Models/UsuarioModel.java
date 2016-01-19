package es.deusto.ptsi.Models;

public class UsuarioModel implements java.io.Serializable {

	private String nombreUsuario;
	private String email;
	private String password;
	
	public UsuarioModel() {
		
	}
	
	public UsuarioModel(String nombreUsuario, String email, String password) {
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
