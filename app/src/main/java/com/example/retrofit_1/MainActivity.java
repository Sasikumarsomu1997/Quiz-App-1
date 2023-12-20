package com.example.retrofit_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.retrofit_1.databinding.ActivityMainBinding;
import com.example.retrofit_1.model.Question;
import com.example.retrofit_1.model.QuestionList;
import com.example.retrofit_1.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    QuizViewModel quizViewModel;

    List<Question> quizquestionList;

    static int result = 0;
    static int totalQuestions = 0;

    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);


        result = 0;

        totalQuestions = 0;

        DisplayFirstQuestion();
        activityMainBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplaynextQuestion();
            }
        });
    }



    private void DisplayFirstQuestion() {

        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {

                quizquestionList = questions;

                activityMainBinding.txtQuestion.setText("Question 1" + questions.get(0).getQuestion());
                activityMainBinding.radio1.setText(questions.get(0).getOption1());
                activityMainBinding.radio2.setText(questions.get(0).getOption2());
                activityMainBinding.radio3.setText(questions.get(0).getOption3());
                activityMainBinding.radio4.setText(questions.get(0).getOption4());

            }
        });


    }
    private void DisplaynextQuestion() {
        if (activityMainBinding.btnNext.getText().equals("FINISH")){
            Intent intent = new Intent(MainActivity.this,ResultActivity.class);
            startActivity(intent);
            finish();
        }
        int selectionOption = activityMainBinding.radioGroup.getCheckedRadioButtonId();

        if (selectionOption != -1){
            RadioButton radioButton = findViewById(selectionOption);

            if ((quizquestionList.size()-i)>0){
                totalQuestions = quizquestionList.size();

                if (radioButton.getText().toString().equals((quizquestionList).get(i).getCorrectOption())){
                    result ++;

                    activityMainBinding.txtResult.setText("Correct Ans :" + result);
                }

                if (i == 0){
                    i++;
                }
                activityMainBinding.txtQuestion.setText("Question" +(i+1)+ ":" + quizquestionList.get(i).getQuestion());
                activityMainBinding.radio1.setText(quizquestionList.get(i).getOption1());
                activityMainBinding.radio2.setText(quizquestionList.get(i).getOption2());
                activityMainBinding.radio3.setText(quizquestionList.get(i).getOption3());
                activityMainBinding.radio4.setText(quizquestionList.get(i).getOption4());

                if (i == (quizquestionList.size()-1)){

                    activityMainBinding.btnNext.setText("FINISH");
                }
                activityMainBinding.radioGroup.clearCheck();

                i++;

            }
            else {
                Toast.makeText(this, "Please Select The Option", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
