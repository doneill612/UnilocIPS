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
        List<ScanResult> results = scanner.performSurvey();
        if(results != null) {

        }
    }



}
