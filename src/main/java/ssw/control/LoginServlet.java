package ssw.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ssw.model.Client;
import ssw.persistance.ClientDB;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url;
        HttpSession session = request.getSession();
        if ((request.getParameter("redir")!= null) && (request.getParameter("redir").equals("home"))) {
            url = "/ranking";
            session.setAttribute("url", url);
        }
        else if ((request.getParameter("redir")!= null) && (request.getParameter("redir").equals("new"))) {
            url = "/newRecipe";
            session.setAttribute("url", url);
        }
        else if ((request.getParameter("redir")!= null) && (request.getParameter("redir").equals("book"))) {
            url = "/book";
            session.setAttribute("url", url);
        }
        
        
        // If the user cames from the navigation menu there is no error
        if (request.getParameter("menu").equals("1")) {
            request.setAttribute("error", false);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            // Get the information from the form
            String id = request.getParameter("username");
            String password = request.getParameter("password");

            // If login succesful, Forward to the ranking page
            Client client = ClientDB.checkClient(id, password);
            if ( client != null) {
                url = (String)session.getAttribute("url");
                session.removeAttribute("url");
                session.setAttribute("client", client);
                
                // Add experience to the user for loging in the page
                boolean check = client.addExp(5);
                ClientDB.modifyXP(client);
                if(check) ClientDB.modifyLevel(client);
            } 
            // If not, try again
            else {
                url = "/login.jsp";
                request.setAttribute("error", true);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response); 
        }
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 
            processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
