package it.inventory.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListClass extends AppCompatActivity {

    static List<ListList> list_myLists;
    static RecyclerView list_rv;
    static ListAdapter list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        list_rv = (RecyclerView) findViewById(R.id.list_rec);
        list_rv.setHasFixedSize(true);
        list_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list_myLists = new ArrayList<>();

        Lists lists = new Lists();
        lists.displayLists(getApplicationContext(), getIntent().getStringExtra("fileId"));


    }
}