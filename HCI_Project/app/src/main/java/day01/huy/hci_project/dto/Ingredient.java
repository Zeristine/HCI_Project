package day01.huy.hci_project.dto;

public class Ingredient {
    private float id;
    private String name;
    private String imageLink;

    public Ingredient(float id, String name, String imageLink) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
