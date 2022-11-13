package com.example.dictionaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.models.Definitions;

import java.util.ArrayList;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Definitions> definitions;

    public DefinitionsAdapter(Context context, ArrayList<Definitions> definitions) {
        this.context = context;
        this.definitions = definitions;
    }

    @NonNull
    @Override
    public DefinitionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.definations_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionsAdapter.ViewHolder holder, int position) {
        holder.textViewDefinition.setText(definitions.get(position).getDefinition());
        if(!definitions.get(position).getExample().equals("")){
            holder.textViewExample.setText(definitions.get(position).getExample());
        }
        if(!definitions.get(position).getSynonyms().isEmpty()){
            StringBuilder synonyms = new StringBuilder();
            synonyms.append(definitions.get(position).getSynonyms());
            holder.textViewSynonyms.setText(synonyms);
            holder.textViewSynonyms.setSelected(true);
        }
        if(!definitions.get(position).getAntonyms().isEmpty()){
            StringBuilder antonyms = new StringBuilder();
            antonyms.append(definitions.get(position).getAntonyms());
            holder.textViewAntonyms.setText(antonyms);
            holder.textViewAntonyms.setSelected(true);
        }

    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDefinition, textViewExample, textViewSynonyms, textViewAntonyms;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAntonyms = itemView.findViewById(R.id.textview_antonyms);
            textViewSynonyms = itemView.findViewById(R.id.textview_synonyms);
            textViewDefinition = itemView.findViewById(R.id.textview_examples);
            textViewDefinition = itemView.findViewById(R.id.textview_definitions);
        }
    }
}
