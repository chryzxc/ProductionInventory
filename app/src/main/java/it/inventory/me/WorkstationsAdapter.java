package it.inventory.me;

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

public class WorkstationsAdapter extends RecyclerView.Adapter<WorkstationsAdapter.ViewHolder> {
    private List<WorkstationsList> myListList;
    private Context ct;

    public WorkstationsAdapter(List<WorkstationsList> myListList, Context ct) {
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
        WorkstationsList myList=myListList.get(position);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        holder.computerName.setText(String.valueOf(myList.getComputerName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ct, Specification.class);
                intent.putExtra("id",myList.getId());
                intent.putExtra("workstationNumber",myList.getComputerName());
                view.getContext().startActivity(intent);
            }
        });




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