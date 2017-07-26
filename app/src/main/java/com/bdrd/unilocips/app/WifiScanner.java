package com.bdrd.unilocips.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author David O'Neill
 */
final class WifiScanner {

    final WifiManager wifiManager;
    final Context parentActivity;

    @TargetApi(Build.VERSION_CODES.M)
    WifiScanner(Context activity) {
        this.parentActivity = activity;
        this.wifiManager = (WifiManager) parentActivity.getApplicationContext()
                                                       .getSystemService(Context.WIFI_SERVICE);
    }

    void performScan(int currentX, int currentY) {

    }

    List<ScanResult> performSurvey() {
        Future<?> resultSet = ThreadingManager.getProvider().sumbitTask(wifiManager::getScanResults);
        try {   return (List<ScanResult>) resultSet.get();    }
        catch (InterruptedException | ExecutionException e) {
            Log.d("performSurvey()", "Experienced some trouble... " +
                    e.getCause() + " : printing stack.");
            e.printStackTrace();
        }
        return null;
    }
}
