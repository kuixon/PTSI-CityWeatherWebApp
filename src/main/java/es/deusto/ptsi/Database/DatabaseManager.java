package es.deusto.ptsi.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.deusto.ptsi.Models.CiudadModel;
import es.deusto.ptsi.Models.UsuarioModel;

public class DatabaseManager {
	
	private static DatabaseManager instance = null;
	private static String STRING_CON = "jdbc:mysql://localhost:3306/cityweatherwebappdb";
	private static String USUARIO = "root";
	private static String CONTRASEÑA = "";
	private Connection con = null;
	
	private DatabaseManager() {
		try {
			establecerConexion();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
			con = DriverManager.getConnection(STRING_CON, USUARIO, CONTRASEÑA);
			System.out.println("Se establece la conexión con la base de datos");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR al establecer la conexión con la base de datos");
			return false;
		}
	}
	
	public void cerrarConexion() {
		try {
			con.close();
			System.out.println("Se cierra la conexion con la base de datos.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR al cerrar la conexión con la base de datos");
		}
	}
	
	public void insertarUsuario(UsuarioModel um) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO usuarios(nombreUsuario,email,contrasena) "
						+ "VALUES('" + um.getNombreUsuario() + "','" + um.getEmail() + "',"
								+ "'" + um.getPassword() + "')");
				System.out.println("Usuario insertado correctamente en la base de datos.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR al insertar un usuario en la base de datos.");
		}
	}
	
	public void insertarCiudad(CiudadModel cm) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO ciudades(nombreCiudad,tiempo,temperatura,"
						+ "temperaturaMaxima,temperaturaMinima,velocidadViento,humedad) "
						+ "VALUES('" + cm.getNombre() + "','" + cm.getTiempo() + "',"
						+ Double.toString(cm.getTemperatura()) + "," + Double.toString(cm.getTemperaturaMaxima()) +
						"," + Double.toString(cm.getTemperaturaMinima()) + "," + Double.toString(cm.getVelocidadViento())
						+ "," + Double.toString(cm.getHumedad()) + ")");
				System.out.println("Ciudad insertada correctamente en la base de datos.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR al insertar una ciudad en la base de datos.");
		}
	}
	
	public void insertarRelacion(int idusuario, int idciudad) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO usuariosciudades(idUsuarios,idCiudades) "
						+ "VALUES(" + Integer.toString(idusuario) + "," + Integer.toString(idciudad) + ")");
				System.out.println("Relación insertada correctamente en la base de datos.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR al insertar la relación en la base de datos.");
		}
	}
	
	public int obtenerIdUsuarioPorEmail(String email) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT idUsuarios FROM usuarios WHERE email = '" + email + "'");
				if (rs.next()) {
					int id = rs.getInt("idUsuarios");
					return id;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error a la hora de establecer la conexión.");
			return 0;
		}
	}
	
	public int obtenerIdCiudadPorNombre(String nombre) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT idCiudades FROM ciudades WHERE nombreCiudad = '" + nombre + "'");
				if (rs.next()) {
					int id = rs.getInt("idCiudades");
					return id;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error a la hora de establecer la conexión.");
			return 0;
		}
	}
	
	public UsuarioModel obtenerUsuario(String email) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE email = '" + email + "'");
				if (rs.next()) {
					UsuarioModel um = new UsuarioModel(rs.getString("nombreUsuario"), rs.getString("email"), rs.getString("contrasena"));
					return um;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error a la hora de establecer la conexión.");
			return null;
		}
	}
	
	public CiudadModel obtenerCiudad(String nombreCiudad) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM ciudades WHERE nombreCiudad = '" + nombreCiudad + "'");
				if (rs.next()) {
					CiudadModel cm = new CiudadModel(rs.getString("nombreCiudad"), rs.getString("tiempo"), rs.getDouble("temperatura"), 
							rs.getDouble("temperaturaMaxima"), rs.getDouble("temperaturaMinima"), rs.getDouble("velocidadViento"), 
							rs.getDouble("humedad"), false);
					return cm;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error a la hora de establecer la conexión.");
			return null;
		}
	}
	
	public ArrayList<CiudadModel> obtenerCiudadesUsuario(int idusuario) throws SQLException {
		ArrayList<CiudadModel> alcm = new ArrayList<CiudadModel>();
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM ciudades c INNER JOIN usuariosciudades uc ON"
						+ " c.idCiudades = uc.idCiudades WHERE uc.idUsuarios = " + Integer.toString(idusuario));
				while (rs.next()) {
					CiudadModel cm = new CiudadModel(rs.getString("nombreCiudad"), rs.getString("tiempo"), rs.getDouble("temperatura"), 
							rs.getDouble("temperaturaMaxima"), rs.getDouble("temperaturaMinima"), rs.getDouble("velocidadViento"), 
							rs.getDouble("humedad"), false);
					alcm.add(cm);
				}
				return alcm;
			} else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error a la hora de establecer la conexión.");
			return null;
		}
	}
	
	public void actualizarCiudad(CiudadModel cm) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				int idciudad = obtenerIdCiudadPorNombre(cm.getNombre());
				Statement stmt = con.createStatement();
				stmt.executeUpdate("UPDATE ciudades SET nombreCiudad = '" + cm.getNombre() + "',"
						+ " tiempo = '" + cm.getTiempo() + "', temperatura = " + Double.toString(cm.getTemperatura()) + ","
						+ " temperaturaMaxima = " + Double.toString(cm.getTemperaturaMaxima()) + ", temperaturaMinima = "
						+ Double.toString(cm.getTemperaturaMinima()) + ", velocidadViento = " + Double.toString(cm.getVelocidadViento()) + ", "
						+ "humedad = " + Double.toString(cm.getHumedad()) + " WHERE idCiudades = " + Integer.toString(idciudad));
				System.out.println("Ciudad " + cm.getNombre() + " actualizada correctamente.");
			} else {
				System.out.println("Error al establecer la conexión.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarRelacion(int idusuario, int idciudad) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				int filasAfectadas = stmt.executeUpdate("DELETE FROM usuariosciudades WHERE idUsuarios = " + Integer.toString(idusuario) + 
						" AND idCiudades = " + Integer.toString(idciudad));
				System.out.println("La relación fue borrada correctamente. En total " + Integer.toString(filasAfectadas) + 
						" filas han sido eliminadas.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existeRelacion(int idusuario, int idciudad) throws SQLException {
		try {
			cerrarConexion();
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuariosciudades WHERE idUsuarios = " + Integer.toString(idusuario) +
					" AND idCiudades = " + Integer.toString(idciudad));
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("No se ha establecido la conexión.");
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean loginUsuario(String email, String password) throws SQLException {
		try {
			cerrarConexion();
			if(establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT contrasena FROM usuarios WHERE email = '" + email + "'");
				if (rs.next()) {
					if (rs.getString(1).equals(password)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error a la hora de establecer la conexión.");
			return false;
		}
	}
}