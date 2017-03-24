package br.felipeberbert.fallingwords.MenuActivity;

import android.content.Context;

import br.felipeberbert.fallingwords.Data.DataSource;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class MenuPresenter implements MenuContract.Presenter {
    private final Context mContext;
    private final MenuContract.View mView;
    private final DataSource mDataSource;

    public MenuPresenter(Context context, MenuContract.View view, DataSource dataSource) {
        this.mContext = context;
        this.mView = view;
        this.mDataSource = dataSource;

        mView.setPresenter(this);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void startGame() {
        mView.showGame();
    }

    @Override
    public void loadHighscore() {

    }
}
