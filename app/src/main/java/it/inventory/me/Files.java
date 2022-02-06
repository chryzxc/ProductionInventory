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

                    FileClass.file_myLists.clear();
                    FileClass.file_adapter = new FileAdapter(FileClass.file_myLists, context);
                    FileClass.file_rv.setAdapter(FileClass.file_adapter);
                    FileClass.file_adapter.notifyDataSetChanged();


                    if (error != null) {
                        Toast.makeText(context, "Listen failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (DocumentSnapshot document : snapshots.getDocuments()) {

                        if (document.exists()){
                            FileClass.file_myLists.add(new FileList(document.getId(),document.getString("file_name"),null,null));

                        }


                    }

                    FileClass.file_adapter = new FileAdapter(FileClass.file_myLists, context);
                    FileClass.file_rv.setAdapter(FileClass.file_adapter);
                    FileClass.file_adapter.notifyDataSetChanged();

                }

            }
        });


    }

}
