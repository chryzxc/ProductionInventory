package it.inventory.me;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class Workstations {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void displayLists(Context context, String fileId) {



        db.collection("Files").document(fileId).collection("list").orderBy("computer_name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                if (!snapshots.isEmpty()){

                    Workstation.workstations_myLists.clear();
                    Workstation.workstations_adapter = new WorkstationsAdapter(Workstation.workstations_myLists, context);
                    Workstation.workstations_rv.setAdapter(Workstation.workstations_adapter);
                    Workstation.workstations_adapter.notifyDataSetChanged();


                    if (error != null) {
                        Toast.makeText(context, "Listen failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for (DocumentSnapshot document : snapshots.getDocuments()) {

                        if (document.exists()){
                            Workstation.workstations_myLists.add(new WorkstationsList(document.getLong("computer_name").intValue()));

                        }


                    }

                    Workstation.workstations_adapter = new WorkstationsAdapter(Workstation.workstations_myLists, context);
                    Workstation.workstations_rv.setAdapter(Workstation.workstations_adapter);
                    Workstation.workstations_adapter.notifyDataSetChanged();

                }

            }
        });


    }

}
