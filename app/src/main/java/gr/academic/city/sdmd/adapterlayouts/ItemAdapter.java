package gr.academic.city.sdmd.adapterlayouts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by trumpets on 3/2/16.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Item getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.layout_item, parent, false);
        }

        Item item = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.tv_name);
        TextView quantityTextView = convertView.findViewById(R.id.tv_quantity);

        Log.d("Ivo", "Old value is " + nameTextView.getText().toString() + " but we will set it to " + item.getName());

        nameTextView.setText(item.getName());
        nameTextView.setTextColor(item.getColor());
        quantityTextView.setText(String.valueOf(item.getQuantity()));
        quantityTextView.setTextColor(item.getColor());

        return convertView;
    }
}
