package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoDAO;
import model.Prodotto;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();
	private int currentPage;
	private int recordsPerPage;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		recordsPerPage = 9;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Prodotto> lista = new ArrayList<>();
	    
		
		if((request.getParameter("currentPage") == null)&& (request.getParameter("recordsPerPage") == null))
		{
			currentPage = 1;
			lista = prodottoDAO.doRetrieveByAll(currentPage, recordsPerPage);
		}
		else {
	        currentPage = Integer.valueOf(request.getParameter("currentPage"));
	        recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
	        lista = prodottoDAO.doRetrieveByAll(currentPage, recordsPerPage);
		}	    
	    
	    request.setAttribute("prodotti", lista);
	  
	    int rows = prodottoDAO.getNumberOfRows();
	        
	    int nOfPages = rows / recordsPerPage;
	        
        if (rows % recordsPerPage > 0) 
        {
            nOfPages++;
        }
        
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
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
