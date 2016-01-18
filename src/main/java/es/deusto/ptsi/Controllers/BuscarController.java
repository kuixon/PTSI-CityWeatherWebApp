package es.deusto.ptsi.Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.deusto.ptsi.Database.DatabaseManager;
import es.deusto.ptsi.Models.CiudadModel;
import es.deusto.ptsi.Models.UsuarioModel;
import es.deusto.ptsi.Utilities.JSONObject;
import es.deusto.ptsi.Utilities.JSONReader;

@WebServlet(description = "Controller para la acción buscar.", urlPatterns = { "/buscar" })
public class BuscarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String nombreCiudad = request.getParameter("nombreCiudad");
		String emailUsuarioLogin = request.getParameter("emailUsuarioLogin");
		
		UsuarioModel um = new UsuarioModel();
		if (!emailUsuarioLogin.equals("")) {
			try {
				um = DatabaseManager.getInstance().obtenerUsuario(emailUsuarioLogin);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			um.setNombreUsuario("");
			um.setEmail("");
			um.setContraseña("");
		}
		
		String ciudadQuery = nombreCiudad.replaceAll("\\s","");
		JSONObject json = JSONReader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + ciudadQuery + "&units=metric&APPID=c62dd1a67db43c63b19e933e51028163");
		CiudadModel cm = new CiudadModel();
		
	    String tiempo = "";
	    for (int i = 0; i < json.getJSONArray("weather").length(); i++) {
	    	if (i != json.getJSONArray("weather").length()-1) {
	    		tiempo += ((JSONObject) json.getJSONArray("weather").get(i)).getString("main") + "#" + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description") + "#";
	    	} else {
	    		tiempo += ((JSONObject) json.getJSONArray("weather").get(i)).getString("main") + "#" + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description");
	    	}
	    }
	    
	    cm.setNombre(nombreCiudad);
	    cm.setTiempo(tiempo);
	    cm.setTemperatura(json.getJSONObject("main").getDouble("temp"));
		cm.setTemperaturaMaxima(json.getJSONObject("main").getDouble("temp_max"));
		cm.setTemperaturaMinima(json.getJSONObject("main").getDouble("temp_min"));
		cm.setVelocidadViento((json.getJSONObject("wind").getDouble("speed") * 3600) / 1000);
		cm.setHumedad(json.getJSONObject("main").getDouble("humidity"));
	    
		try {
			if (DatabaseManager.getInstance().obtenerCiudad(nombreCiudad) != null) {
				int idciudad = DatabaseManager.getInstance().obtenerIdCiudadPorNombre(nombreCiudad);
				int idusuario = DatabaseManager.getInstance().obtenerIdUsuarioPorEmail(emailUsuarioLogin);
				
				if (DatabaseManager.getInstance().existeRelacion(idusuario, idciudad)) {
					cm.setFavoritos(true);
				} else {
					cm.setFavoritos(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sesion.setAttribute("usuario", um);
		sesion.setAttribute("ciudad", cm);
		request.getRequestDispatcher("jsp/buscar.jsp").forward(request, response);
	}

}
