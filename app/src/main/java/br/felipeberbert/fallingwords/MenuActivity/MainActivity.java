package br.felipeberbert.fallingwords.MenuActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.felipeberbert.fallingwords.Data.DataSource;
import br.felipeberbert.fallingwords.R;

public class MainActivity extends AppCompatActivity {

    MenuPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuFragment fragment;
        if (savedInstanceState == null) {
            fragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
            mPresenter = new MenuPresenter(this, fragment, DataSource.getInstance());
        }
    }
}
