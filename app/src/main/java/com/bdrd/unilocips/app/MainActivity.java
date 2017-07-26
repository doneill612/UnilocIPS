package com.bdrd.unilocips.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.bdrd.unilocips.R;

/**
 *
 * @author David O'Neill
 */
public class MainActivity extends AppCompatActivity {

    private Button createRadioMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(!ThreadingManager.isReady())
            ThreadingManager.init();
    }

    private void init() {
        createRadioMapButton = (Button) findViewById(R.id.create_radio_map_button);
        createRadioMapButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateRadioMapActivity.class);
            startActivity(intent);
        });
    }


}
