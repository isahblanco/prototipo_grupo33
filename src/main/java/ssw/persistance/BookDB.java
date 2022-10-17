package ssw.persistance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
public class BookDB {
    
    /**
     * Removes a relation in the database between a client and a saved recipe
     * @param clientId Identificator of the client
     * @param recipeId Recipe that won`t be saved by the client anymore.
     */
    public static void removeSavedRecipe(String clientId, int recipeId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        String query = String.format("DELETE FROM Book WHERE client = '%s' AND recipe = %d", clientId, recipeId);
        
        try{
            statement = connection.createStatement();
            statement.executeUpdate(query);
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes a recipe from the database created by the user 
     * @param clientId id of the user who delete their own recipe
     * @param recipeId id of the recipe to be deleted
     */
    public static void deleteOwnRecipe(String clientId, int recipeId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        String query1 = String.format("DELETE FROM Book WHERE recipe = %d", recipeId);
        String query2 = String.format("DELETE FROM Rate WHERE recipe = %d", recipeId);
        String query3 = String.format("DELETE FROM Recipe WHERE creator = '%s' AND id = %d", clientId, recipeId);
        try{
            statement = connection.createStatement();
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Allows the current user to save an specific recipe in their book, inserts
     * into the database a relation between the id of the saved recipe and the
     * id of the user who saves it
     * @param clientId id of the user who saves the recipe
     * @param recipeId id of the recipe saved
     */
    public static void saveRecipe(String clientId, int recipeId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        String query = String.format("INSERT INTO BOOK (client,recipe) VALUES ('%s', %d)", clientId, recipeId);
        
        try{
            statement = connection.createStatement();
            statement.executeUpdate(query);
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks whether a recipe is saved or not by a given user
     * @param clientId id of the user 
     * @param recipeId id of the recipe
     * @return True if saved, false if not
     */
    public static boolean isSaved (String clientId, int recipeId){
    
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            ResultSet rs = null;
            String consult =  String.format("SELECT * FROM Book WHERE book.RECIPE= %d AND book.CLIENT = '%s'", recipeId, clientId);

            boolean isInBook = false;
            try{
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(consult);

                if(rs.next()){
                    isInBook = true;
                }
                
                rs.close();
                pool.freeConnection(connection);
                
                return isInBook;
            }
            
            catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
    
}
