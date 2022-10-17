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
@WebServlet(name = "RecipeInfoServlet", urlPatterns = {"/recipeInfo"})
public class RecipeInfoServlet extends HttpServlet {

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
        
        // Obtain the recipe from the database 
        int id = -1;
        if (request.getParameter("id")!=null ){
            id = Integer.parseInt(request.getParameter("id"));
        } else if (request.getAttribute("id")!=null) {
            id = (int) request.getAttribute("id");
        }
        
        Recipe recipe = RecipeDB.getRecipe(id);
        
        // Obtain 4 random suggestions
        Recipe[] recipesByTags = RecipeDB.getRecipesByTags(recipe.getTags(), 20, recipe.getId());
        
        // Save it so the view can see it
        request.setAttribute("recipe", recipe);
        request.setAttribute("recommendations", recipesByTags);
               
        // Before redirect lets add 1 view if its not you
        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute("client");
     
        if ( client != null){
            if( !recipe.getCreator().getId().equals(client.getId()) ){
                recipe.addView();
                RecipeDB.addView(recipe);
                
                // Add experience to the user for viewing the recipe
                boolean check = client.addExp(1);
                ClientDB.modifyXP(client);
                if(check) ClientDB.modifyLevel(client);
                
                // Add experience to the user for having your recipe viewed
                check = recipe.getCreator().addExp(1);
                ClientDB.modifyXP(recipe.getCreator());
                if(check) ClientDB.modifyLevel(recipe.getCreator());
            }
        }
            
        
        // forward request and response objects to JSP page
        String url = "/recipe_info.jsp";
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
