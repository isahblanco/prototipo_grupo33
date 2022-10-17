package ssw.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import ssw.model.Client;
import ssw.model.Recipe;
import ssw.persistance.ClientDB;
import ssw.persistance.RecipeDB;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
@WebServlet(name = "NewRecipeServlet", urlPatterns = {"/newRecipe"})
@MultipartConfig
public class NewRecipeServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute("client");
        
        String url;
        // When "create" parameter is especified in the request, the recipe is saved in the database 
        if ((request.getParameter("create") != null) && (request.getParameter("create").equals("yes"))) {
            int id = createNewRecipe(request);
            request.setAttribute("id", id);
            url = "/recipeInfo";
            
            // Add experience to the user for creating a recipe
            boolean check = client.addExp(15);
            ClientDB.modifyXP(client);
            if(check) ClientDB.modifyLevel(client);
        }
        // If not, just show the new recipe form
        else{
            url = "/new_recipe.jsp";
        }
        
        // forward request and response objects to JSP page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }
    
    /**
     * Create a new recipe with the date from the request
     * @param request
     * @return True if was created, false if there was an error
     */
    private int createNewRecipe(HttpServletRequest request) throws IOException, ServletException {
        int id = 0;
        String name = request.getParameter("name");
        int preparationTime = (request.getParameter("time").equals("")) ? 0 : Integer.parseInt(request.getParameter("time"));
        String visibility = request.getParameter("visibility");
        String steps = request.getParameter("process");
        String ingredients = request.getParameter("ingredients");
        Part multimedia = request.getPart("multimedia");
        String tags = request.getParameter("selected-tags");
        Client creator = (Client) request.getSession().getAttribute("client");
        
        
        // Parsing the tags
        tags = tags.substring(1);
        tags = tags.toLowerCase();
        tags = tags.replace(",", "++");
        
        // Update the database with the new recipe
        Recipe newRecipe = new Recipe(name, preparationTime, steps, ingredients, visibility.equals("active"), creator, tags);
        newRecipe.setVisibility(visibility.equals("active"));
        id = RecipeDB.insert(newRecipe, multimedia.getInputStream());
        return id;
        
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
