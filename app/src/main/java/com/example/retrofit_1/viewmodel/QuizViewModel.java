package com.example.retrofit_1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit_1.model.QuestionList;
import com.example.retrofit_1.repository.QuizRepository;


public class QuizViewModel extends ViewModel {
    QuizRepository repository = new QuizRepository();


    LiveData<QuestionList> questionListLiveData;

    public QuizViewModel(){
        questionListLiveData = repository.getQuestionFromApi();
    }

    public LiveData<QuestionList> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
