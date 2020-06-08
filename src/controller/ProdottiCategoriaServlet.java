package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class ProdottiCategoriaServlet
 */
@WebServlet("/ProdottiCategoriaServlet")
public class ProdottiCategoriaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();
	private final CategoriaDAO catDAO = new CategoriaDAO();
	private int currentPage;
	private int recordsPerPage;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottiCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		recordsPerPage=9;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Prodotto> prod = new ArrayList<>() ;
		
		if((request.getParameter("currentPage")==null)&& (request.getParameter("recordsPerPage") == null))
		{
			currentPage=1;			
			prod = prodottoDAO.doRetrieveByCategoria(Integer.parseInt(request.getParameter("id")), currentPage,recordsPerPage);
		}
		else {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
	        recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
	        
	        prod = prodottoDAO.doRetrieveByCategoria(Integer.parseInt(request.getParameter("id")), currentPage,recordsPerPage);
		}
		System.out.println(prod);
		if(prod.isEmpty())
			throw new ExceptionServlet("Nessun prodotto nella categoria selezionata");
		
	     int rows = prodottoDAO.getNumberOfRowsCategory(Integer.parseInt(request.getParameter("id"))); 
		 int nOfPages = rows / recordsPerPage;
		        
	     if (rows % recordsPerPage > 0) 
	     {
	            nOfPages++;
	     }
		
		request.setAttribute("prodotti", prod);
		request.setAttribute("categoria", catDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodottiCategoria.jsp");
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
