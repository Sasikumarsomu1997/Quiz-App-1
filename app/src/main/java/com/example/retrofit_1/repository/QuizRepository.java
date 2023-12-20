package com.example.retrofit_1.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofit_1.model.QuestionList;
import com.example.retrofit_1.service.QuestionApi;
import com.example.retrofit_1.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {

    QuestionApi questionApi;

    public QuizRepository() {

        this.questionApi = new RetrofitInstance().getRetrofitInstance();
    }

    public LiveData<QuestionList> getQuestionFromApi() {
        MutableLiveData<QuestionList> data = new MutableLiveData<>();

        Call<QuestionList> response = questionApi.getQuestions();

        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {


                Log.d("CheckData", String.valueOf(response));
                 QuestionList questionList = response.body();
                data.setValue(questionList);


            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {
                Log.d("Checkdatanull",String.valueOf(t));

            }
        });

        return data;

    }

}
