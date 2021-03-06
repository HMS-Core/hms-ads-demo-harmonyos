/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
     http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.huawei.hms.ads.ohos.oaiddemo;

import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.io.IOException;

public class OaidSdkUtil {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0X00201, "OaidSdkUtil");

    public static void getOaid(Context context, OaidCallback callback) {
        if (null == context || null == callback) {
            HiLog.error(LABEL, "invalid input param");
            return;
        }
       try {
            //Get advertising id information. Do not call this method in the main thread.
            AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (null != info) {
                callback.onSuccess(info.getId(), info.isLimitAdTrackingEnabled());
            } else {
                callback.onFail("oaid is null");
            }
        } catch (IOException e) {
            HiLog.error(LABEL, "getAdvertisingIdInfo IOException");
            callback.onFail("getAdvertisingIdInfo IOException");
        }
    }
}
