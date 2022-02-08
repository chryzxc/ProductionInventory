package it.inventory.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Workstation extends AppCompatActivity {

    static List<WorkstationsList> workstations_myLists;
    static RecyclerView  workstations_rv;
    static WorkstationsAdapter  workstations_adapter;

    static String fileId;
    static androidx.appcompat.app.AlertDialog addTerminal;
    static View myLayout;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        inflater = getLayoutInflater();

        fileId = getIntent().getStringExtra("fileId");



        workstations_rv = (RecyclerView) findViewById(R.id.list_rec);
        workstations_rv.setHasFixedSize(true);
        workstations_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        workstations_myLists = new ArrayList<>();

        Workstations workstations = new Workstations();
        workstations.displayLists(getApplicationContext(), getIntent().getStringExtra("fileId"));


    }

    public void addTerminal(){

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(Workstation.this);
        myLayout = inflater.inflate(R.layout.add_terminal, null);




        builder.setView(myLayout);
        addTerminal = builder.create();

        addTerminal.setCancelable(true);
        addTerminal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addTerminal.show();

    }



}