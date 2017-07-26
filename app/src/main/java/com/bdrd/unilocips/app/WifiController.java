package com.bdrd.unilocips.app;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

/**
 * @author David O'Neill
 */
final class WifiController {

    private final CreateRadioMapActivity activity;
    private final WifiScanner scanner;


    WifiController(CreateRadioMapActivity activity) {
        this.activity = activity;
        this.scanner = new WifiScanner(this.activity);
    }

    void requestSurvey() {
        disableButtons();
        List<ScanResult> results = scanner.performSurvey();
        activity.displaySurveyResults(results);
        enableButtons();
    }

    void requestMeasurement() {

    }

    private void enableButtons() {
        activity.setAdvancedSettingsButtonEnabled(true);
        activity.setTakeMeasurementButtonEnabled(true);
        activity.setSurveyButtonEnabled(true);
    }

    private void disableButtons() {
        activity.setAdvancedSettingsButtonEnabled(false);
        activity.setTakeMeasurementButtonEnabled(false);
        activity.setSurveyButtonEnabled(false);
    }



}
