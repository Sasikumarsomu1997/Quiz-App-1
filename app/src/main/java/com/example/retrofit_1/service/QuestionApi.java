package com.example.retrofit_1.service;

import com.example.retrofit_1.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionApi {

    @GET("myquizapi.php")
    Call<QuestionList> getQuestions();
}
