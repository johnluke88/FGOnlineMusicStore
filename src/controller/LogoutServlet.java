package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("utente");
		
		String dest = request.getHeader("referer");
	    if (dest == null || dest.contains("/LogoutServlet") || dest.contains("/AdminUpdateUserBeanServlet") || dest.contains("/AdminCategoria")
	    		|| dest.contains("/AdminProdotto")|| dest.contains("/AdminUserBeanServlet")|| dest.contains("/AggiungiCategoria")|| dest.contains("/AggiungiRimuoviProdotto")
	    		|| dest.contains("/CheckoutServlet")|| dest.contains("/ModificaProdotto")|| dest.contains("/OrdiniServlet")
	    		|| dest.contains("/Riepilogo") || dest.contains("/UpdateProfilo")||dest.contains("/RegistrazioneServlet") || dest.trim().isEmpty()) {
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
