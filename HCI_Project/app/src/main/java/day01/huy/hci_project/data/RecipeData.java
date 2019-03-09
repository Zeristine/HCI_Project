package day01.huy.hci_project.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import day01.huy.hci_project.dto.Ingredient;

public class RecipeData {
    private final Map<String, List<Ingredient>> vegetarians = new HashMap<>();
    private final Map<String, List<Ingredient>> nonVegetarians = new HashMap<>();
    private final Map<String, List<Ingredient>> drinks = new HashMap<>();
    private final List<Ingredient> mainMan = new ArrayList<>();
    private final List<Ingredient> subMan = new ArrayList<>();
    private final List<Ingredient> mainChay = new ArrayList<>();
    private final List<Ingredient> subChay = new ArrayList<>();
    private final List<Ingredient> mainDrink = new ArrayList<>();
    private final List<Ingredient> subDrink = new ArrayList<>();

    public RecipeData() {
        initMapChay();
        initMapDrink();
        initMapMan();
    }

    private void initMapMan() {
        mainMan.add(new Ingredient(1, "rau muống", "raumuong"));
        mainMan.add(new Ingredient(2, "cà rốt", "carot"));
        mainMan.add(new Ingredient(3, "củ cải trắng", "cucaitrang"));
        mainMan.add(new Ingredient(4, "khoai tây", "khoaitay"));
        mainMan.add(new Ingredient(5, "trứng", "trung"));
        mainMan.add(new Ingredient(6, "thịt bò", "thitbo"));
        mainMan.add(new Ingredient(7, "thịt heo", "thitheo"));
        mainMan.add(new Ingredient(8, "thịt gà", "thitga"));
        mainMan.add(new Ingredient(9, "thịt cá", "thitca"));
        mainMan.add(new Ingredient(10, "thịt tôm", "thittom"));
        mainMan.add(new Ingredient(11, "thịt cua", "thitcua"));

        subMan.add(new Ingredient(1, "muối", "muoi"));
        subMan.add(new Ingredient(2, "đường", "duong"));
        subMan.add(new Ingredient(3, "tiêu", "tieu"));
        subMan.add(new Ingredient(4, "tỏi", "toi"));
        subMan.add(new Ingredient(5, "ớt", "ot"));
        subMan.add(new Ingredient(6, "hành phi", "hanhphi"));
        subMan.add(new Ingredient(7, "hành lá", "hanhla"));
        subMan.add(new Ingredient(8, "hành tây", "hanhtay"));

        nonVegetarians.put("main", mainMan);
        nonVegetarians.put("sub", subMan);
    }

    private void initMapChay() {
        mainChay.add(new Ingredient(1, "rau muống", "raumuong"));
        mainChay.add(new Ingredient(2, "tàu hủ", "tauhu"));
        mainChay.add(new Ingredient(3, "cà rốt", "carot"));
        mainChay.add(new Ingredient(4, "nấm", "nam"));
        mainChay.add(new Ingredient(5, "khoai tây", "khoaitay"));
        mainChay.add(new Ingredient(6, "bông cải", "bongcai"));
        mainChay.add(new Ingredient(7, "gạo lứt", "gaolut"));

        subChay.add(new Ingredient(1, "muối", "muoi"));
        subChay.add(new Ingredient(2, "đường", "duong"));
        subChay.add(new Ingredient(3, "tiêu", "tieu"));
        subChay.add(new Ingredient(4, "tỏi", "toi"));
        subChay.add(new Ingredient(5, "ớt", "ot"));
        subChay.add(new Ingredient(6, "hành phi", "hanhphi"));
        subChay.add(new Ingredient(7, "hành lá", "hanhla"));
        subChay.add(new Ingredient(8, "hành tay", "hanhtay"));

        vegetarians.put("main", mainChay);
        vegetarians.put("sub", subChay);
    }

    private void initMapDrink() {
        mainDrink.add(new Ingredient(1, "rượu đỏ", "ruoudo"));
        mainDrink.add(new Ingredient(2, "rượu trắng", "ruoutrang"));
        mainDrink.add(new Ingredient(3, "champagne", "champagne"));
        mainDrink.add(new Ingredient(4, "cà phê đen", "capheden"));
        mainDrink.add(new Ingredient(5, "sữa", "sua"));

        subDrink.add(new Ingredient(1, "dâu tây", "dautay"));
        subDrink.add(new Ingredient(1, "xoài", "xoai"));
        subDrink.add(new Ingredient(1, "chanh", "chanh"));
        subDrink.add(new Ingredient(1, "mật ong", "matong"));
        subDrink.add(new Ingredient(1, "dưa hấu", "duahau"));
        subDrink.add(new Ingredient(1, "bơ", "bo"));

        drinks.put("main", mainDrink);
        drinks.put("sub", subDrink);
    }

    public Map<String, List<Ingredient>> getVegetarians() {
        return vegetarians;
    }

    public Map<String, List<Ingredient>> getNonVegetarians() {
        return nonVegetarians;
    }

    public Map<String, List<Ingredient>> getDrinks() {
        return drinks;
    }

    public List<String> getIngredientOneType(List<Ingredient> main, List<Ingredient> sub){
        List<String> list = new ArrayList<>();
        for (Ingredient ingredient: main) {
            list.add(ingredient.getName());
        }
        for (Ingredient ingredient: sub) {
            list.add(ingredient.getName());
        }
        return list;
    }

    public boolean hasContain(List<Ingredient> list, String value){
        for (Ingredient ingredient: list) {
            if(ingredient.getName().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
}
