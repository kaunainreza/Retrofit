package com.example.retrofit.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.R;
import com.example.retrofit.repositories.DatabaseRepo;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<Property> listdata;
    private Context context;
    private DatabaseRepo databaseRepo;

    // RecyclerView recyclerView;
    public MyListAdapter(List<Property> listdata, Context context, DatabaseRepo databaseRepo) {
        this.listdata = listdata;
        this.context = context;
        this.databaseRepo = databaseRepo;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Property myListData = listdata.get(position);
        holder.textView.setText(listdata.get(position).getId());
        holder.textViewP.setText(listdata.get(position).getPrice().toString());

        Boolean isSelected = listdata.get(position).getIsSelected();
        if (isSelected == null) isSelected = false;
        holder.checkBox.setChecked(isSelected);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListData.setIsSelected(holder.checkBox.isChecked());
                databaseRepo.updateIsSelected( myListData.getId(),holder.checkBox.isChecked());
            }
        });


        String url = listdata.get(position).getImgSrc();
        url = url.replace("http:", "https:");
        Glide.with(context)
                .load(url)
                .into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + myListData.getPrice(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView textViewP;
        public CheckBox checkBox;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textViewId);
            this.textViewP = (TextView) itemView.findViewById(R.id.textViewP);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.checkboxSelect);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}