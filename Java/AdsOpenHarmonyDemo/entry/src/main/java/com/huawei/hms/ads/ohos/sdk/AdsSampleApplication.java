package com.huawei.hms.ads.ohos.sdk;

import com.huawei.hms.ads.HwAds;
import ohos.aafwk.ability.AbilityPackage;

public class AdsSampleApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        // Initialize the HUAWEI Ads SDK.
        HwAds.init(this);
    }
}
