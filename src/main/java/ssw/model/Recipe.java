package ssw.model;

import java.io.Serializable;

/**
 * @author ignaren
 * @author dancruz
 * @author mariher
 */
public class Recipe implements Serializable{
    private int id;
    private String name;
    private int preparationTime;
    private String steps; 
    private String ingredients;
    private int views;
    private int score;
    private boolean visibility;
    private Client creator;
    private String tags;
    
    public Recipe()
    {
        this.id = -1;
        this.name = "";
        this.preparationTime = 0;
        this.steps = "";
        this.ingredients = "";
        this.views = 0;
        this.score = 0;
        this.visibility = false;
        this.creator = null;
        this.tags = "";
        
    }
    
    public Recipe(String name, int preparationTime, String steps, 
            String ingredients, boolean visibility, Client creator, String tags)
    {
        this.name = name;
        this.preparationTime = preparationTime;
        this.steps = steps;
        this.ingredients = ingredients;
        this.views = 0;
        this.score = 0;
        this.visibility = visibility;
        this.creator = creator;
        this.tags = tags;
    }

    
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getSteps() {
        return steps;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getViews() {
        return views;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void addView() {
        this.views += 1;
    }
    
    public void setViews(int views) {
        this.views = views;
    }
    
    public void addScore(int score) {
        this.score += score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public Client getCreator() {
        return creator;
    }

    public void setCreator(Client creator) {
        this.creator = creator;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
