package ssw.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Pattern;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
        
        // If you came from the navigation menu
        if (request.getParameter("new").equals("1")) {
            request.setAttribute("error", "");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);
        } 
        // If you came from a try from the register page
        else {
            String id = request.getParameter("username");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            
            // Check if there are any errors 
            String error = null;
            if(ClientDB.getClient(id) != null) {
                error = "This username already exist";
            }else if (validatePassword(password)){
                error = "Password must have a capital letter, a non alphanumerical symbol, a digit and a letter.";
            } else if (!password.equals(password2)) {
                error = "Passwords do not match";
            }
            
            // If there are errors during the process show the error message and try again
            // If not, create the user and log him in for this session
            if (error!=null) {
                request.setAttribute("error", error);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "");
                Client newClient = new Client(id, password);
                String path = getServletContext().getRealPath("/media/client");
                File defaultPic = new File(path + "/default.png");
                FileInputStream fis = new FileInputStream(defaultPic);
                ClientDB.insertClient(newClient, fis);
                HttpSession session = request.getSession();
                session.setAttribute("client", newClient);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/client_profile_edit.jsp");
                dispatcher.forward(request, response);
            }
        }
        
    }
    
    /**
     * TODO: add more (number of characters, characters...
     * Validates if the user password meet the needed criteria
     * It checks if the password have more than 8 char and less than 20
     * @param password
     * @return True if its correct, false if not
     */
    private boolean validatePassword(String password) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if ((password.length() < 8) || (password.length() > 20)) return true;
        else if (!pattern.matcher(password).matches()) return true;
        else return false;
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
