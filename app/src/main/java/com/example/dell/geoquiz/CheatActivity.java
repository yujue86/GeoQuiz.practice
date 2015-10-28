package com.example.dell.geoquiz;

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

    private boolean mAnswerIs;

    private Button mShowAnswer;
    private TextView mAnswerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIs = getIntent().getBooleanExtra(EXTRA_ANSWER_IS,false);

        mAnswerTextView = (TextView)findViewById(R.id.show_answe_textView);

        mShowAnswer = (Button)findViewById(R.id.show_answe_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIs == true)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
            }
        });
    }

}
