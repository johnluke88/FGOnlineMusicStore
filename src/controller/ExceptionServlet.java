package controller;
 
import javax.servlet.ServletException;
 
public class ExceptionServlet extends ServletException {
    private static final long serialVersionUID = 1L;
   
    public ExceptionServlet()
    {
        super();
    }
 
    public ExceptionServlet(String message, Throwable rootCause)
    {
        super(message, rootCause);
    }
 
    public ExceptionServlet(String message)
    {
        super(message);
    }
 
    public ExceptionServlet(Throwable rootCause)
    {
        super(rootCause);
    }
 
}