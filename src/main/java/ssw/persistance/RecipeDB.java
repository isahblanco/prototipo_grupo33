package ssw.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import ssw.model.Client;
import ssw.model.Recipe;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
public class RecipeDB {
    
    /**
     * Creates a recipe from the ResultSet passed as an argument
     * @param rs ResultSet which contains the information to create a recipe object
     * @return The recipe object created
     * @throws SQLException 
     */
    private static Recipe createRecipe(ResultSet rs) throws SQLException, IOException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt("id"));
        recipe.setName(rs.getString("rname"));
        recipe.setPreparationTime(rs.getInt("PreparationTime"));
        recipe.setSteps(rs.getString("steps"));
        recipe.setIngredients(rs.getString("ingredients"));
        Blob multimedia = rs.getBlob("multimediaFiles");
        recipe.setViews(rs.getInt("views"));                    
        recipe.setScore(rs.getInt("score"));
        recipe.setVisibility(rs.getBoolean("visibility"));
        Client creator = ClientDB.getClient(rs.getString("creator"));
        recipe.setCreator(creator);
        recipe.setTags(rs.getString("tags").replace("++", " "));
        return recipe;
    }
    
    /**
    * Update the databse with the new recipe information
    * @param recipe Recipe to be added
    */
    public static int insert(Recipe recipe, InputStream multimediaFile) throws IOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        //Statement statement;
        PreparedStatement ps = null;
        int id = 0;
        String query = 
                "INSERT INTO Recipe(rname, preparationTime, steps, ingredients, views, score, visibility, creator, tags, multimediaFiles) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try{
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, recipe.getName());
            ps.setInt(2, recipe.getPreparationTime());
            ps.setString(3, recipe.getSteps());
            ps.setString(4, recipe.getIngredients());
            ps.setInt(5, recipe.getViews());
            ps.setInt(6, recipe.getScore());
            ps.setBoolean(7, recipe.isVisibility());
            ps.setString(8, recipe.getCreator().getId());
            ps.setString(9,recipe.getTags());
            ps.setBlob(10, multimediaFile);
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();      
                if (rs.next()){
                    id = rs.getInt(1);
                }
            ps.close();
            pool.freeConnection(connection);
            return id;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
                
    }
    
    /**
     * Searchs in the database for the recipes with the best (or worst) characteristic
     * declared as 
     * @param category
     * @param length
     * @param order
     * @return The array of recipes
     */
    public static Recipe[] rankingMaker (String category, int length, String order) throws IOException{
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String query = String.format("SELECT * FROM Recipe WHERE visibility=true ORDER BY %s %s FETCH FIRST %d ROWS ONLY", category, order, length);
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            Recipe[] recipes = new Recipe[length];
            Recipe recipe;

            int index = 0;
            while(rs.next()){
                recipe = createRecipe(rs);
                recipes[index] = recipe;
                index++;
            }

            rs.close();
            pool.freeConnection(connection);
            return recipes;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
     
    /**
     * This method returns a recipe based on the ID looking for it into the database
     * @param recipeID The id of the recipe is being searched in the database
     * @return The recipe object
     */
    public static Recipe getRecipe (int recipeID) throws IOException{
    
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            String query= String.format("SELECT * FROM Recipe WHERE id = %d", recipeID);
        
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                Recipe recipe = null;
                if(rs.next()){
                    recipe = createRecipe(rs);
                }
                rs.close();
                pool.freeConnection(connection);
                
                return recipe;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
    

    /**
     * 
     * @param recipeID
     * @return the score of a recipe
     */
    public static int getScore (int recipeID){
    
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            String query= String.format("SELECT score FROM Recipe WHERE id = %d", recipeID);
            int score = -2;
            
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                if(rs.next()){
                    score = rs.getInt("score");
                }
                rs.close();
                pool.freeConnection(connection);
                
                return score;
            }catch (SQLException e){
                e.printStackTrace();
                return -5;
            }
        }
    
    /**
     * Updates the total score of a recipe
     * @param recipeID
     * @param score 
     */
    public static void updateScore(int recipeID, int score){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        String query = String.format(
                "UPDATE Recipe SET score = %d WHERE id = %d", score, recipeID );
        
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
     * @return all the recipes from the client 
     * @throws IOException 
     */
    public static Recipe[] getRecipesOfClient(String clientId) throws IOException{
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String query= String.format("SELECT * FROM Recipe WHERE creator = '%s' and visibility = true", clientId);
        
        try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                ArrayList<Recipe> recipes = new ArrayList<Recipe>();
                while(rs.next()){
                    recipes.add(createRecipe(rs));
                }
                rs.close();
                pool.freeConnection(connection);
                
                Recipe[] resultRecipes = (Recipe[]) recipes.toArray(new Recipe[recipes.size()]);
                return resultRecipes;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
    }
        
    /**
     * @param tags
     * @param numberRows
     * @param excludeIndex
     * @return and array of recipes with numberRows elements with a random order, 
     * excluding the number selected and based in the tags introduced by tags
     * @throws IOException 
     */
    public static Recipe[] getRecipesByTags(String tags, int numberRows, int excludeIndex) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        // If there is only one tag, set the second tag to a String that will not match any recipe tag in the database
        String[] tagsSeparated = tags.split(" ");
        String tag1 = tagsSeparated[0], tag2;
        if (tagsSeparated.length == 1) tag2 = "NO_TAGS";
        else tag2 = tagsSeparated[1];
        
        // Select 20 recipes based on the tags selected ordered by score
        String query = String.format("SELECT * "
                + "FROM RECIPE "
                + "WHERE score >= 0 AND visibility = true AND lower(tags) LIKE lower(%s) AND id <> %d"
                + "OR score >= 0 AND visibility = true AND lower(tags) LIKE lower(%s) AND id <> %d"
                + "ORDER BY Score DESC "
                + "FETCH FIRST %d ROWS ONLY", "'%"+tag1+"%'", excludeIndex, "'%"+tag2+"%'", excludeIndex, numberRows);
        
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            Recipe recipe;
            while (rs.next()) {
                recipe = createRecipe(rs);
                recipes.add(recipe);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        Collections.shuffle(recipes);
        return (Recipe[]) recipes.toArray(new Recipe[recipes.size()]);
    }
    
    /**
     * Searchs in the database for the recipes which contains within its name or tags the input 
     * from the search-bar
     * @param search
     * @return ArrayList of recipes
     */
    public static ArrayList<Recipe> getSearchRecipes(String search) throws IOException{
    
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            ResultSet rs = null;
            
            String query= String.format("SELECT * FROM Recipe WHERE visibility=true AND lower(tags) LIKE lower(%s) OR visibility = true AND lower(rname) LIKE lower(%s)","'%"+search+"%'","'%"+search+"%'");
            try{
                         
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(query);
                
                ArrayList<Recipe> recipes = new ArrayList();
                Recipe recipe;
                
                while(rs.next()){
                    recipe = createRecipe(rs);
                    recipes.add(recipe);
                    }
                rs.close();
                pool.freeConnection(connection);
                
                return recipes;
            }
            
            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
    
    /**
     * Returns the recipes saved by the client passed as a parameter
     * @param client Client whose saved recipes are being searched
     * @return ArrayList of recipes
     */
    public static ArrayList<Recipe> getBookRecipes (Client client) throws IOException{
    
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            ResultSet rs = null;
            String consult =  String.format("SELECT * FROM Recipe, Book WHERE recipe.visibility=true AND book.RECIPE= recipe.ID AND book.CLIENT = '%s' AND recipe.creator <> '%s' ", client.getId(), client.getId());

            try{
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(consult);
                
                ArrayList<Recipe> recipes;
                recipes = new ArrayList();
                Recipe recipe;

                while(rs.next()){
                    recipe = createRecipe(rs);
                    recipes.add(recipe);
                }
                
                rs.close();
                pool.freeConnection(connection);
                
                return recipes;
            }
            
            catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
    
    /**
     * Returns the recipes created by the client passed as a parameter
     * @param client Client whose created recipes are being searched
     * @return ArrayList of recipes
     */
    public static ArrayList<Recipe> getMyRecipes (Client client) throws IOException{
    
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String consult =  String.format("SELECT * FROM Recipe where CREATOR = '%s' ", client.getId());

            try{
                Statement statement = connection.createStatement();
                rs = statement.executeQuery(consult);
                
                ArrayList<Recipe> recipes;
                recipes = new ArrayList();
                Recipe recipe;

                while(rs.next()){
                    recipe = createRecipe(rs);
                    recipes.add(recipe);
                }
                rs.close();
                pool.freeConnection(connection);
                return recipes;
            }
            catch (SQLException e){
                e.printStackTrace();
                return null;
            }   
    }
    
    /**
    * Update the databse with the new recipe information of an existing recipe
    * @param recipe Recipe to be modified
    */
    public static void modify(Recipe recipe, InputStream... multimediaFile) throws IOException{
        boolean multimediaSent = false;
        if (multimediaFile.length > 0) multimediaSent = true;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE Recipe SET rname = ?, preparationTime = ?, steps = ?, ingredients = ?, visibility = ?, tags = ? WHERE id = ?";
        if (multimediaSent) query = "UPDATE Recipe SET rname = ?, preparationTime = ?, steps = ?, ingredients = ?, visibility = ?, tags = ?, multimediaFiles = ? WHERE id = ?";
        
                
        try{
            ps = connection.prepareStatement(query);
            
            ps.setString(1, recipe.getName());
            ps.setInt(2, recipe.getPreparationTime());
            ps.setString(3, recipe.getSteps());
            ps.setString(4, recipe.getIngredients());
            ps.setBoolean(5, recipe.isVisibility());
            ps.setString(6,recipe.getTags());
            
            if (multimediaSent) {
                ps.setBlob(7, multimediaFile[0]);
                ps.setInt(8, recipe.getId());
            } else {
                ps.setInt(7, recipe.getId());
            }

            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
    /**
    * Update the databse with the new recipe information (score) of an existing recipe
    * @param recipe Recipe to be modified
    */
    public static void modifyscore(Recipe recipe) throws IOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        //Statement statement;
        String query = 
                "UPDATE Recipe SET rname = ?, preparationTime = ?, steps = ?, ingredients = ?, visibility = ?, tags = ? WHERE id = ?";
        try{
            ps = connection.prepareStatement(query);
            
            ps.setString(1, recipe.getName());
            ps.setInt(2, recipe.getPreparationTime());
            ps.setString(3, recipe.getSteps());
            ps.setString(4, recipe.getIngredients());
            ps.setBoolean(5, recipe.isVisibility());
            ps.setString(6,recipe.getTags());
            ps.setInt(8, recipe.getId());
            
            ps.executeUpdate(query);
            ps.close();
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
                
    }
    
    /**
    * Update the databse with the new recipe information of an existing recipe
    * @param recipe Recipe to be modified
    */
    public static void addView(Recipe recipe){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        String query = String.format(
                "UPDATE Recipe SET  views = %d WHERE id = %d",
                recipe.getViews(),
                recipe.getId()
        );
        
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
    * Recovers from the database the multimedia file of an specific recipe given
    * its id as a parameter
    * @param idRecipe id of the recipe from which get the multimedia file
    * @param res output stream of the file
    */
    public static void getMultimedia(int idRecipe, OutputStream res) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ps = connection.prepareStatement("SELECT multimediafiles FROM recipe WHERE id=?");

            ps.setInt(1, idRecipe);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                Blob blob = result.getBlob("multimediafiles");
                if (!result.wasNull() && blob.length() > 1) {
                    InputStream imagen = blob.getBinaryStream();
                    byte[] buffer = new byte[1000];
                    int len = imagen.read(buffer);
                    while (len != -1) {
                        res.write(buffer, 0, len);
                        len = imagen.read(buffer);
                    }
                    imagen.close();
                }
            }
            pool.freeConnection(connection);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
