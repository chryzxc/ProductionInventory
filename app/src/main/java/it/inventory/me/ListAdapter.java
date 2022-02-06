package it.inventory.me;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListList> myListList;
    private Context ct;

    public ListAdapter(List<ListList> myListList, Context ct) {
        this.myListList = myListList;
        this.ct = ct;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rec,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListList myList=myListList.get(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        holder.computerName.setText(myList.getComputerName());





    }



    @Override
    public int getItemCount() {
        return myListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView computerName;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            computerName = (TextView) itemView.findViewById(R.id.computerName);







        }
    }
}