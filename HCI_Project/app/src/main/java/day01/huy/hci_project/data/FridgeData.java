package day01.huy.hci_project.data;

import java.util.List;

import day01.huy.hci_project.dto.Ingredient;

public class FridgeData {
    private List<Ingredient> list;

    public FridgeData(List<Ingredient> list) {
        this.list = list;
    }

    public List<Ingredient> getList() {
        return list;
    }

    public void setList(List<Ingredient> list) {
        this.list = list;
    }

    

    //add Ingredient to List
    public void addIngredientToFridge(Ingredient ingredient){
        list.add(ingredient);
    }

    //update Ingredient in List
    public void updateIngredientInFridge(Ingredient ingredient){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(ingredient.getName())){
                list.set(i,ingredient);
            }
        }
    }

    //delete Ingredient in list
    public void deleteIngredientInFridge(Ingredient ingredient){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(ingredient.getName())){
                list.remove(i);
            }
        }
    }

    public List<Ingredient> showAllIngredient(){
        return list;
    }

}
