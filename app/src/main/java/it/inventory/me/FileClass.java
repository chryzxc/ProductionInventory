package it.inventory.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FileClass extends AppCompatActivity {

    static List<FileList> file_myLists;
    static RecyclerView file_rv;
    static FileAdapter file_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);



        file_rv = (RecyclerView) findViewById(R.id.file_rec);
        file_rv.setHasFixedSize(true);
        file_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        file_myLists = new ArrayList<>();

        Files files = new Files();
        files.displayFiles(getApplicationContext());
    }
}