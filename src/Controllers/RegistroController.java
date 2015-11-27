package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Models.UsuarioModel;

@WebServlet(description = "Controller de registro", urlPatterns = { "/registro" })
public class RegistroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("html/registro.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("nombreUsuario");
		String email = request.getParameter("email");
		String contraseña = request.getParameter("contraseña");
		
		try {
			if (DatabaseManager.getInstance().existeUsuarioConEmail(email)) {
				request.getRequestDispatcher("jsp/registroErrores.jsp").forward(request, response);
			} else {
				UsuarioModel um = new UsuarioModel(nombreUsuario, email, contraseña);
				DatabaseManager.getInstance().insertarUsuario(um);
				request.getRequestDispatcher("jsp/registroCorrecto.jsp").forward(request, response);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
