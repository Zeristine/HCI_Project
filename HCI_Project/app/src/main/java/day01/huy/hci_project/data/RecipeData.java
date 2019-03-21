package day01.huy.hci_project.data;

import org.jetbrains.annotations.Contract;

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
            recipes.add(new Recipe(1, "Rau muống xào tỏi", "HuyLM", "Rau muống xào tỏi trứ danh", "raumuong", null, 3.5, null, null));
            recipes.add(new Recipe(2, "Canh rau muống", "HuyLM", "Canh rau muống đậm đà hương vị", "canhraumuong", null, 4.5, null, null));
            recipes.add(new Recipe(3, "Chè", "HuyLM", "Chè thập cẩm thơm ngon", "che", null, 4, null, null));
            recipes.add(new Recipe(4, "Chè trân châu", "HuyLM", "Chè trân châu sánh mịn", "chetranchau", null, 0, null, null));
            recipes.add(new Recipe(5, "Viên Socola", "HuyLM", "Viên socola ngon lành", "chocoball", null, 0, null, null));
            recipes.add(new Recipe(6, "Gà chiên", "HieuBT", "Gà chiên giòn tan", "garan", null, 0, null, null));
            recipes.add(new Recipe(7, "Hamburger", "HieuBT", "Hamburger ", "hamburger", null, 0, null, null));
            recipes.add(new Recipe(8, "Nui xào bò", "HieuBT", "Nui xào bò ngon lành", "nuixaobo", null, 0, null, null));
            recipes.add(new Recipe(9, "Canh chua cá lóc", "HuyLM", "Hương vị đồng quê", "canhchuacaloc", null, 0, null, null));
            recipes.add(new Recipe(10, "Thịt heo quay", "HuyLM", "Bí kíp da giòn", "thitheoquay", null, 0, null, null));
            recipes.add(new Recipe(11, "Cá chiên xù", "HuyLM", "Cá chiên giòn rượm", "cachienxu", null, 0, null, null));
            recipes.add(new Recipe(12, "Cua rang me", "HuyLM", "Cua rang chua ngọt", "cuarangme", null, 0, null, null));
            recipes.add(new Recipe(13, "Tàu hủ chiên", "HuyLM", "Tàu hủ chiên vàng", "tauhuchien", null, 0, null, null));
            recipes.add(new Recipe(14, "Khoai tây chiên", "HuyLM", "Món Âu khó cưỡng", "khoaitaychien", null, 0, null, null));
            recipes.add(new Recipe(15, "Rau muống xào tỏi", "HieuBT", "Rau muống xào tỏi trứ danh", "raumuong", null, 2.5, null, null));

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

    @Contract(pure = true)
    public List<Recipe> getRecipes() {
        return recipes;
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

    public Recipe getRecipeByTitleAndChef(String chef, String title) {
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(title) && recipe.getAuthor().equals(chef)) {
                return recipe;
            }
        }
        return null;
    }

    public boolean isContributed(Recipe target) {
        List<Recipe> sameRecipeList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equals(target.getTitle())) {
                sameRecipeList.add(recipe);
            }
        }
        if (!sameRecipeList.isEmpty()) {
            if (sameRecipeList.get(0).getId() != target.getId()) {
                return true;
            }
        }
        return false;
    }

    public void removeFromRecipe(Recipe target, String type) {
        switch (type) {
            case "contribute":
                for (Recipe recipe : recipes) {
                    if (recipe.getId() == target.getId()) {
                        recipes.remove(recipe);
                        removeFromFavorite(recipe);
                        return;
                    }
                }
            case "post":
                for (Recipe recipe : recipes) {
                    if (recipe.getTitle().equals(target.getTitle())) {
                        recipes.remove(recipe);
                        removeFromFavorite(recipe);
                    }
                }
                break;
        }
    }
}
