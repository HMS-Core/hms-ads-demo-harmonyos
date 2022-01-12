package com.huawei.hms.ads.ohos.sdk.slice;


import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.window.dialog.ToastDialog;

public class BaseAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    protected void showToast(final String text) {
        getUITaskDispatcher().asyncDispatch(new Runnable() {
            @Override
            public void run() {
                new ToastDialog(getAbility()).setText(text).show();
            }
        });
    }

}
