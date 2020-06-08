package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class ModificaProdottoServlet
 */
@WebServlet("/ModificaProdotto")
public class ModificaProdottoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final ProdottoDAO prodDAO = new ProdottoDAO();
	private final CategoriaDAO catDAO = new CategoriaDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkAdmin(request);
		
		ArrayList<Categoria> categorie = catDAO.doRetrieveAll();
		Prodotto prod = prodDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("prodotto", prod);
		request.setAttribute("categorie", categorie);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminProdotto.jsp");
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
