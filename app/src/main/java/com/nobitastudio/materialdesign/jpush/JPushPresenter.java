package com.nobitastudio.materialdesign.jpush;

import android.content.Context;
import android.os.Bundle;

import com.nobitastudio.materialdesign.util.LogUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * @author chenxiong
 * @email nobita0522@qq.com
 * @date 2019/01/17 14:27
 * @description 极光推送消息处理的具体类，该类实现JpushContract接口,用户需要处理的极光消息均在此类中做具体处理
 */
public class JPushPresenter implements JPushContract{
    private Context mContext;

    public JPushPresenter() {
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override
    public void doProcessPushMessage(Bundle bundle) {
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);

        LogUtil.e(JPushPresenter.class,"=====doProcessPushMessage=======");

    }

    @Override
    public void doProcessPusNotify(Bundle bundle) {
        LogUtil.e(JPushPresenter.class,"=====doProcessPusNotify=======");
    }

    @Override
    public void doOpenPusNotify(Bundle bundle) {
        LogUtil.e(JPushPresenter.class,"=====doOpenPusNotify=======");
    }
}
