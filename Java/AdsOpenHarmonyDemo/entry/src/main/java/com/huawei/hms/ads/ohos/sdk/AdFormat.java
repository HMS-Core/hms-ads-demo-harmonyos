package com.huawei.hms.ads.ohos.sdk;

public class AdFormat {
    /**
     * Item name
     */
    private String title;

    /**
     * Target class
     */
    private Class targetClass;

    public AdFormat(String title, Class<?> targetClass) {
        this.title = title;
        this.targetClass = targetClass;
    }

    public String getTitle() {
        return title;
    }

    public Class getTargetClass() {
        return targetClass;
    }
}
