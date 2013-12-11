package org.strofcon.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    private static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "org.strofcon.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "org.strofcon.android.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;
    private boolean mAnswerHasBeenShown;

    private TextView mAnswerTextView;

    private Button mShowAnswer;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        mAnswerHasBeenShown = isAnswerShown;
        Log.i(TAG, "SETANSWERMETHOD - mAnswerHasBeenShown: "+mAnswerHasBeenShown);
        data.putExtra(EXTRA_ANSWER_SHOWN, mAnswerHasBeenShown);
        setResult(RESULT_OK, data);
    }

    private void displayAnswer(boolean answerIsTrue) {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_cheat);

        // Answer not shown until user hits "Show answer" button
        setAnswerShownResult(false);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAnswer(mAnswerIsTrue);
                setAnswerShownResult(true);
            }
        });

        if (savedInstanceState != null) {
            mAnswerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE);
            mAnswerHasBeenShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);
            Log.i(TAG, "mAnswerHasBeenShown: "+mAnswerHasBeenShown);
            Log.i(TAG, "mAnswerIsTrue: "+mAnswerIsTrue);

            if (mAnswerHasBeenShown) {
                displayAnswer(mAnswerIsTrue);
                setAnswerShownResult(mAnswerHasBeenShown);
            }

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        Log.i(TAG, "mAnswerHasBeenShown: "+mAnswerHasBeenShown);
        Log.i(TAG, "mAnswerIsTrue: "+mAnswerIsTrue);
        savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mAnswerHasBeenShown);
        savedInstanceState.putBoolean(EXTRA_ANSWER_IS_TRUE, mAnswerIsTrue);
    }

}
