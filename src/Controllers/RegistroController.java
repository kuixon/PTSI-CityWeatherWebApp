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

@WebServlet(description = "Controller de registro", urlPatterns = { "/registro" })
public class RegistroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
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
		
		UsuarioModel um = null;
		
		if (EntrarController.checkCookie(request) == null) {
			um = new UsuarioModel(nombreUsuario, email, contraseña);
			sesion.setAttribute("usuario", um);
			request.getRequestDispatcher("jsp/registro.jsp").forward(request, response);
		} else {
			um = EntrarController.checkCookie(request);
			sesion.setAttribute("usuario", um);
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String nombreUsuario = request.getParameter("nombreUsuario");
		String email = request.getParameter("email");
		String contraseña = request.getParameter("contraseña");
		
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, contraseña);
		
		try {
			if (DatabaseManager.getInstance().obtenerUsuario(email) != null) {
				sesion.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/registroErrores.jsp").forward(request, response);
			} else {
				DatabaseManager.getInstance().insertarUsuario(um);
				sesion.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/registroCorrecto.jsp").forward(request, response);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
