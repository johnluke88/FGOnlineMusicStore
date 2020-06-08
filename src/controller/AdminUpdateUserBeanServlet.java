package controller;
 
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.UserBean;
import model.UserBeanDAO;
 
/**
 * Servlet implementation class AdminUpdateOrDeleteUserBeanServlet
 */
@WebServlet("/AdminUpdateUserBeanServlet")
public class AdminUpdateUserBeanServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private final UserBeanDAO userBeanDAO = new UserBeanDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateUserBeanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        checkAdmin(request);
       
        String idUser = request.getParameter("id");
       
        
        // MODIFICA DELL'UTENTE
        String usr = request.getParameter("username");
        String psw = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("telefono");
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String mail = request.getParameter("mail");
        boolean admin =Boolean.parseBoolean(request.getParameter("admin"));

        UserBean userBean = new UserBean();
        if(usr == null && psw == null && nome == null && cognome == null && telefono == null 
        		&& via == null && citta == null && cap == null && mail == null && admin==false)
        {
        	request.setAttribute("notifica", false);
            userBean = userBeanDAO.doRetrieveByKey(Integer.parseInt(idUser));   		
        } else {
        	byte[] bytes = nome.getBytes(StandardCharsets.ISO_8859_1);
        	nome = new String(bytes, StandardCharsets.UTF_8);
        	bytes = cognome.getBytes(StandardCharsets.ISO_8859_1);
        	cognome = new String(bytes, StandardCharsets.UTF_8);
        	bytes = via.getBytes(StandardCharsets.ISO_8859_1);
        	via = new String(bytes, StandardCharsets.UTF_8);
        	bytes = citta.getBytes(StandardCharsets.ISO_8859_1);
        	citta = new String(bytes, StandardCharsets.UTF_8);
    		
    		if (!(usr != null && usr.length() >= 6 && usr.matches("^[0-9a-zA-Z]+$")))
    		{
    			throw new ExceptionServlet("Username non valido.");
    		}

    		if (!(psw.equals("") || (psw.length() >= 8 && psw.matches(".*[0-9].*"))))
    		{
    			throw new ExceptionServlet("Password non valida.");
    		}
    		
    		if (!(nome != null && nome.trim().length() > 0 && nome.matches("^[ a-zA-Z\u00C0-\u00ff]+$")))
    		{
    			throw new ExceptionServlet("Nome non valido.");
    		}
    		
    		if (!(cognome != null && cognome.trim().length() > 0 && cognome.matches("^[ a-zA-Z\u00C0-\u00ff]+$")))
    		{
    			throw new ExceptionServlet("Cognome non valido.");
    		}
    		
    		if (!(telefono != null && telefono.matches("^[0-9]{10}$")))
    		{
    			throw new ExceptionServlet("Telefono non valido.");
    		}

    		if (!(mail != null && mail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")))
    		{
    			throw new ExceptionServlet("Email non valida.");
    		}
    		
    		if (!(via != null && via.trim().length() > 0 && via.matches("^[ 0-9a-zA-Z\u00C0-\u00ff]+$")))
    		{
    			throw new ExceptionServlet("Via non valida.");
    		}
    		
    		if (!(citta != null && citta.trim().length() > 0 && citta.matches("^[ a-zA-Z\u00C0-\u00ff]+$")))
    		{
    			throw new ExceptionServlet("Citta' non valida.");
    		}
    		
    		if (!(cap != null && cap.matches("^[0-9]{5}$")))
    		{
    			throw new ExceptionServlet("Cap non valido.");
    		}
    		userBean.setUsr(usr);
    		if(!psw.equals(""))
    			userBean.encryptThisString(psw);
    		else
    			userBean.setPsw(userBeanDAO.doRetrieveByKey(Integer.parseInt(idUser)).getPsw());
	        userBean.setNome(nome);
	        userBean.setCognome(cognome);
	        userBean.setTelefono(telefono);
	        userBean.setVia(via);
	        userBean.setCitta(citta);
	        userBean.setCap(cap);
	        userBean.setMail(mail);
	        userBean.setAdmin(admin);   
	        userBean.setId(Integer.parseInt(idUser));
	        userBeanDAO.doSaveOrUpdate(userBean);
	        request.setAttribute("notifica", true); 	
        }
        request.setAttribute("user", userBean);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminUpdateUtente.jsp");
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