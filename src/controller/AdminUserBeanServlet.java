package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBeanDAO;
import model.UserBean;

/**
 * Servlet implementation class AdminUserBeanServlet
 */
@WebServlet("/AdminUserBeanServlet")
public class AdminUserBeanServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private final UserBeanDAO userBeanDAO = new UserBeanDAO();   
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserBeanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			checkAdmin(request);
			
			if(request.getParameter("rimuovi")!=null) {
		        int idUser =Integer.parseInt(request.getParameter("id"));
		        userBeanDAO.doDelete(idUser);
			}
			ArrayList<UserBean> users = userBeanDAO.doRetrieveAll();
			request.setAttribute("utenti", users);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminUserBean.jsp");
			dispatcher.forward(request, response);
			
		}
		catch(RuntimeException e){
			response.sendRedirect(".");
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
