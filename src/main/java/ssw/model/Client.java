package ssw.model;

import java.io.Serializable;
import static java.lang.Math.log;


/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
public class Client implements Serializable{
    private final int BASEXP = 20;
    private String id;
    private String nickname;
    private String name;
    private String surnames;
    private String email;
    private int exp;
    private int level;
    private String biography;
    private String password;
    private int neededXP;
    
    public Client() 
    {
        this.id = "";
        this.nickname = "";
        this.name = "";
        this.surnames = "";
        this.email = "";
        this.exp = 0;
        this.level = 1;
        this.biography = "";
        this.password = "";
        neededXP = BASEXP;
    }
    
    public Client(String id, String password)
    {
        this.id = id;
        this.nickname = "@" + id;
        this.name = "";
        this.surnames= "";
        this.email = "";
        this.exp = 0;
        this.level = 0;
        this.biography = "";
        this.password = password;
        neededXP = BASEXP;
    }
    
    @Override
    public String toString(){
        return String.format("Id: %s, Nickname: %s, Name: %s, Surnames: %s, Email: %s, Exp: %d, Level: %d, Password: %s", 
                this.id, this.nickname, this.name, this.surnames, this.email, this.exp, this.level, this.password);
    }

    public String getNickname() {
        return nickname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getEmail() {
        return email;
    }

    public int getExp() {
        return exp;
    }

    public int getNeededXP() {
        return neededXP;
    }

    /** 
     * @return the % of XP that you have in the progression for your next lvl
     */
    public int getCurrentXP() {
        double currxp;
        currxp = ((double) exp/neededXP) * 100;
        return (int)currxp;
    }
    
    public int getLevel() {
        return level;
    }

    public EnumTitles getTitle() {
        return EnumTitles.values()[level];
    }

    public String getBiography() {
        return biography;
    }

    public void setId(String id) {
        this.id = id;
        this.nickname = "@" + id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(int level) {
        this.level = level;
        for(int i=1;i<=level+1;i++){
             neededXP += 20 * log(i); //20-50-97-157-226...
        }
    }
    
    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    /**
     * Method to add experience to the user.
     * If actual xp is bigger than needed xp u gain a level, 
     * keep the exceded xp and recalculate the needed xp 
     * @return true if lvl up, flase if not  
     * @param exp 
     */
    public boolean addExp(int exp) {
        this.exp += exp;
        return calculateLevel();
    }
    
    private boolean calculateLevel() {
        if(exp >= neededXP){
            exp = (int) (exp%neededXP); 
            level += 1;  
            neededXP += 20 * log(level+1); //20-50-97-157-226...
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
