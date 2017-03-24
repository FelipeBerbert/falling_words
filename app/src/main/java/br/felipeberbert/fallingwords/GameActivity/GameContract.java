package br.felipeberbert.fallingwords.gameactivity;

import br.felipeberbert.fallingwords.BasePresenter;
import br.felipeberbert.fallingwords.BaseView;
import br.felipeberbert.fallingwords.model.Word;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public interface GameContract {

    interface Presenter extends BasePresenter {
        void runNewGame();
        void loadNextWord();
        void registerAnswer(int answer);
    }

    interface View extends BaseView<Presenter> {
        void showQuestionWord(Word word);
        void showAnswerWord(Word word);
        void updateScore(int score);
        void showMenuScreen();
        void showPositiveFeedBack();
        void showNegativeFeedBack();
        void hideFeedback();
        void enableCorrectAnswerButton(boolean enabled);
        void enableWrongAnswerButton(boolean enabled);
        void showResultDialog(boolean isHighScore, int score);
    }
}
