package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserBeanDAO;

/**
 * Servlet implementation class ProfiloServlet
 */
@WebServlet("/ProfiloServlet")
public class ProfiloServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
      private final UserBeanDAO userBeanDAO = new UserBeanDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfiloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserBean user =(UserBean) request.getSession().getAttribute("utente");
		if(user == null)
			throw new ExceptionServlet("Utente non autorizzato");
        
		if(request.getParameter("modifica")!=null)
		{
			request.setAttribute("notifica", true);
			request.setAttribute("utente", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("rimuovi")!=null)
		{
			userBeanDAO.doDelete(user.getId());
			request.getSession().setAttribute("utente", null);
			
			String dest = request.getHeader("referer");
			if (dest == null || dest.contains("/UpdateProfilo") || dest.contains("/ProfiloServlet") 
					|| dest.trim().isEmpty()) {
	    		dest = ".";
	    	}
	    	response.sendRedirect(dest);
		}
		else {
			request.setAttribute("utente", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilo.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
