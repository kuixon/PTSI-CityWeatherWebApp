package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseManager;
import Models.UsuarioModel;

@WebServlet(description = "El controller de entrar", urlPatterns = { "/entrar" })
public class EntrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
		UsuarioModel um = new UsuarioModel(nombreUsuario, email, contraseña);
		
		request.setAttribute("usuario", um);
		request.getRequestDispatcher("jsp/entrar.jsp").forward(request, response);
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
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
		
		if (action == null) {
			um = checkCookie(request);
			if (um == null) {
				um = new UsuarioModel(nombreUsuario, email, contraseña);
				request.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/entrar.jsp").forward(request, response);
			} else {
				try {
					if (DatabaseManager.getInstance().loginUsuario(um.getEmail(), um.getContraseña())) {
						sesion.setAttribute("nombreUsuario", um.getNombreUsuario());
						request.getRequestDispatcher("jsp/loginCorrecto.jsp").forward(request, response);
					} else {
						request.setAttribute("usuario", um);
						request.getRequestDispatcher("jsp/loginErrores.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (action.equalsIgnoreCase("logout")) {
				sesion.removeAttribute("nombreUsuario");
				Cookie[] cookies = request.getCookies();
				for (Cookie ck : cookies) {
					if (ck.getName().equalsIgnoreCase("username")) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
					if (ck.getName().equalsIgnoreCase("password")) {
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
				um = new UsuarioModel(nombreUsuario, email, contraseña);
				request.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/entrar.jsp").forward(request, response);
			}
		}
	}
	
	private UsuarioModel checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		UsuarioModel um = null;
		if(cookies == null) {
			return null;
		} else {
			String email = "", contraseña = "";
			for (Cookie ck : cookies) {
				if (ck.getName().equalsIgnoreCase("username"))
					email = ck.getValue();
				if (ck.getName().equalsIgnoreCase("password"))
					contraseña = ck.getValue();
			}
			if (!email.isEmpty() && !contraseña.isEmpty()) {
				try {
					um = new UsuarioModel(DatabaseManager.getInstance().obtenerUsuario(email).getNombreUsuario(), email, contraseña);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return um;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession sesion = request.getSession();
		
		if (action == null) {
			String email = request.getParameter("email").trim();
			String contraseña = request.getParameter("contraseña").trim();
			
			try {
				if (DatabaseManager.getInstance().existeUsuarioConEmail(email)) {
					UsuarioModel um = DatabaseManager.getInstance().obtenerUsuario(email);
					
					if (DatabaseManager.getInstance().loginUsuario(email, contraseña)) {
						sesion.setAttribute("nombreUsuario", um.getNombreUsuario());
						Cookie ckEmail = new Cookie("username", um.getEmail());
						ckEmail.setMaxAge(3600);
						response.addCookie(ckEmail);
						Cookie ckContraseña = new Cookie("password", um.getContraseña());
						ckContraseña.setMaxAge(3600);
						response.addCookie(ckContraseña);
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
			
		} else {
			
		}
	}

}
