package es.deusto.ptsi.Controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.deusto.ptsi.Models.UsuarioModel;

@WebServlet(description = "El controlador index.", urlPatterns = { "/index" })
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		UsuarioModel um = null;
		if (EntrarController.checkCookie(request) == null) {
			um = new UsuarioModel("", "", "");
			sesion.setAttribute("usuario", um);
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		} else {
			um = EntrarController.checkCookie(request);
			sesion.setAttribute("usuario", um);
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		}
	}
}
