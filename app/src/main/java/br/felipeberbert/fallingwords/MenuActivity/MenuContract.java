package br.felipeberbert.fallingwords.menuactivity;

import br.felipeberbert.fallingwords.BasePresenter;
import br.felipeberbert.fallingwords.BaseView;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public interface MenuContract {

    interface Presenter extends BasePresenter {
        void startGame();
        void loadHighscore();
    }

    interface View extends BaseView<Presenter> {
        void showHighscore();
        void showGame();
    }
}
