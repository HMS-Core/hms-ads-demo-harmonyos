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

package com.huawei.hms.ads.ohos.sdk.slice;

import com.huawei.hms.ads.ohos.sdk.*;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ListContainer;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends BaseAbilitySlice {

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0X10201, MainAbilitySlice.class.getSimpleName());

    private List<AdFormat> adFormats = new ArrayList<>();

    private ListContainer listContainer;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_slice_main);
        HiLog.debug(LABEL, "onStart");
        initAdItems();

        listContainer = findComponentById(ResourceTable.Id_item_list_container);
        final AdSampleItemProvider provider = new AdSampleItemProvider(getContext(), adFormats);
        listContainer.setItemProvider(provider);
        listContainer.setItemClickedListener((listContainer, component, position, id) -> {
            HiLog.debug(LABEL, "onItemClicked");
            AdFormat adFormat = provider.getItem(position);
            try {
//                present((AbilitySlice) adFormat.getTargetClass().newInstance(), new Intent());
                Intent sintent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withBundleName(getBundleName())
                        .withAbilityName(adFormat.getTargetClass().getName())
                        .build();
                sintent.setOperation(operation);
                startAbility(sintent);
            } catch (Throwable throwable) {
                HiLog.error(LABEL, "start ability error");
            }

        });
    }

    private void initAdItems() {
        adFormats.add(new AdFormat(getString(ResourceTable.String_banner_ad), BannerAbility.class));
        adFormats.add(new AdFormat(getString(ResourceTable.String_native_ad), NativeAbility.class));
        adFormats.add(new AdFormat(getString(ResourceTable.String_reward_ad), RewardAbility.class));
        adFormats.add(new AdFormat(getString(ResourceTable.String_interstitial_ad), InterstitialAbility.class));
        adFormats.add(new AdFormat(getString(ResourceTable.String_instream_ad), InstreamAbility.class));


    }
}
