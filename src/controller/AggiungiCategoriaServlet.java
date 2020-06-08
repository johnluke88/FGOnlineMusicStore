package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;

/**
 * Servlet implementation class AggiungiCategoriaServlet
 */
@WebServlet("/AggiungiCategoria")
public class AggiungiCategoriaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final CategoriaDAO catDAO = new CategoriaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkAdmin(request);
		
		if(request.getParameter("id")==null) {
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminCategoria.jsp");
        dispatcher.forward(request, response);
		}
		else
		{
			Categoria cat = catDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id")));
			File uploads = new File(getServletContext().getInitParameter("file-upload-category"));
			File file = new File(uploads, cat.getNome() + ".jpg");
//			if(!file.delete())
//				throw new ExceptionServlet("Impossibile eliminare immagine categoria");
			file.delete();
			catDAO.doDelete(Integer.parseInt(request.getParameter("id")));
			
	        RequestDispatcher dispatcher = request.getRequestDispatcher("CategorieServlet");
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
