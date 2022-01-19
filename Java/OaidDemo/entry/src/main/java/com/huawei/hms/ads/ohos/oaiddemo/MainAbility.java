package com.huawei.hms.ads.ohos.oaiddemo;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;
import ohos.agp.utils.TextTool;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbility extends Ability implements OaidCallback {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0X00201, "MainAbility");

    Text mOaid;

    Text mDAP;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_main);
        initView();
    }

    protected void initView() {
        // Create the "ad_id" TextView, which tries to show the obtained "OAID".
        mOaid =  (Text) findComponentById(ResourceTable.Id_oaid);
        // Create the "disable_ad_id" TextView, which tries to show the obtained "Disable Personalized Ads" Switch
        mDAP =  (Text) findComponentById(ResourceTable.Id_dap);
        getIdentifierThread.start();
    }

    /**
     * Obtains device ID information from a non-UI thread.
     */
    private Thread getIdentifierThread = new Thread(() -> getOaid());

    private void getOaid() {
        //  Get OAID by sdk mode.
        OaidSdkUtil.getOaid(this, this);
    }

    /**
     * Update the device ID information from a UI thread.
     */
    private void updateAdIdInfo(final String oaid, final boolean isLimitAdTrackingEnabled) {
        getUITaskDispatcher().asyncDispatch(new Runnable() {
            @Override
            public void run() {
                if (!TextTool.isNullOrEmpty(oaid)) {
                    mOaid.setText(oaid);
                }
                mDAP.setText(String.valueOf(isLimitAdTrackingEnabled));
            }
        });
    }

    @Override
    public void onSuccess(String oaid, boolean isOaidTrackLimited) {
        HiLog.info(LABEL, "oiad=" + oaid + ", isLimitAdTrackingEnabled=" + isOaidTrackLimited);
        updateAdIdInfo(oaid, isOaidTrackLimited);
    }

    @Override
    public void onFail(String errMsg) {
        HiLog.error(LABEL, "getOaid Fail: " + errMsg);
    }
}
