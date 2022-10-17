package ssw.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ssw.model.Client;
import ssw.persistance.ClientDB;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
@WebServlet(name = "ClientEditServlet", urlPatterns = {"/edit_profile"})
@MultipartConfig
public class ClientEditServlet extends HttpServlet {
    
           
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
        Client client = (Client) request.getSession().getAttribute("client");
        
        // Get the information from the form
        String name = request.getParameter("user-name");
        String surname = request.getParameter("user-surname");
        String id = request.getParameter("id");
        String email = request.getParameter("user-correo");
        String biography = request.getParameter("user-biography");
        Part multimedia = request.getPart("user-new-pic");
        
        // Update the client information
        client.setName(name);
        client.setSurnames(surname);
        client.setId(id);
        client.setEmail(email);
        client.setBiography(biography);
        
        // Update the DB with the new information of the client
        if (multimedia.getSize() > 0){
            ClientDB.modifyClient(client, multimedia.getInputStream());
        }
        else  ClientDB.modifyClient(client);

        
        // Redirection to the client profile
        String url = "/profile_view?self=true";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response); 
        
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
