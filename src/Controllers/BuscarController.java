package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseManager;
import Models.CiudadModel;
import Models.UsuarioModel;

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
		
		//INICIO - APARTADO DONDE OBTENDRÍAMOS LOS DATOS DE LA API
		CiudadModel cm = new CiudadModel();
		cm.setNombre(nombreCiudad);
		cm.setTiempo("Lluvia");
		cm.setTemperatura(9.8);
		cm.setTemperaturaMaxima(14.5);
		cm.setTemperaturaMinima(2.5);
		cm.setVelocidadViento(40.3);
		cm.setHumedad(98);
		//FIN - APARTADO DONDE OBTENDRÍAMOS LOS DATOS DE LA APIS
		
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
