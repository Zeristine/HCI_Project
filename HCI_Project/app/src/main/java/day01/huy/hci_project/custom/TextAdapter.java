package day01.huy.hci_project.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import day01.huy.hci_project.R;

public class TextAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource;
    private List<String> items;

    public TextAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_view_simple_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtListItem = convertView.findViewById(R.id.txtListItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtListItem.setText(items.get(position));
        return convertView;
    }

    public class ViewHolder {
        TextView txtListItem;
    }
}
