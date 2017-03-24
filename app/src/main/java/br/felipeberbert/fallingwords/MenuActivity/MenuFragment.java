package br.felipeberbert.fallingwords.menuactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.felipeberbert.fallingwords.R;
import br.felipeberbert.fallingwords.gameactivity.GameActivity;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class MenuFragment extends Fragment implements MenuContract.View {

    private MenuContract.Presenter mPresenter;

    private Button mBtStartGame;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        mBtStartGame = (Button) rootView.findViewById(R.id.bt_start_game);
        setupViews();
        return rootView;
    }

    private void setupViews() {
        mBtStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startGame();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe(); //Avoid leaks
    }

    @Override
    public void showHighscore() {

    }

    @Override
    public void showGame() {
        Intent intent = new Intent(getContext(), GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MenuContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
