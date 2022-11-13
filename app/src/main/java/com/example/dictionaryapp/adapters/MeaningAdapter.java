package com.example.dictionaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.models.Definitions;
import com.example.dictionaryapp.models.Meanings;

import java.util.ArrayList;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Meanings> meanings;

    public MeaningAdapter(Context context, ArrayList<Meanings> meanings) {
        this.context = context;
        this.meanings = meanings;
    }

    @NonNull
    @Override
    public MeaningAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meanings_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningAdapter.ViewHolder holder, int position) {
        holder.textViewPartOfSpeech.setText("Part Of Speech: " + meanings.get(position).getPartOfSpeech());
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        System.out.println("Definitions: " + meanings.get(position).getDefinitions().toString());
        DefinitionsAdapter definitionsAdapter = new DefinitionsAdapter(context, (ArrayList<Definitions>) meanings.get(position).getDefinitions());
        holder.recyclerView.setAdapter(definitionsAdapter);
    }

    @Override
    public int getItemCount() {
        return meanings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPartOfSpeech;
        private RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPartOfSpeech = itemView.findViewById(R.id.textview_partOfSpeech);
            recyclerView = itemView.findViewById(R.id.recyclerview_definitions);
        }
    }
}
