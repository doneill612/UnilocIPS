package com.bdrd.unilocips.app;

import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bdrd.unilocips.R;

import java.util.List;

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

    void displaySurveyResults(List<ScanResult> results) {
        final String header = "Network Survey Results";
        String body = "", newLineChar = "\n";
        if(results == null) body = "Nothing to show here.";
        else
        {
            for(ScanResult result : results) {
                body += "Result " + results.indexOf(result)
                        + ":" + newLineChar +
                        "RSSI" + ": " + result.level + newLineChar +
                        "SSID" + ": " + result.SSID + newLineChar +
                        "BSSID" + ": " + result.BSSID + newLineChar +
                        newLineChar;
            }
        }
        AlertDialog.Builder modal = new AlertDialog.Builder(this);
        modal.setTitle(header);
        modal.setMessage(body);
        modal.setPositiveButton("Go back", (dialog, __) -> dialog.dismiss());
        modal.show();
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

    private void openAdvancedSettings() {

    }

    private void init() {
        wifiController = new WifiController(this);
        takeMeasurementButton = (Button) findViewById(R.id.take_measurement_button);
        advancedSettingsButton = (Button) findViewById(R.id.advanced_settings_button);
        surveyButton = (Button) findViewById(R.id.survey_button);
        currentXInput = (EditText) findViewById(R.id.x_coord_input);
        currentYInput = (EditText) findViewById(R.id.y_coord_input);
        addListeners();
    }

    private void addListeners() {
        surveyButton.setOnClickListener(view -> wifiController.requestSurvey());
        takeMeasurementButton.setOnClickListener(view -> wifiController.requestMeasurement());
        advancedSettingsButton.setOnClickListener(view -> openAdvancedSettings());
    }

}
