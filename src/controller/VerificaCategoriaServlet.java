package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaDAO;

/**
 * Servlet implementation class VerificaUsername
 */
@WebServlet("/VerificaCategoria")
public class VerificaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final CategoriaDAO catDAO = new CategoriaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String nomeOld = request.getParameter("nomeOld");
		response.setContentType("text/xml");
		if (nome != null && nome.length() >= 2 && nome.matches("^[ 0-9a-zA-Z\u00C0-\u00ff]+$")
				&& (catDAO.doRetrieveByName(nome) == null || nome.equals(nomeOld))) {
			response.getWriter().append("<ok/>");
		} else {
			response.getWriter().append("<no/>");
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
