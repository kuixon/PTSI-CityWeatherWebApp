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

@WebServlet(description = "Controlador para quitar ciudades de las listas de favoritos de cada usuario.", urlPatterns = { "/quitarfavoritos" })
public class QuitarFavoritosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String nombreCiudad = request.getParameter("nombreCiudad");
		String emailUsuario = request.getParameter("emailUsuario");
		
		UsuarioModel um = new UsuarioModel();
		CiudadModel cm = new CiudadModel();
		try {
			um = DatabaseManager.getInstance().obtenerUsuario(emailUsuario);
			cm = DatabaseManager.getInstance().obtenerCiudad(nombreCiudad);
			int idusuario = DatabaseManager.getInstance().obtenerIdUsuarioPorEmail(um.getEmail());
			int idciudad = DatabaseManager.getInstance().obtenerIdCiudadPorNombre(cm.getNombre());
			
			DatabaseManager.getInstance().eliminarRelacion(idusuario, idciudad);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sesion.setAttribute("usuario", um);
		sesion.setAttribute("ciudad", cm);
		request.getRequestDispatcher("jsp/quitadaFavoritos.jsp").forward(request, response);
	}

}
