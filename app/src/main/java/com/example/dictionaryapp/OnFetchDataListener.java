package com.example.dictionaryapp;

import com.example.dictionaryapp.models.ApiResponse;

public interface OnFetchDataListener {
    void onFetchData(ApiResponse apiResponse, String message);

    void onError(String message);
}
