package com.huawei.hms.ads.ohos.sdk;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.window.dialog.ToastDialog;

public class BaseAbility extends Ability {

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
    }

    protected void showToast(final String text) {
        getUITaskDispatcher().asyncDispatch(new Runnable() {
            @Override
            public void run() {
                new ToastDialog(getApplicationContext()).setText(text).show();
            }
        });
    }

}
