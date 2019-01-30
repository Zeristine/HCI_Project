package day01.huy.hci_project.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.R;
import day01.huy.hci_project.dto.Recipe;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private int recource;
    private List<Recipe> recipes;

    public RecipeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Recipe> recipes) {
        super(context, resource, recipes);
        this.context = context;
        this.recource = resource;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_view_recipe, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgRecipe = convertView.findViewById(R.id.imgRecipeImage);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtRecipeTitle);
            viewHolder.txtAuthor = convertView.findViewById(R.id.txtAuthor);
            viewHolder.txtCreateDate = convertView.findViewById(R.id.txtCreateDate);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Recipe recipe = recipes.get(position);
//        viewHolder.imgRecipe.setImageDrawable();
        viewHolder.txtTitle.setText(recipe.getTitle());
        viewHolder.txtAuthor.setText(recipe.getAuthor());
        viewHolder.txtCreateDate.setText(recipe.getCreatedDate().toString());
        return convertView;
    }

    public class ViewHolder{
        TextView txtTitle, txtAuthor, txtCreateDate;
        ImageView imgRecipe;
    }
}
