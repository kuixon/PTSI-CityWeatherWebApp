package es.deusto.ptsi.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.appengine.api.utils.SystemProperty;
import java.util.logging.Logger;

import es.deusto.ptsi.Models.CiudadModel;
import es.deusto.ptsi.Models.UsuarioModel;

public class DatabaseManager {
	
	private static final Logger log = Logger.getLogger(DatabaseManager.class.getName());
	private static DatabaseManager instance = null;
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
			String url = null;
			if (SystemProperty.environment.value() ==
			    SystemProperty.Environment.Value.Production) {
				// Connecting from App Engine.
				// Load the class that provides the "jdbc:google:mysql://"
				// prefix.
				Class.forName("com.mysql.jdbc.GoogleDriver");
				url = "jdbc:google:mysql://cityweatherwebapp:bd-cityweatherwebapp/cityweatherwebapp-db?user=root&password=1234";
			} else {
				// Connecting from an external network.
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://173.194.107.200:3306/cityweatherwebapp-db?user=endika&password=78952922v";
			}

			con = DriverManager.getConnection(url);
			log.info("Se establece la conexión con la base de datos");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR establecerConexion: " + e.getMessage());
			return false;
		}
	}
	
	public void insertarUsuario(UsuarioModel um) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO usuarios(nombreUsuario,email,contrasena) "
					+ "VALUES('" + um.getNombreUsuario() + "','" + um.getEmail() + "',"
							+ "'" + um.getPassword() + "')");
			log.info("Usuario insertado correctamente en la base de datos.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR insertarUsuario: " + e.getMessage());
		}
	}
	
	public void insertarCiudad(CiudadModel cm) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO ciudades(nombreCiudad,tiempo,temperatura,"
					+ "temperaturaMaxima,temperaturaMinima,velocidadViento,humedad) "
					+ "VALUES('" + cm.getNombre() + "','" + cm.getTiempo() + "',"
					+ Double.toString(cm.getTemperatura()) + "," + Double.toString(cm.getTemperaturaMaxima()) +
					"," + Double.toString(cm.getTemperaturaMinima()) + "," + Double.toString(cm.getVelocidadViento())
					+ "," + Double.toString(cm.getHumedad()) + ")");
			log.info("Ciudad insertada correctamente en la base de datos.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR insertarCiudad: " + e.getMessage());
		}
	}
	
	public void insertarRelacion(int idusuario, int idciudad) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO usuariosciudades(idUsuarios,idCiudades) "
					+ "VALUES(" + Integer.toString(idusuario) + "," + Integer.toString(idciudad) + ")");
			log.info("Relación insertada correctamente en la base de datos.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR insertarRelacion: " + e.getMessage());
		}
	}
	
	public int obtenerIdUsuarioPorEmail(String email) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT idUsuarios FROM usuarios WHERE email = '" + email + "'");
			if (rs.next()) {
				int id = rs.getInt("idUsuarios");
				log.info("Id del usuario: " + id); 
				return id;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerIdUsuarioPorEmail: " + e.getMessage());
			return 0;
		}
	}
	
	public int obtenerIdCiudadPorNombre(String nombre) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT idCiudades FROM ciudades WHERE nombreCiudad = '" + nombre + "'");
			if (rs.next()) {
				int id = rs.getInt("idCiudades");
				log.info("Id de la ciudad: " + id);
				return id;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerIdCiudadPorNombre: " + e.getMessage());
			return 0;
		}
	}
	
	public UsuarioModel obtenerUsuario(String email) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE email = '" + email + "'");
			if (rs.next()) {
				UsuarioModel um = new UsuarioModel(rs.getString("nombreUsuario"), rs.getString("email"), rs.getString("contrasena"));
				log.info("Usuario: " + um.getNombreUsuario());
				return um;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerUsuario: " + e.getMessage());
			return null;
		}
	}
	
	public CiudadModel obtenerCiudad(String nombreCiudad) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ciudades WHERE nombreCiudad = '" + nombreCiudad + "'");
			if (rs.next()) {
				CiudadModel cm = new CiudadModel(rs.getString("nombreCiudad"), rs.getString("tiempo"), rs.getDouble("temperatura"), 
						rs.getDouble("temperaturaMaxima"), rs.getDouble("temperaturaMinima"), rs.getDouble("velocidadViento"), 
						rs.getDouble("humedad"), false);
				log.info("Ciudad: " + cm.getNombre());
				return cm;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerCiudad: " + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<CiudadModel> obtenerCiudadesUsuario(int idusuario) {
		ArrayList<CiudadModel> alcm = new ArrayList<CiudadModel>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ciudades c INNER JOIN usuariosciudades uc ON"
					+ " c.idCiudades = uc.idCiudades WHERE uc.idUsuarios = " + Integer.toString(idusuario));
			while (rs.next()) {
				CiudadModel cm = new CiudadModel(rs.getString("nombreCiudad"), rs.getString("tiempo"), rs.getDouble("temperatura"), 
						rs.getDouble("temperaturaMaxima"), rs.getDouble("temperaturaMinima"), rs.getDouble("velocidadViento"), 
						rs.getDouble("humedad"), false);
				log.info("Ciudad " + cm.getNombre() + " añadida al arrayList.");
				alcm.add(cm);
			}
			return alcm;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR obtenerCiudadesUsuario: " + e.getMessage());
			return null;
		}
	}
	
	public void actualizarCiudad(CiudadModel cm) {
		try {
			int idciudad = obtenerIdCiudadPorNombre(cm.getNombre());
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE ciudades SET nombreCiudad = '" + cm.getNombre() + "',"
					+ " tiempo = '" + cm.getTiempo() + "', temperatura = " + Double.toString(cm.getTemperatura()) + ","
					+ " temperaturaMaxima = " + Double.toString(cm.getTemperaturaMaxima()) + ", temperaturaMinima = "
					+ Double.toString(cm.getTemperaturaMinima()) + ", velocidadViento = " + Double.toString(cm.getVelocidadViento()) + ", "
					+ "humedad = " + Double.toString(cm.getHumedad()) + " WHERE idCiudades = " + Integer.toString(idciudad));
			log.info("Ciudad " + cm.getNombre() + " actualizada correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR actualizarCiudad: " + e.getMessage());
		}
	}
	
	public void eliminarRelacion(int idusuario, int idciudad) {
		try {
			Statement stmt = con.createStatement();
			int filasAfectadas = stmt.executeUpdate("DELETE FROM usuariosciudades WHERE idUsuarios = " + Integer.toString(idusuario) + 
					" AND idCiudades = " + Integer.toString(idciudad));
			log.info("La relación fue borrada correctamente. En total " + Integer.toString(filasAfectadas) + 
					" filas han sido eliminadas.");
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR eliminarRelacion: " + e.getMessage());
		}
	}
	
	public boolean existeRelacion(int idusuario, int idciudad) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuariosciudades WHERE idUsuarios = " + Integer.toString(idusuario) +
				" AND idCiudades = " + Integer.toString(idciudad));
			if (rs.next()) {
				log.info("Si existe relacion.");
				return true;
			} else {
				log.info("No existe relacion.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR existeRelacion: " + e.getMessage());
			return false;
		}
	}
	
	public boolean loginUsuario(String email, String password) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT contrasena FROM usuarios WHERE email = '" + email + "'");
			if (rs.next()) {
				if (rs.getString(1).equals(password)) {
					log.info("Login correcto.");
					return true;
				} else {
					log.info("Login incorrecto.");
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.warning("ERROR loginUsuario: " + e.getMessage());
			return false;
		}
	}
}