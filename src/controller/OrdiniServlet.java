package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ordine;
import model.OrdineDAO;
import model.ProdottoDAO;
import model.UserBean;
import model.UserBeanDAO;

/**
 * Servlet implementation class OrdiniServlet
 */
@WebServlet("/OrdiniServlet")
public class OrdiniServlet extends  BaseServlet {
	private static final long serialVersionUID = 1L;
	private final OrdineDAO ordDAO = new OrdineDAO();
	private final ProdottoDAO prodDAO = new ProdottoDAO();
	private final UserBeanDAO userDAO = new UserBeanDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdiniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cliente = request.getParameter("id");
		
		ArrayList<Ordine> lista = new ArrayList<>();
		LinkedHashMap<Integer, String> nomi = new LinkedHashMap<>();
		if(cliente != null) {
			checkAdmin(request);
			
			lista = ordDAO.doRetrieveById(Integer.parseInt(cliente));
			for(Ordine o: lista)
			{
				String nome = prodDAO.doRetrieveByKey(o.getIdProdotto()).getNome();
				Integer id =(Integer) o.getIdProdotto();
				nomi.put(id, nome);
			}
			request.setAttribute("notifica", true);
			request.setAttribute("cliente", userDAO.doRetrieveByKey(Integer.parseInt(cliente)));
		}
		else {
			
			UserBean user =(UserBean) request.getSession().getAttribute("utente");
			if(user == null)
				throw new ExceptionServlet("Utente non autorizzato");
			lista = ordDAO.doRetrieveById(user.getId());
			for(Ordine o: lista)
			{
				String nome = prodDAO.doRetrieveByKey(o.getIdProdotto()).getNome();
				Integer id =(Integer) o.getIdProdotto();
				nomi.put(id, nome);
			}
		}
		request.setAttribute("nomi", nomi);
		request.setAttribute("lista", lista);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mieiOrdini.jsp");
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
