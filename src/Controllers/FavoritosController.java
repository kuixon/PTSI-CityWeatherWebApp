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
import Models.UsuarioModel;

@WebServlet(description = "Controller para el apartado de ciudades favoritas", urlPatterns = { "/favoritos" })
public class FavoritosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String emailUsuarioLogueado = request.getParameter("email");
		
		UsuarioModel um = new UsuarioModel();
		
		try {
			um = DatabaseManager.getInstance().obtenerUsuario(emailUsuarioLogueado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sesion.setAttribute("usuario", um);
		request.getRequestDispatcher("jsp/favoritos.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
