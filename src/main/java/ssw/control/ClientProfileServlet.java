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
import ssw.model.Recipe;
import ssw.persistance.ClientDB;
import ssw.persistance.RecipeDB;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
@WebServlet(name = "ClientProfileServlet", urlPatterns = {"/profile_view"})
public class ClientProfileServlet extends HttpServlet {

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
        if ((request.getParameter("self") != null) && ("true".equals(request.getParameter("self")))) {
            // Forward request to the logged client profile
            HttpSession session = request.getSession();
            Client profileVisited = (Client)session.getAttribute("client");
            request.setAttribute("profileVisited", profileVisited);
            Recipe[] clientRecipes = RecipeDB.getRecipesOfClient(profileVisited.getId());
            request.setAttribute("clientRecipes", clientRecipes);
            url = "/client_profile.jsp";  
        } else {
            Client profileVisited = ClientDB.getClient(request.getParameter("profile"));
            request.setAttribute("profileVisited", profileVisited);
            Recipe[] clientRecipes = RecipeDB.getRecipesOfClient(profileVisited.getId());
            request.setAttribute("clientRecipes", clientRecipes);
            url = "/client_profile.jsp";
        }
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
