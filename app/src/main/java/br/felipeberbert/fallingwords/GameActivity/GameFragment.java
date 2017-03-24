package br.felipeberbert.fallingwords.GameActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.felipeberbert.fallingwords.R;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class GameFragment extends Fragment implements GameContract.View {

    private GameContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        setupViews();
        return rootView;
    }

    private void setupViews() {
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
}
