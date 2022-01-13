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

package com.huawei.hms.ads.ohos.sdk;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.banner.BannerView;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ElementScatter;

import java.util.Locale;

public class BannerAbility extends BaseAbility {

    private static final int REFRESH_TIME = 60;

    private BannerView bannerView;

    private BannerView defaultBannerView;

    private StackLayout adStackLayout;

    private RadioContainer sizeRadioContainer;

    private RadioContainer colorRadioGroup;

    private Component.ClickedListener buttonListener = new Component.ClickedListener() {
        @Override
        public void onClick(Component component) {
            defaultBannerView.setVisibility(Component.INVISIBLE);
            if (bannerView != null) {
                adStackLayout.removeComponent(bannerView);
                bannerView.destroy();
            }

            // Call new BannerView(Context context) to create a BannerView class.
            bannerView = new BannerView(component.getContext());

            // Set an ad slot ID.
            bannerView.setAdId(getString(ResourceTable.String_banner_ad_id));

            // Set the background color and size based on user selection.
            BannerAdSize adSize = getBannerAdSize(sizeRadioContainer.getMarkedButtonId());
            bannerView.setBannerAdSize(adSize);
            Element color = getBannerViewBackground(colorRadioGroup.getMarkedButtonId());
//            bannerView.setBackground(color);
            adStackLayout.addComponent(bannerView);
            bannerView.setAdListener(adListener);
            bannerView.loadAd(new AdParam.Builder().build());
        }
    };

    private AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            // Called when an ad is loaded successfully.
            showToast("Ad loaded.");
        }

        @Override
        public void onAdFailed(int errorCode) {
            // Called when an ad fails to be loaded.
            showToast(String.format(Locale.ROOT, "Ad failed to load with error code %d.", errorCode));
        }

        @Override
        public void onAdOpened() {
            // Called when an ad is opened.
            showToast(String.format("Ad opened "));
        }

        @Override
        public void onAdClicked() {
            // Called when a user taps an ad.
            showToast("Ad clicked");
        }

        @Override
        public void onAdLeave() {
            // Called when a user has left the app.
            showToast("Ad Leave");
        }

        @Override
        public void onAdClosed() {
            // Called when an ad is closed.
            showToast("Ad closed");
        }
    };

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_banner);
        Text bannerTitle = (Text) findComponentById(ResourceTable.Id_text_banner);
        bannerTitle.setText("This is banner ads sample.");
        sizeRadioContainer = (RadioContainer) findComponentById(ResourceTable.Id_size_radioContainer);
        colorRadioGroup = (RadioContainer) findComponentById(ResourceTable.Id_color_radioGroup);

        // Load the default banner ad.
        loadDefaultBannerAd();
        // Set the button for loading an ad.
        Button loadButton = (Button) findComponentById(ResourceTable.Id_refreshButton);
        loadButton.setClickedListener(buttonListener);
        adStackLayout = (StackLayout) findComponentById(ResourceTable.Id_ad_frame);
    }

    /**
     * Load the default banner ad.
     */
    private void loadDefaultBannerAd() {
        // Obtain BannerView based on the configuration in layout/activity_main.xml.
        defaultBannerView = (BannerView) findComponentById(ResourceTable.Id_hw_banner_view);
        defaultBannerView.setAdListener(adListener);
        defaultBannerView.setBannerRefresh(REFRESH_TIME);

        AdParam adParam = new AdParam.Builder().build();
        defaultBannerView.loadAd(adParam);
    }

    private BannerAdSize getBannerAdSize(int markedId) {
        BannerAdSize adSize = null;
        switch (markedId) {
            case 0:
                adSize = BannerAdSize.BANNER_SIZE_320_50;
                break;
            case 1:
                adSize = BannerAdSize.BANNER_SIZE_320_100;
                break;
            case 2:
                adSize = BannerAdSize.BANNER_SIZE_300_250;
                break;
            case 3:
                adSize = BannerAdSize.BANNER_SIZE_SMART;
                break;
            case 4:
                adSize = BannerAdSize.BANNER_SIZE_360_57;
                break;
            case 5:
                adSize = BannerAdSize.BANNER_SIZE_360_144;
                break;
            default:
                break;
        }
        return adSize;
    }

    private Element getBannerViewBackground(int markedId) {
        Element colorlement;
        switch (markedId) {
            case 0:
                colorlement = ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_background_banner_white);
                break;
            case 1:
                colorlement = ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_background_banner_black);
                break;
            case 2:
                colorlement = ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_background_banner_red);
                break;
            case 3:
                colorlement = ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_background_banner_transparent);
                break;
            default:
                colorlement = ElementScatter.getInstance(getContext()).parse(ResourceTable.Graphic_background_banner_transparent);
                break;
        }
        return colorlement;
    }
}
