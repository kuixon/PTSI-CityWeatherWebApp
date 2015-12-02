package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseManager;
import Models.UsuarioModel;

@WebServlet(description = "El controller de entrar", urlPatterns = { "/entrar" })
public class EntrarController extends HttpServlet {
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
		
		String contraseņa;
		if(request.getParameter("contraseņa") != null) {
			contraseņa = request.getParameter("contraseņa");
		} else {
			contraseņa = "";
		}
		
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, contraseņa);
		
		request.setAttribute("usuario", um);
		request.getRequestDispatcher("jsp/entrar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String contraseņa = request.getParameter("contraseņa");
		
		try {
			if (DatabaseManager.getInstance().existeUsuarioConEmail(email)) {
				UsuarioModel um = DatabaseManager.getInstance().obtenerUsuario(email);
				
				if (DatabaseManager.getInstance().loginUsuario(email, contraseņa)) {
					request.setAttribute("usuario", um);
					request.getRequestDispatcher("jsp/loginCorrecto.jsp").forward(request, response);
				} else {
					request.setAttribute("usuario", um);
					request.getRequestDispatcher("jsp/loginErrores.jsp").forward(request, response);
				}
			} else {
				UsuarioModel um = new UsuarioModel("",email,"");
				
				request.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/loginErrores.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
