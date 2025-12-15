package com.polidea.rxandroidble3.internal.scan;

import android.bluetooth.le.ScanResult;

import androidx.annotation.RestrictTo;

import com.polidea.rxandroidble3.scan.IsConnectable;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public interface IsConnectableChecker {
    IsConnectable check(ScanResult scanResult);
}
