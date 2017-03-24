package br.felipeberbert.fallingwords.gameactivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import br.felipeberbert.fallingwords.R;
import br.felipeberbert.fallingwords.data.DataSource;
import br.felipeberbert.fallingwords.model.Word;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class GamePresenter implements GameContract.Presenter {

    public final static int NO_ANSWER = 0;
    public final static int ANSWER_CORRECT = 1;
    public final static int ANSWER_WRONG = 2;

    private final static int TOTAL_STAGES = 4;

    private final Context mContext;
    private final GameContract.View mView;
    private final DataSource mDataSource;
    private int mGameScore;
    private int mCurrentAnswer;
    private int currentWordIndex;

    public GamePresenter(Context context, GameContract.View view, DataSource dataSource) {
        this.mContext = context;
        this.mView = view;
        this.mDataSource = dataSource;

        mView.setPresenter(this);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void runNewGame() {
        mGameScore = 0;
        currentWordIndex = 0;
        Collections.shuffle(mDataSource.getmWordList());
        loadNextWord();
    }

    @Override
    public void loadNextWord() {
        mView.enableCorrectAnswerButton(false);
        mView.enableWrongAnswerButton(false);
        if (currentWordIndex < TOTAL_STAGES) {
            mCurrentAnswer = NO_ANSWER;
            final Word questionWord = mDataSource.getmWordList().get(currentWordIndex++);
            final Word answerWord;
            Random rand = new Random();
            boolean displayCorrectWord = rand.nextBoolean(); // Random to decide if the falling card will have the correct answer
            if (displayCorrectWord) {
                answerWord = questionWord;
            } else {
                answerWord = mDataSource.getmWordList().get(currentWordIndex + TOTAL_STAGES); // get a wrong answer that is out of this round's range, it will be random because the list was shuffled
            }
            mView.showQuestionWord(questionWord);
            mView.showAnswerWord(answerWord);
            mView.hideFeedback();
            mView.enableCorrectAnswerButton(true);
            mView.enableWrongAnswerButton(true);
            Observable.timer(6, TimeUnit.SECONDS, Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            verifyAnswer(questionWord, answerWord);
                        }
                    });
        } else {
            finishGame();
        }
    }

    private void verifyAnswer(Word question, Word answer) {
        boolean wasCorrect = question.equals(answer);
        if (mCurrentAnswer == NO_ANSWER) {
            // If no answer was given, the score stays the same
        } else if ((wasCorrect && mCurrentAnswer == ANSWER_CORRECT) || (!wasCorrect && mCurrentAnswer == ANSWER_WRONG)) {
            mView.updateScore(++mGameScore);
            mView.showPositiveFeedBack();
        } else {
            mView.updateScore(--mGameScore);
            mView.showNegativeFeedBack();
        }
        mView.updateScore(mGameScore);
        loadNextWord();
    }

    private void finishGame() {
        mView.showResultDialog(saveHighScore(), mGameScore);
    }


    private boolean saveHighScore() {
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.app_name), Context.MODE_PRIVATE);
        int previousHighScore = sharedPref.getInt(mContext.getString(R.string.pref_high_score), 0);
        if (mGameScore > previousHighScore) {
            sharedPref.edit().putInt(mContext.getString(R.string.pref_high_score), mGameScore).apply();
            return true;
        }
        return false;
    }

    @Override
    public void registerAnswer(int answer) {
        mCurrentAnswer = answer;
        mView.enableWrongAnswerButton(answer == ANSWER_CORRECT);
        mView.enableCorrectAnswerButton(answer == ANSWER_WRONG);
    }
}
