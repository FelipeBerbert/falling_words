package br.felipeberbert.fallingwords.GameActivity;

import android.content.Context;

import br.felipeberbert.fallingwords.Data.DataSource;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class GamePresenter implements GameContract.Presenter {
    private final Context mContext;
    private final GameContract.View mView;
    private final DataSource mDataSource;

    public GamePresenter(Context context, GameContract.View view, DataSource dataSource) {
        this.mContext = context;
        this.mView = view;
        this.mDataSource = dataSource;

        mView.setPresenter(this);
    }

    @Override
    public void unsubscribe() {

    }

}
