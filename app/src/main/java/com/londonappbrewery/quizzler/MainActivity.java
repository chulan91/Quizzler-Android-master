package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mScoreTextView;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;

    int mScore;
    int mIndex;
    int mQuestion;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.00/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);



        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
              updateQuestion();
            }
        });


        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
                updateQuestion();
            }
        });




    }

    private void updateQuestion(){
         if(mIndex < (mQuestionBank.length-1)) {
             mIndex = (mIndex + 1);
             mQuestion = mQuestionBank[mIndex].getQuestionID();
             mQuestionTextView.setText(mQuestion);
             mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
             mScoreTextView.setText("Score "+mScore+"/"+mQuestionBank.length);
         }
         else{
             mProgressBar.setProgress(100);
             AlertDialog.Builder alert = new AlertDialog.Builder(this);
             alert.setTitle("Game Over");
             alert.setCancelable(false);
             alert.setMessage("Your scored "+mScore+" out of "+mQuestionBank.length );
             alert.setPositiveButton("Restart Quiz", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     mScore = 0;
                     mIndex=0;
                     mProgressBar.setProgress(0);
                     mQuestion = mQuestionBank[mIndex].getQuestionID();
                     mQuestionTextView.setText(mQuestion);
                     mScoreTextView.setText("Score "+mScore+"/"+mQuestionBank.length);

                 }
             });
            alert.show();
         }
//         else{
//             mIndex=0;
//             mQuestion = mQuestionBank[mIndex].getQuestionID();
//             mQuestionTextView.setText(mQuestion);
//             mProgressBar.setProgress(0);
//             mScore=0;
//             mScoreTextView.setText("Score "+mScore+"");
//         }
    }

    private void checkAnswer(boolean userSelection){

        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if(userSelection == correctAnswer )
        {
            Toast.makeText(getApplicationContext(),R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore = mScore+1;
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }

    }
}
