package es.deusto.ptsi.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import es.deusto.ptsi.Database.DatabaseManager;
import es.deusto.ptsi.Models.CiudadModel;
import es.deusto.ptsi.Models.UsuarioModel;
import es.deusto.ptsi.Utilities.JSONObject;
import es.deusto.ptsi.Utilities.JSONReader;

@WebServlet(description = "Controller para el apartado de ciudades favoritas", urlPatterns = { "/favoritos" })
public class FavoritosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String emailUsuarioLogueado = request.getParameter("email");
		
		UsuarioModel um = new UsuarioModel();
		int idusuario = 0;
		ArrayList<CiudadModel> alcm = new ArrayList<CiudadModel>();
		try {
			DatabaseManager.getInstance().establecerConexion();
			um = DatabaseManager.getInstance().obtenerUsuario(emailUsuarioLogueado);
			idusuario = DatabaseManager.getInstance().obtenerIdUsuarioPorEmail(emailUsuarioLogueado);
			alcm = DatabaseManager.getInstance().obtenerCiudadesUsuario(idusuario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		sesion.setAttribute("usuario", um);
		sesion.setAttribute("ciudades", alcm);
		request.getRequestDispatcher("jsp/favoritos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String emailUsuarioLogueado = request.getParameter("emailUsuario");
		
		UsuarioModel um = new UsuarioModel();
		
		try {
			DatabaseManager.getInstance().establecerConexion();
			um = DatabaseManager.getInstance().obtenerUsuario(emailUsuarioLogueado);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<CiudadModel> alcm = new ArrayList<CiudadModel>();
		
		boolean fin = false;
		int cont = 1;
		while (!fin) {
			String nomCiudad = request.getParameter("ciudad" + cont) != null ? request.getParameter("ciudad" + cont) : "";
			if (!nomCiudad.equals("")) {
				CiudadModel cm = new CiudadModel();
				cm.setNombre(nomCiudad);
				
				nomCiudad = nomCiudad.replaceAll("\\s","");
				JSONObject json = JSONReader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + nomCiudad + "&units=metric&APPID=c62dd1a67db43c63b19e933e51028163");
				
				String tiempo = "";
			    for (int i = 0; i < json.getJSONArray("weather").length(); i++) {
			    	if (i != json.getJSONArray("weather").length()-1) {
			    		tiempo += ((JSONObject) json.getJSONArray("weather").get(i)).getString("main") + "#" + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description") + "#";
			    	} else {
			    		tiempo += ((JSONObject) json.getJSONArray("weather").get(i)).getString("main") + "#" + ((JSONObject) json.getJSONArray("weather").get(i)).getString("description");
			    	}
			    }
			    
			    cm.setTiempo(tiempo);
			    cm.setTemperatura(json.getJSONObject("main").getDouble("temp"));
				cm.setTemperaturaMaxima(json.getJSONObject("main").getDouble("temp_max"));
				cm.setTemperaturaMinima(json.getJSONObject("main").getDouble("temp_min"));
				cm.setVelocidadViento((json.getJSONObject("wind").getDouble("speed") * 3600) / 1000);
				cm.setHumedad(json.getJSONObject("main").getDouble("humidity"));
				cm.setFavoritos(true);
				
				alcm.add(cm);
				cont++;
				
				try {
					DatabaseManager.getInstance().establecerConexion();
					DatabaseManager.getInstance().actualizarCiudad(cm);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				fin = true;
			}
		}
		
		sesion.setAttribute("usuario", um);
		sesion.setAttribute("ciudades", alcm);
		request.getRequestDispatcher("jsp/favoritos.jsp").forward(request, response);
	}

}
