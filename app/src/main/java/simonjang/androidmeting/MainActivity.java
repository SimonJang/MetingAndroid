package simonjang.androidmeting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs = null;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("simonjang.androidmeting", MODE_PRIVATE);

        if(prefs.getBoolean("dataInserted", true)) {
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            setupWelcome();
        }

        else {
            startOverview();
        }
    }

    private void setupWelcome() {

        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbarLayout.setTitle("Meting Applicatie");
                    toolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
                    isShow = true;
                } else if(isShow) {
                    toolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    private void startOverview() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
