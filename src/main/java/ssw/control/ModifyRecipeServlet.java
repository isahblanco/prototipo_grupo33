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

import ssw.model.Recipe;
import ssw.persistance.RecipeDB;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
@WebServlet(name = "ModifyRecipeServlet", urlPatterns = {"/modify_recipe"})
@MultipartConfig
public class ModifyRecipeServlet extends HttpServlet {

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
        
        // Obtain the recipe from he database 
        String url = "/book";
        Recipe recipe = null;
        // Load the recipe which is about to be modified from the database
        if (request.getParameter("id") != null){
          int id = Integer.parseInt(request.getParameter("id"));
          recipe = RecipeDB.getRecipe(id);  
          request.setAttribute("recipe", recipe);
          url = "/modify_recipe.jsp";
        }
        // IF the Servlet is called with "create" parameter then the recipe will be modified in the database
        if ((request.getParameter("create") != null) && (request.getParameter("create").equals("yes"))) {
            modifyRecipe(request,recipe);
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id", id);
            url = "/recipeInfo";
        }

        // forward request and response objects to JSP page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }
    
    /**
     * Modify an existance recipe with the date from the request
     * @param request
     * @param recipe Recipe object to be modified
     * @return True if was created, false if there was an error
     */
    private boolean modifyRecipe(HttpServletRequest request, Recipe recipe) throws IOException, ServletException {
        
        
        String name = request.getParameter("name");
        int preparationTime = (request.getParameter("time").equals("")) ? 0 : Integer.parseInt(request.getParameter("time"));
        String visibility = request.getParameter("visibility");
        String steps = request.getParameter("process");
        String ingredients = request.getParameter("ingredients");
        Part multimedia = request.getPart("multimedia");
        String tags = request.getParameter("selected-tags");
        
        //Server-side data validation.
        if (tags.equals("")) return false;
        if (!name.matches("[a-zA-Z ]+")) return false;
        
        // Parsing the tags
        tags = tags.substring(1);
        tags = tags.toLowerCase();
        tags = tags.replace(",", "++");
        
        // Update the database with the new recipe
        if (!name.equals("")){ recipe.setName(name);}
        if (preparationTime!=0){ recipe.setPreparationTime(preparationTime);}
        recipe.setVisibility(visibility.equals("active"));
        if (!steps.equals("")){ recipe.setSteps(steps);}
        if (!ingredients.equals("")){ recipe.setIngredients(ingredients);}
        if (!tags.equals("")){recipe.setTags(tags);} 
        if (multimedia.getSize() > 0) RecipeDB.modify(recipe, multimedia.getInputStream());
        else RecipeDB.modify(recipe);
        
        return true;
        
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
