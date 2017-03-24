package br.felipeberbert.fallingwords.gameactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.felipeberbert.fallingwords.R;
import br.felipeberbert.fallingwords.menuactivity.MainActivity;
import br.felipeberbert.fallingwords.model.Word;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class GameFragment extends Fragment implements GameContract.View {

    private GameContract.Presenter mPresenter;

    private CardView cardAnswer;
    private TextView tvAnswer, tvQuestion, tvScore;
    private Button btAnswerCorrect, btAnswerWrong;
    private LinearLayout llBackground;


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stopGame();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        tvAnswer = (TextView) rootView.findViewById(R.id.tv_answer);
        tvQuestion = (TextView) rootView.findViewById(R.id.tv_question);
        tvScore = (TextView) rootView.findViewById(R.id.tv_score);
        btAnswerCorrect = (Button) rootView.findViewById(R.id.bt_answer_correct);
        btAnswerWrong = (Button) rootView.findViewById(R.id.bt_answer_wrong);
        llBackground = (LinearLayout) rootView.findViewById(R.id.ll_background);
        cardAnswer = (CardView) rootView.findViewById(R.id.card_answer);
        setupViews();
        mPresenter.runNewGame();
        return rootView;
    }

    private void setupViews() {
        btAnswerWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.registerAnswer(GamePresenter.ANSWER_WRONG);
            }
        });
        btAnswerCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.registerAnswer(GamePresenter.ANSWER_CORRECT);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe(); //Avoid leaks
    }


    @Override
    public void setPresenter(GameContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void showQuestionWord(Word word) {
        tvQuestion.setText(word.getTextEng());
    }

    @Override
    public void showAnswerWord(Word word) {
        tvAnswer.setText(word.getTextSpa());
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        TranslateAnimation anim = new TranslateAnimation(0, 0, -cardAnswer.getHeight(), height);
        anim.setDuration(3000);
        anim.setInterpolator(new AccelerateInterpolator());
        cardAnswer.startAnimation(anim);
    }

    @Override
    public void updateScore(int score) {
        tvScore.setText(getString(R.string.msg_score, score));
    }

    @Override
    public void showMenuScreen() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showPositiveFeedBack() {
//        llBackground.setBackgroundColor(getResources().getColor(R.color.green_light));

    }

    @Override
    public void showNegativeFeedBack() {
//        llBackground.setBackgroundColor(getResources().getColor(R.color.red_light));

    }

    @Override
    public void hideFeedback() {
        llBackground.setBackgroundColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public void enableCorrectAnswerButton(boolean enabled) {
        btAnswerCorrect.setEnabled(enabled);
        if (enabled) {
            btAnswerCorrect.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            btAnswerCorrect.setBackgroundColor(getResources().getColor(R.color.green_light));
        }

    }

    @Override
    public void enableWrongAnswerButton(boolean enabled) {
        btAnswerWrong.setEnabled(enabled);
        if (enabled) {
            btAnswerWrong.setBackgroundColor(getResources().getColor(R.color.red));
        } else {
            btAnswerWrong.setBackgroundColor(getResources().getColor(R.color.red_light));
        }

    }

    @Override
    public void showResultDialog(boolean isHighScore, int score) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(getString(R.string.msg_game_over));
        alertDialog.setCanceledOnTouchOutside(false);
        String message;
        if (isHighScore)
            message = getString(R.string.msg_new_high_score, score);
        else
            message = getString(R.string.msg_score, score);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.msg_play_again),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.runNewGame();
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.msg_leave),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        showMenuScreen();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
