package com.example.am99m.booksharingapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by am99m on 02-04-2019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomHolder> implements Filterable {

    ArrayList<String> book_name;
    Context context;
    ArrayList<String> tempData;
    StorageReference storageReference= FirebaseStorage.getInstance().getReference("Books");

    public  MyAdapter(Context context,ArrayList<String> book_name)
    {
        this.context=context;
        this.book_name=book_name;
        tempData = new ArrayList<>(book_name);
    }

    @Override
    public MyAdapter.CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);

        CustomHolder customHolder=new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.CustomHolder holder, int position) {

        storageReference.child(book_name.get(position)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.image);
            }
        });
        holder.book_title.setText(book_name.get(position));
    }

    @Override
    public int getItemCount() {
        return book_name.size();
    }
    @Override
    public Filter getFilter() {
        return mFilter;
    }private Filter mFilter = new Filter(){


        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length()==0){
                filteredList.addAll(tempData);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(String item : tempData)
                {
                    if(item.toLowerCase().contains(filterPattern)){

                        filteredList.add(item);
                    }

                }


            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            book_name.clear();
            book_name.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };



    public class CustomHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView book_title;
        public CustomHolder(final View view) {
            super(view);
            image=(ImageView)view.findViewById(R.id.image);
            book_title=(TextView)view.findViewById(R.id.book_title_id);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase sqLiteDatabase = v.getContext().openOrCreateDatabase("rec", v.getContext().MODE_APPEND, null);
                    sqLiteDatabase.execSQL("Create table if not exists book_name(name VARCHAR);");
                    sqLiteDatabase.execSQL("Insert into book_name(name) values('" + book_title.getText() + "');");
                    Intent showData=new Intent(v.getContext(),showData.class);
                    v.getContext().startActivity(showData);
                }
            });
        }
    }
}
