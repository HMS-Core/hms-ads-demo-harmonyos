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

import prompt from '@system.prompt';
import Log from '../../utils/log';

const TAG = 'AdsBannerExample';

export default {
    data: {
        adId: "testw6vs28auh3",
        adList: [],
        status: 'init',
        bannerSize: "BANNER_SIZE_360_57",
    },
    onInit() {
        Log.i(TAG, 'onInit');
    },
    onShow() {
        Log.i(TAG, 'onShow');
        this.loadAd();
    },
    onBannerSizeChange(value, e) {
        if (value == e.value) {
            this.bannerSize = value;
        }
        Log.i(TAG, "banner size:", value, e.value, this.btnSize);
    },
    requestAdClickHandler() {
        if (this.status === 'loading') {
            prompt.showToast({
                message: 'Ad loading',
                duration: 2000,
            });
            return;
        } else {
            this.loadAd();
        }
    },
    loadAd() {
        const bannerView = this.$child('bannerView');
        if (!this.bannerSize) {
            prompt.showToast({
                message: this.$t('strings.select_size_text'),
                duration: 2000,
            });
            return;
        }
        bannerView.load();
        this.status = 'loading';
    },
    adLoadedHandler() {
        this.status = "init";
        prompt.showToast({
            message: 'Ad loaded',
            duration: 2000,
        });
    },
    adFailedHandler(event) {
        this.status = 'error';
        let errorMsg = event && event.detail || "";
        prompt.showToast({
            message: 'Ad error:' + errorMsg,
            duration: 2000,
        });
    },
    adCloseHandler() {
        this.status = 'init';
        prompt.showToast({
            message: 'Ad closed',
            duration: 2000,
        });
    }
}
