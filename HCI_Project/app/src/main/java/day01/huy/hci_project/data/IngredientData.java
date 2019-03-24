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
        mainMan.add(new Ingredient(1, "Rau muống","", "raumuong","gram"));
        mainMan.add(new Ingredient(2, "Cà rốt","", "carot","gram"));
        mainMan.add(new Ingredient(3, "Củ cải trắng","", "cucaitrang","gram"));
        mainMan.add(new Ingredient(4, "Khoai tây","", "khoaitay","gram"));
        mainMan.add(new Ingredient(5, "Trứng","", "trung","gram"));
        mainMan.add(new Ingredient(6, "Thịt bò","", "thitbo","gram"));
        mainMan.add(new Ingredient(7, "Thịt heo", "","thitheo","gram"));
        mainMan.add(new Ingredient(8, "Thịt gà", "","thitga","gram"));
        mainMan.add(new Ingredient(9, "Cá", "","thitca","gram"));
        mainMan.add(new Ingredient(10, "Tôm","", "thittom","gram"));
        mainMan.add(new Ingredient(11, "Cua","", "thitcua","gram"));

        subMan.add(new Ingredient(1, "Muối","", "muoi","gram"));
        subMan.add(new Ingredient(2, "Đường","", "duong","gram"));
        subMan.add(new Ingredient(3, "Tiêu","", "tieu","gram"));
        subMan.add(new Ingredient(4, "Tỏi","", "toi","gram"));
        subMan.add(new Ingredient(5, "Ớt","", "ot","gram"));
        subMan.add(new Ingredient(6, "Hành phi", "","hanhphi","gram"));
        subMan.add(new Ingredient(7, "Hành lá", "","hanhla","gram"));
        subMan.add(new Ingredient(8, "Hành tây", "","hanhtay","gram"));

        nonVegetarians.put("main", mainMan);
        nonVegetarians.put("sub", subMan);
    }

    private void initMapChay() {
        mainChay.add(new Ingredient(1, "Rau muống","", "raumuong","gram"));
        mainChay.add(new Ingredient(2, "Tàu hủ","", "tauhu","gram"));
        mainChay.add(new Ingredient(3, "Cà rốt","", "carot","gram"));
        mainChay.add(new Ingredient(4, "Nấm","", "nam","gram"));
        mainChay.add(new Ingredient(5, "Khoai tây", "","khoaitay","gram"));
        mainChay.add(new Ingredient(6, "Bông cải", "","bongcai","gram"));
        mainChay.add(new Ingredient(7, "Gạo lứt","", "gaolut","gram"));

        subChay.add(new Ingredient(1, "Muối","", "muoi","gram"));
        subChay.add(new Ingredient(2, "Đường","", "duong","gram"));
        subChay.add(new Ingredient(3, "Tiêu","", "tieu","gram"));
        subChay.add(new Ingredient(4, "Tỏi","", "toi","gram"));
        subChay.add(new Ingredient(5, "Ớt","", "ot","gram"));
        subChay.add(new Ingredient(6, "Hành phi", "","hanhphi","gram"));
        subChay.add(new Ingredient(7, "Hành lá", "","hanhla","gram"));
        subChay.add(new Ingredient(8, "Hành tây", "","hanhtay","gram"));

        vegetarians.put("main", mainChay);
        vegetarians.put("sub", subChay);
    }

    private void initMapDrink() {
        mainDrink.add(new Ingredient(1, "Rượu đỏ","", "ruoudo","ml"));
        mainDrink.add(new Ingredient(2, "Rượu trắng","", "ruoutrang","ml"));
        mainDrink.add(new Ingredient(3, "Champagne", "","champagne","ml"));
        mainDrink.add(new Ingredient(4, "Cà phê đen", "","capheden","ml"));
        mainDrink.add(new Ingredient(5, "Sữa", "","sua","ml"));

        subDrink.add(new Ingredient(1, "Dâu tây", "","dautay","gram"));
        subDrink.add(new Ingredient(1, "Xoài","", "xoai","gram"));
        subDrink.add(new Ingredient(1, "Chanh", "","chanh","gram"));
        subDrink.add(new Ingredient(1, "Mật ong", "","matong","ml"));
        subDrink.add(new Ingredient(1, "Dưa hấu","", "duahau","gram"));
        subDrink.add(new Ingredient(1, "Bơ","", "bo","gram"));

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
