package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoDAO;
import model.Categoria;
import model.CategoriaDAO;
import model.Prodotto;


/**
 * Servlet implementation class AggiungiRimuoviProdottoServlet
 */
@WebServlet("/AggiungiRimuoviProdotto")
public class AggiungiRimuoviProdottoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final ProdottoDAO prodDAO = new ProdottoDAO();
	private final CategoriaDAO catDAO = new CategoriaDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiRimuoviProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkAdmin(request);
		
		if(request.getParameter("id")==null) {
			
			ArrayList<Categoria> categorie = catDAO.doRetrieveAll();
			request.setAttribute("categorie", categorie);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminProdotto.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			Prodotto prod = prodDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id")));
			File uploads = new File(getServletContext().getInitParameter("file-upload-product"));
			File file = new File(uploads, prod.getNome() + ".jpg");
			file.delete();
//			if(!file.delete())
//				throw new ExceptionServlet("Impossibile eliminare immagine prodotto");
				prodDAO.doDelete(Integer.parseInt(request.getParameter("id")));
			
	        RequestDispatcher dispatcher = request.getRequestDispatcher("");
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
