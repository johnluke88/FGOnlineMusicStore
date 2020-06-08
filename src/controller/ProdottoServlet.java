package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class ProdottoServlet
 */
@WebServlet("/ProdottoServlet")
public class ProdottoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Prodotto prod = prodottoDAO.doRetrieveByName(request.getParameter("nome"));
		if(prod == null)
			throw new ExceptionServlet("Prodotto non trovato");
		
		request.setAttribute("prodotto", prod);
		if(Boolean.parseBoolean(request.getParameter("notifica")))
			request.setAttribute("notifica", true);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
