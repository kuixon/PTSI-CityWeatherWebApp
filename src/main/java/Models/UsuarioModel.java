package Models;

public class UsuarioModel {

	private String nombreUsuario;
	private String email;
	private String contrase�a;
	
	public UsuarioModel() {
		
	}
	
	public UsuarioModel(String nombreUsuario, String email, String contrase�a) {
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contrase�a = contrase�a;
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
	
	public String getContrase�a() {
		return contrase�a;
	}
	
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
}
