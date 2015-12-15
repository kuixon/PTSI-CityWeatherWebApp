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
		
		
		CiudadModel cm = new CiudadModel();
		cm.setNombre(nombreCiudad);
		cm.setTiempo("Soleado");
		cm.setTemperatura(22.5);
		cm.setTemperaturaMaxima(28.6);
		cm.setTemperaturaMinima(18);
		cm.setVelocidadViento(20.1);
		cm.setHumedad(89);
		
		sesion.setAttribute("usuario", um);
		sesion.setAttribute("ciudad", cm);
		request.getRequestDispatcher("jsp/buscar.jsp").forward(request, response);
	}

}
