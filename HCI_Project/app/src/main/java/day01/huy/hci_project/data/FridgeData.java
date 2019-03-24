package day01.huy.hci_project.data;

import java.util.ArrayList;
import java.util.List;

public class FridgeData {
    private static List<String> list = new ArrayList<>();

    public FridgeData() {

    }

    public List<String> getList() {
        return list;
    }

    //add Ingredient to List
    public void addIngredientToFridge(String ingredient, String amount, String unit) {
        list.add(ingredient + "-" + amount + "-" + unit);
    }

    //update Ingredient in List
    public void updateIngredientInFridge(String ingredient, String amount, String unit) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(ingredient)) {
                list.set(i, ingredient + "-" + amount + "-" + unit);
                return;
            }
        }
    }

    //delete Ingredient in list
    public void deleteIngredientInFridge(String ingredient) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(ingredient)) {
                list.remove(i);
            }
        }
    }

    public boolean checkIngredientExist(String ingredient){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(ingredient)) {
                return true;
            }
        }
        return false;
    }
}
