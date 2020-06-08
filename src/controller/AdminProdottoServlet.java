package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.io.output.*;

import model.Categoria;
import model.CategoriaDAO;
import model.Prodotto;
import model.ProdottoDAO;

/**
 * Servlet implementation class AdminCategoria
 */
@WebServlet("/AdminProdotto")
@MultipartConfig
public class AdminProdottoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final CategoriaDAO catDAO = new CategoriaDAO();
	private final ProdottoDAO prodDAO = new ProdottoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProdottoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		checkAdmin(request);
		
		String id = request.getParameter("id");
		Prodotto prod = new Prodotto();
		
		if(id != null)
		{
	        // CONTROLLO DELL'ID
	        String nome = request.getParameter("nome");
	        String descrizione = request.getParameter("descrizione");
	        String prezzoCent = request.getParameter("prezzo");
	        List<String> catId;
	        if(request.getParameterValues("categorie") == null)
	        {
	        	System.out.println("Array null");
	        	catId = new ArrayList<>();
	        }else
	        	catId =Arrays.asList(request.getParameterValues("categorie"));		 //(ArrayList<String>) Arrays.asList(request.getParameterValues("categorie"));
	        
	        if(nome == null && descrizione == null && prezzoCent == null && catId.isEmpty())
	        {
	        	request.setAttribute("notifica", false);
	            prod = prodDAO.doRetrieveByKey(Integer.parseInt(id));
	        }else
	        {    //MODIFICA DEL PRODOTTO
	        	byte[] bytes = nome.getBytes(StandardCharsets.ISO_8859_1);
	        	nome = new String(bytes, StandardCharsets.UTF_8);
	        	bytes = descrizione.getBytes(StandardCharsets.ISO_8859_1);
	        	descrizione = new String(bytes, StandardCharsets.UTF_8);
	        	
	        	
	        	if (!(nome != null && nome.length() >= 2 && nome.matches("^[ 0-9a-zA-Z\u00C0-\u00ff]+$")))
	    		{
	    			throw new ExceptionServlet("Nome non valido.");
	    		}

	    		if (!(descrizione != null && descrizione.length() > 0 && descrizione.matches("^[ 0-9a-zA-Z\u00C0-\u00ff\\'\\°\\;\\,\\.\\(\\)\\!\\?\\[\\]]+$")))
	    		{
	    			throw new ExceptionServlet("Descrizione non valida.");
	    		}		
	    		
	    		if (!(prezzoCent != null && prezzoCent.length()>0 && prezzoCent.matches("^[0-9]+$")))
	    		{
	    			throw new ExceptionServlet("Prezzo non valido");
	    		}
	    		
	    		Part filePart = request.getPart("file");
	    		if(filePart.getSize() > 0)  //CANCELLAZIONE IMMAGINE VECCHIA ED INSERIMENTO DI NUOVA
	    		{
	    			prod = prodDAO.doRetrieveByKey(Integer.parseInt(id));
	    			File uploads = new File(getServletContext().getInitParameter("file-upload-product"));
			        File file = new File(uploads, prod.getNome() + ".jpg");
			        if(file.exists())
			        		file.delete();
			        file = new File(uploads, nome  +".jpg");
			        if(!file.exists()) {
				        try (InputStream fileContent = filePart.getInputStream())
			            {
			    			Files.copy(fileContent, file.toPath());
			            }catch(IOException e)
			            {
			            	e.printStackTrace();
			            	throw new RuntimeException("Impossibile caricare immagine");
			            }
			        }
	    		}
		        else { //CAMBIARE SOLO NOME FILE
		        	
		        	prod = prodDAO.doRetrieveByKey(Integer.parseInt(id));
	    			File uploads = new File(getServletContext().getInitParameter("file-upload-product"));
	    			File file = new File(uploads, prod.getNome() + ".jpg");
			        
	    			file.renameTo(new File(uploads,nome+".jpg"));

		        }
	    		
	    		prod.setNome(nome);
		        prod.setDescrizione(descrizione);
		        prod.setPrezzoCent(Long.parseLong(prezzoCent));
		        ArrayList<Categoria> listCat = new ArrayList<>();
		        for(String c : catId)
		        {
		        	Categoria cat = new Categoria();
		        	cat= catDAO.doRetrieveByKey(Integer.parseInt(c));
		        	listCat.add(cat);
		        }
		        prod.setCategorie(listCat);
		        prod.setId(Integer.parseInt(id));
		        prodDAO.doSaveOrUpdate(prod);
		        request.setAttribute("notifica", true); 
		                	
	        }
	        	request.setAttribute("categorie", catDAO.doRetrieveAll());
	        	request.setAttribute("prodotto", prod);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminProdotto.jsp");
	            dispatcher.forward(request, response);
	    }else //AGGIUNGI PRODOTTO NUOVO
	    {
	    		String nome = request.getParameter("nome");
		        String descrizione = request.getParameter("descrizione");
		        String prezzoCent = request.getParameter("prezzo");
		        List<String> catId;
		        
	        	byte[] bytes = nome.getBytes(StandardCharsets.ISO_8859_1);
	        	nome = new String(bytes, StandardCharsets.UTF_8);
	        	bytes = descrizione.getBytes(StandardCharsets.ISO_8859_1);
	        	descrizione = new String(bytes, StandardCharsets.UTF_8);
		        
		        if(request.getParameterValues("categorie") == null)
		        {
		        	System.out.println("Array Nuovo null");
		        	catId = new ArrayList<>();
		        }else
		        	catId =Arrays.asList(request.getParameterValues("categorie"));	
		        
		        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">	       
		        File uploads = new File(getServletContext().getInitParameter("file-upload-product"));
		        File file = new File(uploads, nome + ".jpg");
		        
		        
		        if (!(nome != null && nome.length() >= 2 && nome.matches("^[ 0-9a-zA-Z\u00C0-\u00ff]+$")))
	    		{
	    			throw new ExceptionServlet("Nome non valido.");
	    		}

	    		if (!(descrizione != null && descrizione.length() > 0 && descrizione.matches("^[ 0-9a-zA-Z\u00C0-\u00ff\\'\\°\\;\\,\\.\\(\\)\\!\\?\\[\\]]+$")))
	    		{
	    			throw new ExceptionServlet("Descrizione non valida.");
	    		}		
	    		
	    		if (!(prezzoCent != null && prezzoCent.length()>0 && prezzoCent.matches("^[0-9]+$")))
	    		{
	    			throw new ExceptionServlet("Prezzo non valido");
	    		}	    		
	    		try (InputStream fileContent = filePart.getInputStream())
	            {
	    			Files.copy(fileContent, file.toPath());
	            }catch(IOException e)
	            {
	            	e.printStackTrace();
	            	throw new ExceptionServlet("Impossibile caricare immagine");
	            }
    		
	    		prod.setNome(nome);
		        prod.setDescrizione(descrizione);
		        prod.setPrezzoCent(Long.parseLong(prezzoCent));
		        ArrayList<Categoria> listCat = new ArrayList<>();
		        for(String c : catId)
		        {
		        	Categoria cat = new Categoria();
		        	cat= catDAO.doRetrieveByKey(Integer.parseInt(c));
		        	listCat.add(cat);
		        }
		        prod.setCategorie(listCat);
		        prodDAO.doSave(prod);
		        
		        request.setAttribute("notifica", true);
	    		request.setAttribute("nome",prod.getNome());
	    		
	    		 RequestDispatcher dispatcher = request.getRequestDispatcher("ProdottoServlet");
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
