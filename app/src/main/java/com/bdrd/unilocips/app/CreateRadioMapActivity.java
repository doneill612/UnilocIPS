package com.bdrd.unilocips.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bdrd.unilocips.R;

/**
 *
 * @author David O'Neill
 */
public class CreateRadioMapActivity extends AppCompatActivity {

    private Button takeMeasurementButton;
    private Button advancedSettingsButton;
    private Button surveyButton;
    private EditText currentXInput;
    private EditText currentYInput;

    private WifiController wifiController;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.radio_map_creation);
        init();
    }

    private void init() {
        wifiController = new WifiController(this);
        takeMeasurementButton = (Button) findViewById(R.id.take_measurement_button);
        advancedSettingsButton = (Button) findViewById(R.id.advanced_settings_button);
        surveyButton = (Button) findViewById(R.id.survey_button);
        currentXInput = (EditText) findViewById(R.id.x_coord_input);
        currentYInput = (EditText) findViewById(R.id.y_coord_input);
    }

    private void addListeners() {
        surveyButton.setOnClickListener(view -> {
            wifiController.requestSurvey();
        });
    }

    void setTakeMeasurementButtonEnabled(boolean enabled) {
        takeMeasurementButton.setEnabled(enabled);
    }

    void setAdvancedSettingsButtonEnabled(boolean enabled) {
        advancedSettingsButton.setEnabled(enabled);
    }

    void setSurveyButtonEnabled(boolean enabled) {
        surveyButton.setEnabled(enabled);
    }

}
