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
		
		String contraseña;
		if(request.getParameter("contraseña") != null) {
			contraseña = request.getParameter("contraseña");
		} else {
			contraseña = "";
		}
		
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, contraseña);
		
		request.setAttribute("usuario", um);
		request.getRequestDispatcher("jsp/registro.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("nombreUsuario");
		String email = request.getParameter("email");
		String contraseña = request.getParameter("contraseña");
		
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, contraseña);
		
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
