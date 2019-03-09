package day01.huy.hci_project.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import day01.huy.hci_project.R;

public class TextAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource;
    private List<String> items;
    private List<String> selectedItems;

    public TextAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
        this.selectedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_pick_ingredient_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = convertView.findViewById(R.id.imgIngredient);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String value = items.get(position);
        viewHolder.checkBox.setText(value);
        if(!selectedItems.isEmpty()){
            if(selectedItems.contains(value)){
                viewHolder.checkBox.setChecked(true);
            }else{
                viewHolder.checkBox.setChecked(false);
            }
        }
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedValue = viewHolder.checkBox.getText().toString();
                if (!viewHolder.checkBox.isChecked()) {
                    if (selectedItems.contains(selectedValue)) {
                        selectedItems.remove(selectedValue);
                        Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    selectedItems.add(selectedValue);
                    Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    public class ViewHolder {
        CheckBox checkBox;
    }

    public List<String> getSelectedItems(){
        return selectedItems;
    }

    public boolean addSelectedIngredient(String ingredient){
        if(items.contains(ingredient)){
            selectedItems.add(ingredient);
            return true;
        }
        return false;
    }
}
