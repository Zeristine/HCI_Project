package day01.huy.hci_project.dto;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.List;

public class Recipe {
    private long id;
    private String title;
    private String author;
    private String content;
    private String imageLink;
    private Bitmap image;
    private double rate;
    private String description;
    private List<String> ingredients;

    public Recipe() {
    }

    public Recipe(long id, String title, String author, String content, String imageLink, Bitmap image, double rate, String description, List<String> ingredients) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.imageLink = imageLink;
        this.image = image;
        this.rate = rate;
        this.description = description;
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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
