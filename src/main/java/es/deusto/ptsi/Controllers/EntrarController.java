package es.deusto.ptsi.Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.deusto.ptsi.Database.DatabaseManager;
import es.deusto.ptsi.Models.UsuarioModel;

@WebServlet(description = "El controller de entrar", urlPatterns = { "/entrar" })
public class EntrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		String contrase�a;
		if(request.getParameter("contrase�a") != null) {
			contrase�a = request.getParameter("contrase�a");
		} else {
			contrase�a = "";
		}
		
		UsuarioModel um = null;
		
		if (action == null) {
			um = checkCookie(request);
			if (um == null) {
				um = new UsuarioModel(nombreUsuario, email, contrase�a);
				sesion.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/entrar.jsp").forward(request, response);
			} else {
				try {
					if (DatabaseManager.getInstance().loginUsuario(um.getEmail(), um.getContrase�a())) {
						sesion.setAttribute("usuario", um);
						request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
					} else {
						sesion.setAttribute("usuario", um);
						request.getRequestDispatcher("jsp/loginErrores.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (action.equalsIgnoreCase("logout")) {
				sesion.removeAttribute("usuario");
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
				um = new UsuarioModel(nombreUsuario, email, contrase�a);
				sesion.setAttribute("usuario", um);
				request.getRequestDispatcher("jsp/entrar.jsp").forward(request, response);
			}
		}
	}
	
	public static UsuarioModel checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		UsuarioModel um = null;
		if(cookies == null) {
			return null;
		} else {
			String email = "", contrase�a = "";
			for (Cookie ck : cookies) {
				if (ck.getName().equalsIgnoreCase("username"))
					email = ck.getValue();
				if (ck.getName().equalsIgnoreCase("password"))
					contrase�a = ck.getValue();
			}
			if (!email.isEmpty() && !contrase�a.isEmpty()) {
				try {
					um = new UsuarioModel(DatabaseManager.getInstance().obtenerUsuario(email).getNombreUsuario(), email, contrase�a);
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
			String contrase�a = request.getParameter("contrase�a").trim();
			
			try {
				UsuarioModel um = DatabaseManager.getInstance().obtenerUsuario(email);
				if (um != null) {
					
					if (DatabaseManager.getInstance().loginUsuario(email, contrase�a)) {
						sesion.setAttribute("usuario", um);
						Cookie ckEmail = new Cookie("username", um.getEmail());
						ckEmail.setMaxAge(3600);
						response.addCookie(ckEmail);
						Cookie ckContrase�a = new Cookie("password", um.getContrase�a());
						ckContrase�a.setMaxAge(3600);
						response.addCookie(ckContrase�a);
						request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
					} else {
						sesion.setAttribute("usuario", um);
						request.getRequestDispatcher("jsp/loginErrores.jsp").forward(request, response);
					}
				} else {
					UsuarioModel um2 = new UsuarioModel("",email,"");
					
					sesion.setAttribute("usuario", um2);
					request.getRequestDispatcher("jsp/loginErrores.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			
		}
	}

}
