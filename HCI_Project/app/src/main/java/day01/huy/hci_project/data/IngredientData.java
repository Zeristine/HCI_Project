package day01.huy.hci_project.data;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import day01.huy.hci_project.dto.Ingredient;

public class IngredientData {
    private final Map<String, List<Ingredient>> vegetarians = new HashMap<>();
    private final Map<String, List<Ingredient>> nonVegetarians = new HashMap<>();
    private final Map<String, List<Ingredient>> drinks = new HashMap<>();
    private final List<Ingredient> mainMan = new ArrayList<>();
    private final List<Ingredient> subMan = new ArrayList<>();
    private final List<Ingredient> mainChay = new ArrayList<>();
    private final List<Ingredient> subChay = new ArrayList<>();
    private final List<Ingredient> mainDrink = new ArrayList<>();
    private final List<Ingredient> subDrink = new ArrayList<>();

    public IngredientData() {
        initMapChay();
        initMapDrink();
        initMapMan();
    }

    private void initMapMan() {
        mainMan.add(new Ingredient(1, "rau muống","", "raumuong","gram"));
        mainMan.add(new Ingredient(2, "cà rốt","", "carot","gram"));
        mainMan.add(new Ingredient(3, "củ cải trắng","", "cucaitrang","gram"));
        mainMan.add(new Ingredient(4, "khoai tây","", "khoaitay","gram"));
        mainMan.add(new Ingredient(5, "trứng","", "trung","gram"));
        mainMan.add(new Ingredient(6, "thịt bò","", "thitbo","gram"));
        mainMan.add(new Ingredient(7, "thịt heo", "","thitheo","gram"));
        mainMan.add(new Ingredient(8, "thịt gà", "","thitga","gram"));
        mainMan.add(new Ingredient(9, "thịt cá", "","thitca","gram"));
        mainMan.add(new Ingredient(10, "thịt tôm","", "thittom","gram"));
        mainMan.add(new Ingredient(11, "thịt cua","", "thitcua","gram"));

        subMan.add(new Ingredient(1, "muối","", "muoi","gram"));
        subMan.add(new Ingredient(2, "đường","", "duong","gram"));
        subMan.add(new Ingredient(3, "tiêu","", "tieu","gram"));
        subMan.add(new Ingredient(4, "tỏi","", "toi","gram"));
        subMan.add(new Ingredient(5, "ớt","", "ot","gram"));
        subMan.add(new Ingredient(6, "hành phi", "","hanhphi","gram"));
        subMan.add(new Ingredient(7, "hành lá", "","hanhla","gram"));
        subMan.add(new Ingredient(8, "hành tây", "","hanhtay","gram"));

        nonVegetarians.put("main", mainMan);
        nonVegetarians.put("sub", subMan);
    }

    private void initMapChay() {
        mainChay.add(new Ingredient(1, "rau muống","", "raumuong","gram"));
        mainChay.add(new Ingredient(2, "tàu hủ","", "tauhu","gram"));
        mainChay.add(new Ingredient(3, "cà rốt","", "carot","gram"));
        mainChay.add(new Ingredient(4, "nấm","", "nam","gram"));
        mainChay.add(new Ingredient(5, "khoai tây", "","khoaitay","gram"));
        mainChay.add(new Ingredient(6, "bông cải", "","bongcai","gram"));
        mainChay.add(new Ingredient(7, "gạo lứt","", "gaolut","gram"));

        subChay.add(new Ingredient(1, "muối", "","muoi","gram"));
        subChay.add(new Ingredient(2, "đường", "","duong","gram"));
        subChay.add(new Ingredient(3, "tiêu", "","tieu","gram"));
        subChay.add(new Ingredient(4, "tỏi", "","toi","gram"));
        subChay.add(new Ingredient(5, "ớt", "","ot","gram"));
        subChay.add(new Ingredient(6, "hành phi", "","hanhphi","gram"));
        subChay.add(new Ingredient(7, "hành lá", "","hanhla","gram"));
        subChay.add(new Ingredient(8, "hành tay", "","hanhtay","gram"));

        vegetarians.put("main", mainChay);
        vegetarians.put("sub", subChay);
    }

    private void initMapDrink() {
        mainDrink.add(new Ingredient(1, "rượu đỏ","", "ruoudo","ml"));
        mainDrink.add(new Ingredient(2, "rượu trắng","", "ruoutrang","ml"));
        mainDrink.add(new Ingredient(3, "champagne", "","champagne","ml"));
        mainDrink.add(new Ingredient(4, "cà phê đen", "","capheden","ml"));
        mainDrink.add(new Ingredient(5, "sữa", "","sua","ml"));

        subDrink.add(new Ingredient(1, "dâu tây", "","dautay","gram"));
        subDrink.add(new Ingredient(1, "xoài","", "xoai","gram"));
        subDrink.add(new Ingredient(1, "chanh", "","chanh","gram"));
        subDrink.add(new Ingredient(1, "mật ong", "","matong","ml"));
        subDrink.add(new Ingredient(1, "dưa hấu","", "duahau","gram"));
        subDrink.add(new Ingredient(1, "bơ","", "bo","gram"));

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

    public List<Ingredient> getIngredients(){
        List<Ingredient> list = new ArrayList<>();
        list.addAll(nonVegetarians.get("main"));
        list.addAll(nonVegetarians.get("sub"));
        list.addAll(vegetarians.get("main"));
        list.addAll(vegetarians.get("sub"));
        list.addAll(drinks.get("main"));
        list.addAll(drinks.get("sub"));
        return list;
    }

    public List<String> getIngredientOneType(List<Ingredient> main, List<Ingredient> sub) {
        List<String> list = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>(main);
        ingredients.addAll(sub);
        for (Ingredient ingredient : ingredients) {
            list.add(ingredient.getName());
        }
        return list;
    }

    public List<String> getIngredientListOfString(List<Ingredient> list){
        List<String> stringList = new ArrayList<>();
        for (Ingredient ingredient: list) {
            stringList.add(ingredient.getName());
        }
        return stringList;
    }

    public boolean hasContain(List<Ingredient> list, String value) {
        for (Ingredient ingredient : list) {
            if (ingredient.getName().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasContainOneMainIngredient(List<Ingredient> main, List<String> selected){
        for (String value: selected) {
            if(hasContain(main, value)){
                return true;
            }
        }
        return  false;
    }

    public List<Ingredient> getSelectedIngredientByTitle(List<String> selected, String type){
        List<Ingredient> ingredientList = new ArrayList<>();
        List<Ingredient> selectedIngredient = new ArrayList<>();
        switch (type){
            case "chay":
                ingredientList.addAll(getVegetarians().get("main"));
                ingredientList.addAll(getVegetarians().get("sub"));
                break;
            case "man":
                ingredientList.addAll(getNonVegetarians().get("main"));
                ingredientList.addAll(getNonVegetarians().get("sub"));
                break;
            case "nuoc":
                ingredientList.addAll(getDrinks().get("main"));
                ingredientList.addAll(getDrinks().get("sub"));
                break;
        }
        for (Ingredient ingredient : ingredientList) {
            for (String item: selected) {
                if(ingredient.getName().equals(item)){
                    selectedIngredient.add(ingredient);
                    break;
                }
            }
        }
        return selectedIngredient;
    }


}
