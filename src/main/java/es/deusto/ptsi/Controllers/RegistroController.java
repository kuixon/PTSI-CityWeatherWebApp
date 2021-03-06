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
import es.deusto.ptsi.Models.UsuarioModel;

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
		
		String password;
		if(request.getParameter("password") != null) {
			password = request.getParameter("password");
		} else {
			password = "";
		}
		
		UsuarioModel um = null;
		
		if (EntrarController.checkCookie(request) == null) {
			um = new UsuarioModel(nombreUsuario, email, password);
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
		String password = request.getParameter("password");
		
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, password);
		
		try {
			DatabaseManager.getInstance().establecerConexion();
			if (DatabaseManager.getInstance().obtenerUsuario(email) != null) {
				sesion.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/registroErrores.jsp").forward(request, response);
			} else {
				DatabaseManager.getInstance().insertarUsuario(um);
				sesion.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/registroCorrecto.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
