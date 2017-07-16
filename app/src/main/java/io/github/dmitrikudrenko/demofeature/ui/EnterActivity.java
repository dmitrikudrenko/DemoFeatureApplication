package io.github.dmitrikudrenko.demofeature.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.github.dmitrikudrenko.demofeature.DemoApplication;
import io.github.dmitrikudrenko.demofeature.R;

public class EnterActivity extends AppCompatActivity {
    public static Intent intent(Context context) {
        return new Intent(context, EnterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        findViewById(R.id.enter_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter(true);
            }
        });

        findViewById(R.id.enter_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter(false);
            }
        });
    }

    private void enter(boolean demo) {
        ((DemoApplication) getApplication()).setDemo(demo);
        startActivity(MainActivity.intent(this));
        finish();
    }
}
