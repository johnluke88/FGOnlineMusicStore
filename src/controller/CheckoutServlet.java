package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.Carrello.CartItem;
import model.Ordine;
import model.OrdineDAO;
import model.UserBean;


/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private final OrdineDAO ordDAO = new OrdineDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
		if(cart==null)
			throw new ExceptionServlet("Carrello vuoto");
		
		
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		int utenteId = utente.getId();
		
		int idProd;
		int quantitaProd;
		long prezzoProd;
				
		ArrayList<CartItem> array = new ArrayList<>(cart.getLista());
		
		ArrayList<Ordine> ordini = new ArrayList<>();
		int key = ordDAO.doRetrieveLastNumOrd();
		

		for(CartItem ci : array)
		{	
			System.out.println(ci);
			idProd = ci.getProdotto().getId();
			quantitaProd = ci.getQuantita();
			prezzoProd = ci.getPrezzoTotCent();
			Ordine ord = new Ordine();
			ord.setIdUtente(utenteId);
			ord.setIdProdotto(idProd);
			ord.setQuantita(quantitaProd);
			ord.setTotaleP(prezzoProd);
			ord.setData(new Date(new GregorianCalendar().getTimeInMillis()));
			ord.setNumOrd(key);
			System.out.println(ord);
			ordini.add(ord);
		}
		ordDAO.doSave(ordini);
		
		request.getSession().removeAttribute("carrello");
		request.getRequestDispatcher("WEB-INF/jsp/ordineSuccess.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
