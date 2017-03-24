package br.felipeberbert.fallingwords.GameActivity;

import br.felipeberbert.fallingwords.BasePresenter;
import br.felipeberbert.fallingwords.BaseView;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public interface GameContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
