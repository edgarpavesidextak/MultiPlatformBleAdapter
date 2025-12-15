package com.polidea.rxandroidble3.internal.operations;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.polidea.rxandroidble3.PhyPair;
import com.polidea.rxandroidble3.RxBlePhyOption;
import com.polidea.rxandroidble3.exceptions.BleGattOperationType;
import com.polidea.rxandroidble3.internal.RxBlePhyImpl;
import com.polidea.rxandroidble3.internal.SingleResponseOperation;
import com.polidea.rxandroidble3.internal.connection.RxBleGattCallback;

import java.util.Set;

import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

@RequiresApi(26 /* Build.VERSION_CODES.O */)
public class PhyUpdateOperation extends SingleResponseOperation<PhyPair> {

    Set<RxBlePhyImpl> txPhy;
    Set<RxBlePhyImpl> rxPhy;
    RxBlePhyOption phyOptions;

    @Inject
    PhyUpdateOperation(
            RxBleGattCallback rxBleGattCallback,
            BluetoothGatt bluetoothGatt,
            TimeoutConfiguration timeoutConfiguration,
            Set<RxBlePhyImpl> txPhy, Set<RxBlePhyImpl> rxPhy, RxBlePhyOption phyOptions) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.PHY_UPDATE, timeoutConfiguration);
        this.txPhy = txPhy;
        this.rxPhy = rxPhy;
        this.phyOptions = phyOptions;
    }

    @Override
    protected Single<PhyPair> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnPhyUpdate().firstOrError();
    }

    @Override
    @SuppressLint("MissingPermission")
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        bluetoothGatt.setPreferredPhy(
                RxBlePhyImpl.enumSetToValuesMask(txPhy),
                RxBlePhyImpl.enumSetToValuesMask(rxPhy),
                phyOptions.getValue()
        );
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return "PhyUpdateOperation{"
                + super.toString()
                + ", txPhy=" + txPhy
                + ", rxPhy=" + rxPhy
                + ", phyOptions=" + phyOptions
                + '}';
    }
}
