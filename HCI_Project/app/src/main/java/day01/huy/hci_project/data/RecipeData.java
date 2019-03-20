package day01.huy.hci_project.data;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.dto.Recipe;

public class RecipeData {
    private static final List<Recipe> recipes = new ArrayList<>();
    private static final List<Recipe> favorites = new ArrayList<>();

    public RecipeData() {
        initRecipes();
        initFavorite();
    }

    private void initRecipes() {
        if (recipes.isEmpty()) {
            recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "adada", "raumuong", 3.5, null, ""));
            recipes.add(new Recipe(2, "Canh rau muống", "HuyLM", "adada", "canhraumuong", 4.5, null, ""));
            recipes.add(new Recipe(3, "Chè", "HuyLM", "adada", "che", 4, null, ""));
            recipes.add(new Recipe(4, "Chè trân châu", "HuyLM", "adada", "chetranchau", 0, null, ""));
            recipes.add(new Recipe(5, "Viên Socola", "HuyLM", "adada", "chocoball", 0, null, ""));
            recipes.add(new Recipe(6, "Gà chiên", "HieuBT", "adada", "garan", 0, null, ""));
            recipes.add(new Recipe(7, "Hamburger", "HieuBT", "adada", "hamburger", 0, null, ""));
            recipes.add(new Recipe(8, "Nui xào bò", "HieuBT", "adada", "nuixaobo", 0, null, ""));
            recipes.add(new Recipe(9, "Canh chua cá lóc", "HuyLM", "adada", "canhchuacaloc", 0, null, ""));
            recipes.add(new Recipe(10, "Thịt heo quay", "HuyLM", "adada", "thitheoquay", 0, null, ""));
            recipes.add(new Recipe(11, "Cá chiên xù", "HuyLM", "adada", "cachienxu", 0, null, ""));
            recipes.add(new Recipe(12, "Cua rang me", "HuyLM", "adada", "cuarangme", 0, null, ""));
            recipes.add(new Recipe(13, "Tàu hủ chiên", "HuyLM", "adada", "tauhuchien", 0, null, ""));
            recipes.add(new Recipe(14, "Khoai tây chiên", "HuyLM", "adada", "khoaitaychien", 0, null, ""));
            recipes.add(new Recipe(15, "Rau muống xào tỏi", "HieuBT", "adada", "raumuong", 3.5, null, ""));

        }
    }

    private void initFavorite() {
        if (favorites.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                favorites.add(recipes.get(i));
            }
        }
    }

    public void addToFavorite(Recipe recipe) {
        favorites.add(recipe);
    }

    public List<Recipe> getFavorites() {
        return favorites;
    }

    public String removeFromFavorite(String recipeTitle) {
        Recipe recipe = findRecipeByTitle(recipeTitle);
        if (recipe == null) {
            return "error";
        }
        return removeFromFavorite(recipe);
    }

    public String removeFromFavorite(Recipe recipe) {
        Recipe target = isContainedInFavorite(recipe);
        if (target != null) {
            favorites.remove(target);
            return "success";
        }
        return "fail";
    }


    public Recipe findRecipeByTitle(String recipeTitle) {
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(recipeTitle)) {
                return recipe;
            }
        }
        return null;
    }

    public Recipe findRecipeById(long id) {
        for (Recipe recipe : recipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }

    public Recipe isContainedInFavorite(Recipe target) {
        for (Recipe recipe : favorites) {
            if (recipe.getId() == target.getId()) {
                return recipe;
            }
        }
        return null;
    }

    public List<Recipe> getRecipesSameChef(String chef, int limit, String duplicateRecipe) {
        List<Recipe> recipeList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getAuthor().equals(chef) && !recipe.getTitle().equals(duplicateRecipe)) {
                recipeList.add(recipe);
                if (limit != 0) {
                    if (recipeList.size() == limit) {
                        break;
                    }
                }
            }
        }
        return recipeList;
    }

    public List<Recipe> findRecipesByTitle(String title) {
        List<Recipe> recipeList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().toLowerCase().contains(title.toLowerCase()) && !recipeList.contains(recipe)) {
                recipeList.add(recipe);
            }
        }
        return recipeList;
    }

    public List<String> getChefsMakeSameRecipe(String recipeName) {
        List<String> chefs = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(recipeName)) {
                chefs.add(recipe.getAuthor());
            }
        }
        return chefs;
    }
}
