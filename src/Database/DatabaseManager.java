package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.CiudadModel;
import Models.UsuarioModel;

public class DatabaseManager {
	
	private static DatabaseManager instance = null;
	private static String STRING_CON = "jdbc:mysql://localhost:3306/cityweatherwebappdb";
	private static String USUARIO = "root";
	private static String CONTRASEÑA = "";
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
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO usuarios(nombreUsuario,email,contrasena) "
						+ "VALUES('" + um.getNombreUsuario() + "','" + um.getEmail() + "',"
								+ "'" + um.getContraseña() + "')");
				System.out.println("Usuario insertado correctamente en la base de datos.");
				cerrarConexion();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR al insertar un usuario en la base de datos.");
		}
	}
	
	public void insertarCiudad(CiudadModel cm) throws SQLException {
		try {
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO ciudades(nombreCiudad,tiempo,temperatura,"
						+ "temperaturaMaxima,temperaturaMinima,velocidadViento,humedad) "
						+ "VALUES('" + cm.getNombre() + "','" + cm.getTiempo() + "',"
						+ Double.toString(cm.getTemperatura()) + "," + Double.toString(cm.getTemperaturaMaxima()) +
						"," + Double.toString(cm.getTemperaturaMinima()) + "," + Double.toString(cm.getVelocidadViento())
						+ "," + Double.toString(cm.getHumedad()) + ")");
				System.out.println("Ciudad insertada correctamente en la base de datos.");
				cerrarConexion();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR al insertar una ciudad en la base de datos.");
		}
	}
	
	public void insertarRelacion(int idusuario, int idciudad) throws SQLException {
		try {
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO usuariosciudades(idUsuarios,idCiudades) "
						+ "VALUES(" + Integer.toString(idusuario) + "," + Integer.toString(idciudad) + ")");
				System.out.println("Relación insertada correctamente en la base de datos.");
				cerrarConexion();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR al insertar la relación en la base de datos.");
		}
	}
	
	public int obtenerIdUsuarioPorEmail(String email) throws SQLException {
		try {
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT idUsuarios FROM usuarios WHERE email = '" + email + "'");
				if (rs.next()) {
					int id = rs.getInt("idUsuarios");
					cerrarConexion();
					return id;
				} else {
					cerrarConexion();
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
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT idCiudades FROM ciudades WHERE nombreCiudad = '" + nombre + "'");
				if (rs.next()) {
					int id = rs.getInt("idCiudades");
					cerrarConexion();
					return id;
				} else {
					cerrarConexion();
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
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE email = '" + email + "'");
				if (rs.next()) {
					UsuarioModel um = new UsuarioModel(rs.getString("nombreUsuario"), rs.getString("email"), rs.getString("contrasena"));
					cerrarConexion();
					return um;
				} else {
					cerrarConexion();
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
			if (establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM ciudades WHERE nombreCiudad = '" + nombreCiudad + "'");
				if (rs.next()) {
					CiudadModel cm = new CiudadModel(rs.getString("nombreCiudad"), rs.getString("tiempo"), rs.getDouble("temperatura"), 
							rs.getDouble("temperaturaMaxima"), rs.getDouble("temperaturaMinima"), rs.getDouble("velocidadViento"), 
							rs.getDouble("humedad"), false);
					cerrarConexion();
					return cm;
				} else {
					cerrarConexion();
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
				
				cerrarConexion();
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
	
	public boolean loginUsuario(String email, String contraseña) throws SQLException {
		try {
			if(establecerConexion()) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT contrasena FROM usuarios WHERE email = '" + email + "'");
				if (rs.next()) {
					if (rs.getString(1).equals(contraseña)) {
						cerrarConexion();
						return true;
					} else {
						cerrarConexion();
						return false;
					}
				} else {
					cerrarConexion();
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
			System.out.println("Error a la hora de establecer la conexión.");
			return false;
		}
	}
}