package ssw.persistance;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ssw.model.Client;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
public class ClientDB {
    
    /**
     * Creates a client from the ResultSet passed as an argument
     * @param rs ResultSet which contains the information to create a client object
     * @return The client object created
     * @throws SQLException 
     */
    private static Client createClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getString("id"));
        client.setName(rs.getString("cname"));
        client.setSurnames(rs.getString("surname"));
        client.setEmail(rs.getString("email"));
        client.setExp(rs.getInt("exp"));
        client.setLevel(rs.getInt("clevel"));
        client.setBiography(rs.getString("biography"));
        client.setPassword(rs.getString("password"));
        return client;
    }
    
    /**
    * Update the databse with the new client informatioin
    * @param client Client to be registered
    * @param multimediaFile Image to introduce into the client
    */
    public static void insertClient(Client client, InputStream multimediaFile){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Client (password,id,cname,surname,email,exp,clevel,profilePic, biography) VALUES ( ?,?,?,?,?,?,?,?,?)";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, client.getPassword());
            ps.setString(2, client.getId());
            ps.setString(3, client.getName());
            ps.setString(4, client.getSurnames());
            ps.setString(5, client.getEmail());
            ps.setInt(6,client.getExp());
            ps.setInt(7,client.getLevel());
            ps.setBlob(8, multimediaFile);
            ps.setString(9, client.getBiography());

            ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
        }
        
        catch (SQLException e) {
            e.printStackTrace();
        }
                
    }
    
    
    /**
     * Comprobates wheter the client exists in the database or not
     * @param id Identificator of the client
     * @param password Passowrd of the client
     * @return Client object corresponding to the credentials
     */
    public static Client checkClient(String id, String password){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String query= "SELECT * FROM Client WHERE id = '%s' and password = '%s'";
        query = String.format(query, id, password);
        
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Client client = null;
            
            if(rs.next()){
                client = createClient(rs);
            }
            
            rs.close();
            pool.freeConnection(connection);
            return client;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
                
    }
    
    /**
     * Edit the information of the client in the database
     * @param client Client whose modification is about to be edited
     * @param multimediaFile
     */
    public static void modifyClient(Client client, InputStream... multimediaFile){
        boolean multimediaSent = false;
        if (multimediaFile.length > 0) multimediaSent = true;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query= "UPDATE Client SET cname = ? ,surname = ? ,email = ?,biography = ? WHERE id = ?";
        if (multimediaSent) {query= "UPDATE Client SET cname = ? ,surname = ? ,email = ?,biography = ?, profilePic = ? WHERE id = ?";}
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurnames());
            ps.setString(3, client.getEmail());
            ps.setString(4,client.getBiography());
            if (multimediaSent) {
                ps.setBlob(5, multimediaFile[0]);
                ps.setString(6, client.getId());
            } else {
                ps.setString(5, client.getId());
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
     * Gets a client object from the database
     * @param id Identificator of the client
     * @return Client object required
     */
    public static Client getClient(String id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement;
        ResultSet rs;
        String query = String.format("SELECT * FROM Client WHERE id = '%s'", id);
        
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            Client client = null;
            
            if(rs.next()){
                client = createClient(rs);
            }
            rs.close();
            pool.freeConnection(connection);
            return client;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
                
    }

    /**
     * Sets the experience of the user passed as an argument. 
     * The new value is already stored in the user object.
     * @param client 
     */
    public static void modifyXP(Client client) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String query= "UPDATE Client SET exp = %d WHERE id = '%s'";
        query = String.format(query, 
                client.getExp(),
                client.getId());    
        
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sets the level of the user passed as an argument. 
     * The new value is already stored in the user object.
     * @param client 
     */
    public static void modifyLevel(Client client) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String query= "UPDATE Client SET clevel = %d  WHERE id = '%s'";
        query = String.format(query, 
                client.getLevel(),
                client.getId());    
        
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            pool.freeConnection(connection);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Obtain the client pic of his profile.
     * @param idClient Id of the client whose image is being asked for
     * @param res Multimedia pic received from database
     */
    public static void getMultimedia(String idClient, OutputStream res) {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ps = connection.prepareStatement("SELECT profilePic FROM Client WHERE id=?");

            ps.setString(1, idClient);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                Blob blob = result.getBlob("profilePic");
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
