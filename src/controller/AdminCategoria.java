package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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

/**
 * Servlet implementation class AdminCategoria
 */
@WebServlet("/AdminCategoria")
@MultipartConfig
public class AdminCategoria extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private final CategoriaDAO catDAO = new CategoriaDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		checkAdmin(request);
		
		String id = request.getParameter("id");
		Categoria cat = new Categoria();
		
		if(id != null)
		{
	        // CONTROLLO DELL'ID
	        String nome = request.getParameter("nome");
	        String descrizione = request.getParameter("descrizione");
	        
	        if(nome == null && descrizione == null)
	        {
	        	request.setAttribute("notifica", false);
	            cat = catDAO.doRetrieveByKey(Integer.parseInt(id));
	        }else
	        {    //MODIFICA DELLA CATEGORIA
	        	byte[] bytes = nome.getBytes(StandardCharsets.ISO_8859_1);
	        	nome = new String(bytes, StandardCharsets.UTF_8);
	        	bytes = descrizione.getBytes(StandardCharsets.ISO_8859_1);
	        	descrizione = new String(bytes, StandardCharsets.UTF_8);
	        	if (!(nome != null && nome.trim().length() >= 2 && nome.matches("^[ 0-9a-zA-Z\u00C0-\u00ff]+$")))
	    		{
	    			throw new ExceptionServlet("Nome non valido.");
	    		}
	    		if (!(descrizione != null && descrizione.trim().length() > 0 && descrizione.matches("^[ 0-9a-zA-Z\\u00C0-\\u00ff\\!\\?\\,\\.\\;\\(\\)\\[\\]\r\n]+$")))
	    		{
	    			throw new ExceptionServlet("Descrizione non valida.");
	    		}		
	    		
	    		Part filePart = request.getPart("file");
	    		if(filePart.getSize() > 0)  //CANCELLAZIONE IMMAGINE VECCHIA ED INSERIMENTO DI NUOVA
	    		{
	    			cat = catDAO.doRetrieveByKey(Integer.parseInt(id));
	    			File uploads = new File(getServletContext().getInitParameter("file-upload-category"));
			        File file = new File(uploads, cat.getNome() + ".jpg");
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
		        	
		        	cat = catDAO.doRetrieveByKey(Integer.parseInt(id));
	    			File uploads = new File(getServletContext().getInitParameter("file-upload-category"));
	    			File file = new File(uploads, cat.getNome() + ".jpg");
			        
	    			file.renameTo(new File(uploads,nome+".jpg"));

		        }
	    		
	    		cat.setNome(nome);
		        cat.setDescrizione(descrizione);
		        cat.setId(Integer.parseInt(id));
		        catDAO.doSaveOrUpdate(cat);
		        request.setAttribute("notifica", true); 
		      
	        	
	        }
	            request.setAttribute("categoria", cat);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminCategoria.jsp");
	            dispatcher.forward(request, response);
	    }else //AGGIUNGI CATEGORIA NUOVA
	    {
		        String nome = request.getParameter("nome");
		        String descrizione = request.getParameter("descrizione");
		       
	        	byte[] bytes = nome.getBytes(StandardCharsets.ISO_8859_1);
	        	nome = new String(bytes, StandardCharsets.UTF_8);
	        	bytes = descrizione.getBytes(StandardCharsets.ISO_8859_1);
	        	descrizione = new String(bytes, StandardCharsets.UTF_8);
		        
		        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">	       
		        File uploads = new File(getServletContext().getInitParameter("file-upload-category"));
		        File file = new File(uploads, nome + ".jpg");
		        
		        
		        if (!(nome != null && nome.trim().length() >= 2 && nome.matches("^[ 0-9a-zA-Z\u00C0-\u00ff]+$")))
	    		{
	    			throw new ExceptionServlet("Nome non valido.");
	    		}

	    		if (!(descrizione != null && descrizione.trim().length() >= 0 && descrizione.matches("^[ 0-9a-zA-Z\\u00C0-\\u00ff\\!\\?\\,\\.\\;\\(\\)\\[\\]]+$")))
	    		{
	    			throw new ExceptionServlet("Descrizione non valida.");
	    		}
	    		try (InputStream fileContent = filePart.getInputStream())
	            {
	    			Files.copy(fileContent, file.toPath());
	            }catch(IOException e)
	            {
	            	e.printStackTrace();
	            	throw new ExceptionServlet("Impossibile caricare immagine");
	            }
    		
	    		cat.setNome(nome);
	    		cat.setDescrizione(descrizione);
	    		
	    		
	    		catDAO.doSave(cat);
	    		request.setAttribute("notifica", true);
	    		
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
