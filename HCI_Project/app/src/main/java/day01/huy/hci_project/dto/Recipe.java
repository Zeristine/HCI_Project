package day01.huy.hci_project.dto;

import java.util.Date;
import java.util.List;

public class Recipe {
    private long id;
    private String title;
    private String author;
    private String content;
    private String imageLink;
    private float rate;
    private List<String> ingredients;

    public Recipe(long id, String title, String author, String content, String imageLink, float rate, List<String> ingredients) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.imageLink = imageLink;
        this.rate = rate;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
