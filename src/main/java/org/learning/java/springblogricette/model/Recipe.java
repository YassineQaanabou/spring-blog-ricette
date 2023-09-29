package org.learning.java.springblogricette.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String ingredients;
    private int duration;
    private int portions;
    private String preparation;
    private String img;

    public Recipe(Integer id, String title, String ingredients, int duration, int portions, String preparation, String img) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.duration = duration;
        this.portions = portions;
        this.preparation = preparation;
        this.img = img;
    }

    public Recipe() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getDuration() {
        return duration;
    }

    public int getPortions() {
        return portions;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getImg() {
        return img;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
