package com.example.dell.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;


public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_ISCHEATER = "ischeater";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;

    private  TrueFalse[] mQuesstionBank = new TrueFalse[]{
            new TrueFalse(R.string.first_question, true),
            new TrueFalse(R.string.second_question, false),
            new TrueFalse(R.string.third_question, true),
    };

    private boolean mIsCheater[] = new boolean[]{
            false, false, false,};

    private void updateQuestion(){
        int question = mQuesstionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressed) {
        boolean answerIsTrue = mQuesstionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;
        if(mIsCheater[mCurrentIndex]){
            messageResId = R.string.judgment_toast;
        }else {
            if (answerIsTrue == userPressed) {
                messageResId = R.string.true_toast;
            } else {
                messageResId = R.string.false_toast;
            }
        }
        makeText(QuizActivity.this,
                messageResId,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIs = mQuesstionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS, answerIs);
                startActivityForResult(i, 0);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuesstionBank.length;
                updateQuestion();
            }
        });

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBooleanArray(KEY_ISCHEATER);
        }
    updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBooleanArray(KEY_ISCHEATER,mIsCheater);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data == null){
            return;
        }
        if(!mIsCheater[mCurrentIndex])
            mIsCheater[mCurrentIndex] = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}
