package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import model.UserBean;

/**
 * Servlet implementation class BaseServlet
 */

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void checkAdmin(HttpServletRequest request) throws ServletException {
        UserBean utente = (UserBean) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isAdmin()) {
        	throw new ExceptionServlet("Utente non autorizzato");
        }
    }
}
