package com.example.dell.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS = "Mengxl.answer_is";
    public static final String EXTRA_ANSWER_SHOWN = "Mengxl.answer_shown";
    private static final String KEY_CHEATER = "cheater";

    private boolean mAnswerIs;
    private boolean mIsCheater;

    private Button mShownAnswer;
    private TextView mAnswerTextView;

    private void setAnswerShown(){
        if(mAnswerIs == true)
            mAnswerTextView.setText(R.string.true_button);
        else
            mAnswerTextView.setText(R.string.false_button);
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        mIsCheater = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIs = getIntent().getBooleanExtra(EXTRA_ANSWER_IS,false);

        mAnswerTextView = (TextView)findViewById(R.id.show_answe_textView);

        setAnswerShownResult(false);

        mShownAnswer = (Button)findViewById(R.id.show_answe_button);
        mShownAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswerShown();
                setAnswerShownResult(true);
            }
        });

        if(savedInstanceState != null){
            mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER,false);
            if(mIsCheater) {
                setAnswerShown();
                setAnswerShownResult(true);
            }else{
                setAnswerShownResult(false);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);
    }
}
