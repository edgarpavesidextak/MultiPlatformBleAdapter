package com.polidea.rxandroidble3.internal;

import static com.polidea.rxandroidble3.internal.DeviceModule.MAC_ADDRESS;

import com.polidea.rxandroidble3.RxBleDevice;

import dagger.BindsInstance;
import dagger.Subcomponent;
import javax.inject.Named;

@DeviceScope
@Subcomponent(modules = {DeviceModule.class})
public interface DeviceComponent {

    @Subcomponent.Builder
    interface Builder {
        DeviceComponent build();

        @BindsInstance
        Builder macAddress(@Named(MAC_ADDRESS) String deviceMacAddress);
    }

    @DeviceScope
    RxBleDevice provideDevice();
}
