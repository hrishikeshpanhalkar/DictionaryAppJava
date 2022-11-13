package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.adapters.MeaningAdapter;
import com.example.dictionaryapp.adapters.PhoneticsAdapter;
import com.example.dictionaryapp.models.ApiResponse;
import com.example.dictionaryapp.models.Meanings;
import com.example.dictionaryapp.models.Phonetics;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private TextView textView_word;
    private RecyclerView recyclerView_phonetics, recyclerView_meanings;
    private ProgressDialog progressDialog;
    private PhoneticsAdapter phoneticsAdapter;
    private MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textViewWord);
        recyclerView_meanings = findViewById(R.id.recyclerview_meanings);
        recyclerView_phonetics = findViewById(R.id.recyclerview_phonetics);
        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener, "Hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(ApiResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse == null){
                Toast.makeText(MainActivity.this, "No Data Found!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(ApiResponse apiResponse) {
        textView_word.setText("Word: " + apiResponse.getWord());
        recyclerView_phonetics.setHasFixedSize(true);
        recyclerView_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, (ArrayList<Phonetics>) apiResponse.getPhonetics());
        recyclerView_phonetics.setAdapter(phoneticsAdapter);
        recyclerView_meanings.setHasFixedSize(true);
        recyclerView_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, (ArrayList<Meanings>) apiResponse.getMeanings());
        recyclerView_meanings.setAdapter(meaningAdapter);
    }
}