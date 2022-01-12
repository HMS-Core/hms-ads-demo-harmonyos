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

import router from '@system.router';
import Log from '../../utils/log';

export default {
    props: ['ad'],
    data: {
        next: "",
        ok: false
    },
    onInit() {
        this.next = this.$t('strings.next');
    },

    onShow() {
        Log.i("JSLog:", "onShow");
    },

    onClickNext() {
        router.push({
            uri: this.ad.route,
        });
    },
}
