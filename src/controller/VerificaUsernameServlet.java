package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBeanDAO;

/**
 * Servlet implementation class VerificaUsername
 */
@WebServlet("/VerificaUsername")
public class VerificaUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserBeanDAO userDAO = new UserBeanDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String usernameOld = request.getParameter("usernameOld");
		response.setContentType("text/xml");
		if (username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+$")
				&& (userDAO.doRetrieveByUsername(username) == null || username.equals(usernameOld))){
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
