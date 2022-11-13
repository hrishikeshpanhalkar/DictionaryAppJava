package com.example.dictionaryapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.models.Phonetics;

import java.util.ArrayList;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Phonetics> phonetics;

    public PhoneticsAdapter(Context context, ArrayList<Phonetics> phonetics) {
        this.context = context;
        this.phonetics = phonetics;
    }

    @NonNull
    @Override
    public PhoneticsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phonetic_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textviewPhonetics.setText(phonetics.get(position).getText());
        if(phonetics.get(position).getAudio().equals("")){
            holder.imageButton.setVisibility(View.INVISIBLE);
        }else{
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer player = new MediaPlayer();
                    try{
                        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        player.setDataSource(phonetics.get(position).getAudio());
                        player.prepare();
                        player.start();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return phonetics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textviewPhonetics;
        private ImageButton imageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewPhonetics = itemView.findViewById(R.id.textview_phonetic);
            imageButton = itemView.findViewById(R.id.imageButton_audio);
        }
    }
}
