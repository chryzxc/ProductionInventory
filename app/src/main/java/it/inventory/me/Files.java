package it.inventory.me;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class Files {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void displayFiles(Context context) {



        db.collection("Files").orderBy("file_name").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                if (!snapshots.isEmpty()){

                    File.file_myLists.clear();
                    File.file_adapter = new FileAdapter(File.file_myLists, context);
                    File.file_rv.setAdapter(File.file_adapter);
                    File.file_adapter.notifyDataSetChanged();


                    if (error != null) {
                        Toast.makeText(context, "Listen failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (DocumentSnapshot document : snapshots.getDocuments()) {

                        if (document.exists()){
                            File.file_myLists.add(new FileList(document.getId(),document.getString("file_name"),document.getDate("date_created"),document.getDate("last_updated"),document.getString("password")));

                        }


                    }

                    File.file_adapter = new FileAdapter(File.file_myLists, context);
                    File.file_rv.setAdapter(File.file_adapter);
                    File.file_adapter.notifyDataSetChanged();

                }

            }
        });


    }

}
