package ssw.control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ssw.model.Client;
import ssw.persistance.ClientDB;
import ssw.persistance.RateDB;
import ssw.persistance.RecipeDB;

/**
 *
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
@WebServlet(name = "RateRecipeServlet", urlPatterns = {"/rate_recipe"})
public class RateRecipeServlet extends HttpServlet {

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
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute("client");
        
        int recipeId = Integer.parseInt(request.getParameter("recipe"));
        String clientId = request.getParameter("client");
        int rate = Integer.parseInt(request.getParameter("score"));
        int oldScore = RecipeDB.getScore(recipeId);
        
        int oldRate = RateDB.getRate(clientId,recipeId);
        if (oldRate >= -1) {
            RateDB.updateRate(clientId, recipeId, rate);
            RecipeDB.updateScore(recipeId,oldScore - oldRate + rate );
            
        } else {
            // Add experience to the user for rating a recipe
            boolean check = client.addExp(5);
            ClientDB.modifyXP(client);
            if(check) ClientDB.modifyLevel(client);
                
            RateDB.rateRecipe(clientId, recipeId, rate);
            RecipeDB.updateScore(recipeId,oldScore + rate );
        }
        
        out.close();   
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
