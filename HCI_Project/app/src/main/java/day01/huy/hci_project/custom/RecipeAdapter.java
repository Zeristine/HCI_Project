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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import day01.huy.hci_project.R;
import day01.huy.hci_project.dto.Recipe;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private int recource;
    private List<Recipe> recipes;
    private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private String shortenedTitle;

    public RecipeAdapter(@NonNull Context context, int resource, @NonNull List<Recipe> recipes) {
        super(context, resource, recipes);
        this.context = context;
        this.recource = resource;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_view_recipe, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgRecipe = convertView.findViewById(R.id.imgRecipeImage);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtRecipeTitle);
            viewHolder.txtAuthor = convertView.findViewById(R.id.txtAuthor);
            viewHolder.txtCreateDate = convertView.findViewById(R.id.txtCreateDate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Recipe recipe = recipes.get(position);
        if (recipe.getImageLink().isEmpty()) {
            viewHolder.imgRecipe.setImageResource(R.drawable.no_image_icon);
        }
        shortenedTitle = recipe.getTitle().length() > 12 ? recipe.getTitle().substring(0, 11) + "..." : recipe.getTitle();
        viewHolder.txtTitle.setText(shortenedTitle);
        viewHolder.txtAuthor.setText(recipe.getAuthor());
        viewHolder.txtCreateDate.setText(getRecipeAge(recipe.getCreatedDate()));
        return convertView;
    }

    public class ViewHolder {
        TextView txtTitle, txtAuthor, txtCreateDate;
        ImageView imgRecipe;
    }

    private String getRecipeAge(Date createdDate) {
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();
        int seconds, minutes, hours;
        String secValue = "", minValue = "", hourValue = "";

        seconds = current.getSeconds() - createdDate.getSeconds();
        secValue = seconds + "g";
        if (seconds >= 60) {
            minutes = seconds / 60;
            secValue = (seconds % 60) + "g";
            minValue = minutes + "ph";
            if (minutes >= 60) {
                hours = minutes / 60;
                minValue = (minutes % 60) + "ph";
                hourValue = hours + "h";
            }
        }
        return hourValue + minValue + secValue;
    }
}
