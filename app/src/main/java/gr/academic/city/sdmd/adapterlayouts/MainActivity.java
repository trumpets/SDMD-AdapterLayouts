package gr.academic.city.sdmd.adapterlayouts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int NUMBER_OF_ITEMS_TO_GENERATE = 20;

    private Random rng = new Random();
    private int[] colors = new int[] {Color.RED, Color.GREEN, Color.BLUE};

    private ListView listView;
    private GridView gridView;

    private List<Item> items;

    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = findViewById(R.id.list_view);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this, "You just clicked " + item.toString() + "  from ListView", Toast.LENGTH_SHORT).show();
            }
        });

        this.gridView = findViewById(R.id.grid_view);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getAdapter().getItem(position);
                Toast.makeText(MainActivity.this, "You just clicked " + item.toString() + "  from GridView", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_remove_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.size() > 0) {
                    items.remove(rng.nextInt(items.size()));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        this.items = generateSetOfItems(NUMBER_OF_ITEMS_TO_GENERATE);

        ///// UNCOMMENT ANY ONE OF THE ADAPTERS TO OBSERVE ITS OPERATION //////

        // ArrayAdapter with Androids simple list item layout
        this.adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);

        // ArrayAdapter with our custom item layout
//        this.adapter = new ArrayAdapter<Item>(this, R.layout.layout_item, R.id.tv_name, items);

        // Our custom ItemAdapter with custom rendering logic
//        this.adapter = new ItemAdapter(this, items);

        this.showListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_list_view:
                showListView();
                return true;

            case R.id.menu_item_grid_view:
                showGridView();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showListView() {
        this.gridView.setAdapter(null);
        this.gridView.setVisibility(View.GONE);

        this.listView.setAdapter(this.adapter);
        this.listView.setVisibility(View.VISIBLE);
    }

    private void showGridView() {
        this.listView.setAdapter(null);
        this.listView.setVisibility(View.GONE);

        this.gridView.setAdapter(this.adapter);
        this.gridView.setVisibility(View.VISIBLE);
    }

    private List<Item> generateSetOfItems(int itemsToGenerate) {
        List<Item> items = new ArrayList<>(itemsToGenerate);

        for (int i = 0; i < itemsToGenerate; i++) {
            String itemName = "Item " + (i + 1);
            int quantity = rng.nextInt(20);
            int color = colors[rng.nextInt(colors.length)];

            items.add(new Item(itemName, quantity, color));
        }

        return items;
    }
}
