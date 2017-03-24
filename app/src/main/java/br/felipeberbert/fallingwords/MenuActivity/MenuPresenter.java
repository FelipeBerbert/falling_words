package br.felipeberbert.fallingwords.menuactivity;

import android.content.Context;
import android.content.SharedPreferences;

import br.felipeberbert.fallingwords.R;
import br.felipeberbert.fallingwords.data.DataSource;

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
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.app_name), Context.MODE_PRIVATE);
        mView.showHighscore(sharedPref.getInt(mContext.getString(R.string.pref_high_score), 0));
    }
}
