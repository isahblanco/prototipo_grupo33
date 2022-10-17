package ssw.scriptsDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility Class
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
public class ImageLoader {
    
    private static final String CURRENT_WORKING_DIRECTORY = System.getProperty("user.dir");
    
    private static File[] getRecipeImagesFromStorage() {
        String recipeMediaURL = CURRENT_WORKING_DIRECTORY + "/src/main/webapp/media/recipesSimple";
        File recipeDir = new File(recipeMediaURL);
        File[] recipeFiles = recipeDir.listFiles();
        Arrays.sort(recipeFiles);
        return recipeFiles;
    }
    
    private static void loadRecipeImages(Connection con) {
        String query = "UPDATE Recipe SET multimediaFiles = ? WHERE id = ?";
        
        int id = 0;
        for (final File recipe: getRecipeImagesFromStorage()){
            try {
                FileInputStream fin = new FileInputStream(recipe);
                PreparedStatement ps = con.prepareStatement(query);
                
                ps.setBinaryStream(1, fin, (int) recipe.length());
                ps.setInt(2, id);
                ps.executeUpdate();
                
                
                id++;
            } catch (FileNotFoundException | SQLException ex) {
                Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void loadClientImages(Connection con) {
        
        String query = "UPDATE Client SET profilePic = ? WHERE id = ?";
        File defaultPic = new File(CURRENT_WORKING_DIRECTORY + "/src/main/webapp/media/client/default.png");
        File chefPic = new File(CURRENT_WORKING_DIRECTORY + "/src/main/webapp/media/client/chef.webp");
        File userPic = new File(CURRENT_WORKING_DIRECTORY + "/src/main/webapp/media/client/user.jpg");
        
        String[] ids = new String[]{"pedri", "itoo", "alvarito", "FMHulin", "dani", "user", "chef", "empty"};
        for (int i=0; i<8; i++){
            try {
                File pic;
                if (i==5) pic = userPic;
                else if (i==6) pic = chefPic;
                else pic = defaultPic;
                
                FileInputStream fin = new FileInputStream(pic);
                PreparedStatement ps = con.prepareStatement(query);
                
                ps.setBinaryStream(1, fin, (int) pic.length());
                ps.setString(2, ids[i]);
                ps.executeUpdate();
                
            } catch (FileNotFoundException | SQLException ex) {
                Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/fantasty", "fantasty", "1234");
        loadRecipeImages(con);
        loadClientImages(con);
        con.close();
        
    }
}
