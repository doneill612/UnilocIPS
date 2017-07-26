package com.bdrd.unilocips.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Button;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 * @author David O'Neill
 */

final class WifiScanner {

    final WifiManager wifiManager;
    final CreateRadioMapActivity parentActivity;

    @TargetApi(Build.VERSION_CODES.M)
    WifiScanner(CreateRadioMapActivity activity) {
        this.parentActivity = activity;
        this.wifiManager = this.parentActivity.getSystemService(WifiManager.class);
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
