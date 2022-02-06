package it.inventory.me;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class Lists {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void displayLists(Context context, String fileId) {



        db.collection("Files").document(fileId).collection("list").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                if (!snapshots.isEmpty()){

                    ListClass.list_myLists.clear();
                    ListClass.list_adapter = new ListAdapter(ListClass.list_myLists, context);
                    ListClass.list_rv.setAdapter(ListClass.list_adapter);
                    ListClass.list_adapter.notifyDataSetChanged();


                    if (error != null) {
                        Toast.makeText(context, "Listen failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (DocumentSnapshot document : snapshots.getDocuments()) {

                        if (document.exists()){
                            ListClass.list_myLists.add(new ListList(document.getId()));

                        }


                    }

                    ListClass.list_adapter = new ListAdapter(ListClass.list_myLists, context);
                    ListClass.list_rv.setAdapter(ListClass.list_adapter);
                    ListClass.list_adapter.notifyDataSetChanged();

                }

            }
        });


    }

}
