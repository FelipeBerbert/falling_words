package br.felipeberbert.fallingwords.GameActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.felipeberbert.fallingwords.Data.DataSource;
import br.felipeberbert.fallingwords.R;

/**
 * Created by Felipe Berbert on 24/03/2017.
 */

public class GameActivity extends AppCompatActivity {

    GamePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameFragment fragment;
        if (savedInstanceState == null) {
            fragment = new GameFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
            mPresenter = new GamePresenter(this, fragment, DataSource.getInstance(this));
        }
    }
}
