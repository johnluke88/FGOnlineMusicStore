package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Carrello.CartItem;
import model.ProdottoDAO;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		if (carrello == null) {
			carrello = new Carrello();
			session.setAttribute("carrello", carrello);
		}
		String prodIdStr = request.getParameter("prodId");
		if (prodIdStr != null) 								// CONTROLLO ESISTENZA PRODOTTO
		{
			int prodId = Integer.parseInt(prodIdStr);

			String addNumStr = request.getParameter("addNum");
			if (addNumStr != null) 							// CONTROLLO DI AGGIUNTA QUANTITA'
			{
				int addNum = Integer.parseInt(addNumStr);
				System.out.println(prodId);
				CartItem cartItem = carrello.get(prodId);
				
				if (cartItem != null) {
					cartItem.setQuantita(cartItem.getQuantita() + addNum);
				} else {
					carrello.addItem(prodottoDAO.doRetrieveByKey(prodId), addNum);
				}
			} else {									// CONTROLLO SE SI ELIMINA O SI SETTA LA QUANTITA'
				String setNumStr = request.getParameter("setNum");
				if (setNumStr != null) {
					int setNum = Integer.parseInt(setNumStr);
					if (setNum <= 0) {							// SE <= 0 RIMUOVI PRODOTTO
						carrello.removeItem(prodId);
					} else {									// > 0 SETTA QUANTITA' DEL PRODOTTO
						CartItem cartItem = carrello.get(prodId);
						if (cartItem != null) {
							cartItem.setQuantita(setNum);
						} else {
							carrello.addItem(prodottoDAO.doRetrieveByKey(prodId), setNum);
						}
					}
				}
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
