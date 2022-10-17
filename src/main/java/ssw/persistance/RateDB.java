package ssw.persistance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
public class RateDB {
    
    /**
     * Inserts a new rate
     * @param clientId 
     * @param recipeId
     * @param score 
     */
    public static void rateRecipe(String clientId, int recipeId, int score) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        String query = String.format("INSERT INTO Rate (score,rater,recipe) VALUES (%d, '%s', %d)", score, clientId, recipeId);
        
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
     * @param clientId
     * @param recipeId
     * @return -1,0,1 if teh recipe is rated as its value, -2 if the recipe isnt rates and -5 if it failed
     */
    public static int getRate (String clientId, int recipeId){
    
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            ResultSet rs = null;
            String consult =  String.format("SELECT score FROM Rate WHERE rater = '%s'  AND recipe = %d ", clientId, recipeId);
            int number;
            try{
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(consult);

                // If It exists returns 1, 0, -1 as possible values
                if(rs.next()){
                    number = rs.getInt("score");
                }
                // If it doesn't exists returns -2
                else{ 
                    number = -2;
                }
                
                rs.close();
                pool.freeConnection(connection);
                return number;
                
            }
            
            catch (SQLException e){
                e.printStackTrace();
                return -5;
            }
        }
    
    /**
     * Updates a rate
     * @param clientId
     * @param recipeId
     * @param score 
     */
    public static void updateRate(String clientId, int recipeId, int score){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        String query = String.format(
                "UPDATE Rate SET score = %d WHERE rater = '%s' AND recipe = %d", score, clientId, recipeId);
        
        try{
            statement = connection.createStatement();
            statement.executeUpdate(query);
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
                
    }
    
    
    
}
