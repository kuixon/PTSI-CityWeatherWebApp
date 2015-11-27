package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.UsuarioModel;

public class DatabaseManager {
	
	private static DatabaseManager instance = null;
	private static String STRING_CON = "jdbc:mysql://localhost:3306/cityweatherwebappdb";
	private static String USUARIO = "root";
	private static String CONTRASE�A = "";
	private Connection con = null;
	
	private DatabaseManager() {
		
	}
	
	public static DatabaseManager getInstance() {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	public boolean establecerConexion() throws ClassNotFoundException {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(STRING_CON, USUARIO, CONTRASE�A);
			System.out.println("Se establece la conexi�n con la base de datos");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR al establecer la conexi�n con la base de datos");
			return false;
		}
	}
	
	public void cerrarConexion() {
		try {
			con.close();
			System.out.println("Se cierra la conexion con la base de datos.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR al cerrar la conexi�n con la base de datos");
		}
	}
	
	public void insertarUsuario(UsuarioModel um) throws SQLException {
		try {
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO usuarios(nombreUsuario,email,contrasena) "
						+ "VALUES('" + um.getNombreUsuario() + "','" + um.getEmail() + "',"
								+ "'" + um.getContrase�a() + "')");
				System.out.println("Usuario insertado correctamente en la base de datos.");
				cerrarConexion();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR al insertar un usuario en la base de datos.");
		}
	}
	
	public boolean existeUsuarioConEmail(String email) throws SQLException {
		try {
			if(establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE email = '" + email + "'");
				if (rs.next()) {
					cerrarConexion();
					return true;
				} else {
					cerrarConexion();
					return false;
				}
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error a la hora de establecer la conexi�n.");
			return false;
		}
	}
}