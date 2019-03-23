package day01.huy.hci_project.dto;

public class Ingredient {
    private float id;
    private String name;
    private String parent;
    private String imageLink;
    private String type;


    public Ingredient(float id, String name, String parent, String imageLink, String type) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.imageLink = imageLink;
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

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
