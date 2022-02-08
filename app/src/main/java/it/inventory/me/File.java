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
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class File extends AppCompatActivity {

    static List<FileList> file_myLists;
    static RecyclerView file_rv;
    static FileAdapter file_adapter;
    static androidx.appcompat.app.AlertDialog addFile;
    static View myLayout;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        inflater = getLayoutInflater();

        TextView textSecret = findViewById(R.id.textSecret);
        textSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Developed by: Christian Rey Villablanca", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.base_900))
                        .show();
            }
        });

        FloatingActionButton fabAddFile = findViewById(R.id.fabAddFile);
        fabAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFile();
            }
        });





        file_rv = (RecyclerView) findViewById(R.id.file_rec);
        file_rv.setHasFixedSize(true);
        file_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        file_myLists = new ArrayList<>();

        Files files = new Files();
        files.displayFiles(getApplicationContext());
    }

    public void addFile(){

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(File.this);
        myLayout = inflater.inflate(R.layout.add_file, null);




        builder.setView(myLayout);
        addFile = builder.create();

        addFile.setCancelable(true);
        addFile.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addFile.show();

    }
}