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
		
		String nombreUsuario;
		if(request.getParameter("nombreUsuario") != null) {
			nombreUsuario = request.getParameter("nombreUsuario");
		} else {
			nombreUsuario = "";
		}
		
		String email;
		if(request.getParameter("email") != null) {
			email = request.getParameter("email");
		} else {
			email = "";
		}
		
		String contrase�a;
		if(request.getParameter("contrase�a") != null) {
			contrase�a = request.getParameter("contrase�a");
		} else {
			contrase�a = "";
		}
		
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, contrase�a);
		
		request.setAttribute("usuario", um);
		request.getRequestDispatcher("jsp/registro.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("nombreUsuario");
		String email = request.getParameter("email");
		String contrase�a = request.getParameter("contrase�a");
		
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, contrase�a);
		
		try {
			if (DatabaseManager.getInstance().existeUsuarioConEmail(email)) {
				request.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/registroErrores.jsp").forward(request, response);
			} else {
				DatabaseManager.getInstance().insertarUsuario(um);
				request.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/registroCorrecto.jsp").forward(request, response);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
