package com.bdrd.unilocips.app;

import android.content.Context;
import android.net.wifi.ScanResult;

import java.util.List;

/**
 * @author David O'Neill
 */
final class WifiController {

    private final Context activity;
    private final WifiScanner scanner;


    WifiController(Context activity) {
        this.activity = activity;
        this.scanner = new WifiScanner(this.activity);
    }

    void requestSurvey() {
        disableButtons();
        List<ScanResult> results = scanner.performSurvey();
        ((CreateRadioMapActivity) activity).displaySurveyResults(results);
        enableButtons();
    }

    void requestMeasurement() {

    }

    private void enableButtons() {
        ((CreateRadioMapActivity) activity).setAdvancedSettingsButtonEnabled(true);
        ((CreateRadioMapActivity) activity).setTakeMeasurementButtonEnabled(true);
        ((CreateRadioMapActivity) activity).setSurveyButtonEnabled(true);
    }

    private void disableButtons() {
        ((CreateRadioMapActivity) activity).setAdvancedSettingsButtonEnabled(false);
        ((CreateRadioMapActivity) activity).setTakeMeasurementButtonEnabled(false);
        ((CreateRadioMapActivity) activity).setSurveyButtonEnabled(false);
    }

}
