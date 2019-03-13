package day01.huy.hci_project.data;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.dto.Recipe;

public class RecipeData {
    private final List<Recipe> recipes = new ArrayList<>();
    private static final List<Recipe> favorites = new ArrayList<>();

    public RecipeData() {
    }

    private void initRecipes() {
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
        recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 0, null));
    }

    public void addToFavorite(Recipe recipe) {
        favorites.add(recipe);
    }

    public List<Recipe> getFavorites() {
        return favorites;
    }

    public String removeFromFavorite(String recipeTitle) {
        Recipe recipe = getRecipe(recipeTitle);
        if (recipe == null) {
            return "error";
        }
        if (favorites.contains(recipe)) {
            favorites.remove(recipe);
            return "success";
        }
        return "fail";
    }

    public Recipe getRecipe(String recipeTitle) {
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(recipeTitle)) {
                return recipe;
            }
        }
        return null;
    }
}
