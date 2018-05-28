package com.example.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicListActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private EditText editView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_basic_list);
        editView = findViewById(R.id.itemText);

        ListView listView = findViewById(R.id.listView);

        adapter = (ArrayAdapter<String>) listView.getAdapter();
        final ArrayList<String> stringArray = new ArrayList<>();
        stringArray.addAll(Arrays.asList(getResources().getStringArray(R.array.items_array)));

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                stringArray);
        listView.setAdapter(adapter);


        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringArray.add(editView.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
