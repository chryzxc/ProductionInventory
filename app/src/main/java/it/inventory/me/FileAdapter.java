package it.inventory.me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private List<FileList> myListList;
    private Context ct;

    public FileAdapter(List<FileList> myListList, Context ct) {
        this.myListList = myListList;
        this.ct = ct;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.file_rec,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FileList myList=myListList.get(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        holder.fileName.setText(myList.getFileName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ct, ListClass.class);
                intent.putExtra("fileId", myList.getId());
                view.getContext().startActivity(intent);

            }
        });




    }



    @Override
    public int getItemCount() {
        return myListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView fileName,dateCreated,lastUpdated;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileName = (TextView) itemView.findViewById(R.id.fileName);
            dateCreated = (TextView) itemView.findViewById(R.id.dateCreated);
            lastUpdated = (TextView) itemView.findViewById(R.id.lastUpdated);







        }
    }
}