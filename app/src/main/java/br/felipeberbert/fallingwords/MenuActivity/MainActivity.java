package br.felipeberbert.fallingwords.menuactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.felipeberbert.fallingwords.R;
import br.felipeberbert.fallingwords.data.DataSource;

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
            mPresenter = new MenuPresenter(this, fragment, DataSource.getInstance(this));
        }
    }
}
