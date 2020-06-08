package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBeanDAO;

/**
 * Servlet implementation class VerificaEmailServlet
 */
@WebServlet("/VerificaEmail")
public class VerificaEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserBeanDAO userDAO = new UserBeanDAO();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		String mailOld = request.getParameter("mailOld");
		response.setContentType("text/xml");
		if (mail != null && mail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")
				&& (userDAO.doRetrieveByMail(mail) == null || mail.equals(mailOld))) {
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
