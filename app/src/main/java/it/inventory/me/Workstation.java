package it.inventory.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Workstation extends AppCompatActivity {

    static List<WorkstationsList> workstations_myLists;
    static RecyclerView  workstations_rv;
    static WorkstationsAdapter  workstations_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        workstations_rv = (RecyclerView) findViewById(R.id.list_rec);
        workstations_rv.setHasFixedSize(true);
        workstations_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workstations_myLists = new ArrayList<>();

        Workstations workstations = new Workstations();
        workstations.displayLists(getApplicationContext(), getIntent().getStringExtra("fileId"));


    }
}