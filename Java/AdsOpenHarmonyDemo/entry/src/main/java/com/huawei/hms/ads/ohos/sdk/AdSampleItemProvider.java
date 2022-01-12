package com.huawei.hms.ads.ohos.sdk;

import ohos.agp.components.BaseItemProvider;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.app.Context;

import java.util.List;

public class AdSampleItemProvider extends BaseItemProvider {

    private Context mContext;

    private List<AdFormat> mFormatList;

    public AdSampleItemProvider(Context context, List<AdFormat> formatList) {
        this.mFormatList = formatList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mFormatList.size();
    }

    @Override
    public AdFormat getItem(int index) {
        return mFormatList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public Component getComponent(int index, Component component, ComponentContainer componentContainer) {
        AdFormat adFormat = getItem(index);
        component = LayoutScatter.getInstance(mContext).parse(ResourceTable.Layout_sample_list_item, null, false);
        Text title = (Text) component.findComponentById(ResourceTable.Id_ad_btn);
        title.setText(adFormat.getTitle());
        return component;
    }
}
