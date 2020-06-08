package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserBeanDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final UserBeanDAO userBeanDAO = new UserBeanDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserBean user = null;
		if(username != null && password != null)
			user = userBeanDAO.doRetrieveByUsernameAndPassWord(username, password);		
		if(user == null)
		{         
			throw new ExceptionServlet("Username e/o Password non validi");
		}
		else 
			request.getSession().setAttribute("utente", user);
		
		String dest = request.getHeader("referer");
	    if (dest == null || dest.contains("/LoginServlet") || dest.contains("/AdminUpdateUserBeanServlet") || dest.contains("/AdminCategoria")
		|| dest.contains("/AdminProdotto")|| dest.contains("/AdminUserBeanServlet")|| dest.contains("/AggiungiCategoria")|| dest.contains("/AggiungiRimuoviProdotto")
		|| dest.contains("/CheckoutServlet")|| dest.contains("/ModificaProdotto")|| dest.contains("/OrdiniServlet")
		|| dest.contains("/Riepilogo") || dest.contains("/UpdateProfilo") || dest.trim().isEmpty()) {
	      dest = ".";
	    }
	    response.sendRedirect(dest);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
