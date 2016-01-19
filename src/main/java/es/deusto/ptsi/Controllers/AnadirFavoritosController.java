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

@WebServlet(description = "Controlador para añadir ciudades favoritos.", urlPatterns = { "/anadirfavoritos" })
public class AnadirFavoritosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String email = request.getParameter("email");
		
		String nombre = request.getParameter("nombre");
		String tiempo = request.getParameter("tiempo");
		double temperatura = Double.parseDouble(request.getParameter("temperatura"));
		double temperaturaMaxima = Double.parseDouble(request.getParameter("temperaturaMaxima"));
		double temperaturaMinima = Double.parseDouble(request.getParameter("temperaturaMinima"));
		double velocidadViento = Double.parseDouble(request.getParameter("velocidadViento"));
		double humedad = Double.parseDouble(request.getParameter("humedad"));
		
		CiudadModel cm = new CiudadModel(nombre, tiempo, temperatura, temperaturaMaxima, temperaturaMinima, velocidadViento, humedad, false);
		UsuarioModel um = new UsuarioModel();
		try {
			um = DatabaseManager.getInstance().obtenerUsuario(email);
			int idusuario = DatabaseManager.getInstance().obtenerIdUsuarioPorEmail(um.getEmail());
			if (DatabaseManager.getInstance().obtenerCiudad(nombre) != null) {
				DatabaseManager.getInstance().actualizarCiudad(cm);
				
				int idciudad = DatabaseManager.getInstance().obtenerIdCiudadPorNombre(cm.getNombre());
				if (!DatabaseManager.getInstance().existeRelacion(idusuario, idciudad)) {
					DatabaseManager.getInstance().insertarRelacion(idusuario, idciudad);
				}
			} else {
				DatabaseManager.getInstance().insertarCiudad(cm);
				int idciudad = DatabaseManager.getInstance().obtenerIdCiudadPorNombre(cm.getNombre());
				DatabaseManager.getInstance().insertarRelacion(idusuario, idciudad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sesion.setAttribute("usuario", um);
		sesion.setAttribute("ciudad", cm);
		request.getRequestDispatcher("jsp/anadidaFavoritos.jsp").forward(request, response);
	}

}
