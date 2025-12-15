package com.polidea.rxandroidble3.internal.operations;

import android.bluetooth.BluetoothGatt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import com.polidea.rxandroidble3.PhyPair;
import com.polidea.rxandroidble3.exceptions.BleGattOperationType;
import com.polidea.rxandroidble3.internal.SingleResponseOperation;
import com.polidea.rxandroidble3.internal.connection.ConnectionModule;
import com.polidea.rxandroidble3.internal.connection.RxBleGattCallback;

import javax.inject.Inject;
import javax.inject.Named;
import io.reactivex.rxjava3.core.Single;

@RequiresApi(26 /* Build.VERSION_CODES.O */)
public class PhyReadOperation extends SingleResponseOperation<PhyPair> {

    @Inject
    PhyReadOperation(RxBleGattCallback bleGattCallback, BluetoothGatt bluetoothGatt,
                     @Named(ConnectionModule.OPERATION_TIMEOUT) TimeoutConfiguration timeoutConfiguration) {
        super(bluetoothGatt, bleGattCallback, BleGattOperationType.PHY_READ, timeoutConfiguration);
    }

    @Override
    protected Single<PhyPair> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnPhyRead().firstOrError();
    }

    @Override
    @RequiresPermission("android.permission.BLUETOOTH_CONNECT")
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        bluetoothGatt.readPhy();
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return "PhyReadOperation{" + super.toString() + '}';
    }
}
