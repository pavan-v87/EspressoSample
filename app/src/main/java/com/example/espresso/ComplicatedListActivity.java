package com.example.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ComplicatedListActivity extends AppCompatActivity {

    private CustomListAdapter adapter;
    private EditText nameEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complicated_list);
        nameEditText = findViewById(R.id.nameText);
        descriptionEditText = findViewById(R.id.descriptionText);

        ListView listView = findViewById(R.id.listView);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Name 1", "Description 1"));
        items.add(new Item("Name 2", "Description 2"));
        items.add(new Item("Name 3", "Description 3"));
        items.add(new Item("Name 4", "Description 4"));
        items.add(new Item("Name 5", "Description 5"));
        items.add(new Item("Name 6", "Description 6"));

        adapter=new CustomListAdapter(items);
        listView.setAdapter(adapter);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(new Item(nameEditText.getText().toString(), descriptionEditText.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });

    }

    private static class CustomListAdapter extends BaseAdapter {


        private final ArrayList<Item> items;

        public CustomListAdapter(ArrayList<Item> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = View.inflate(parent.getContext(), R.layout.list_row, null);
            }
            Item item = (Item) getItem(position);
            TextView name = view.findViewById(R.id.name);
            name.setText(item.getName());

            TextView description = view.findViewById(R.id.description);
            description.setText(item.getDescription());

            return view;
        }
    }
}
