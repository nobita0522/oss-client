package com.nobitastudio.materialdesign.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nobitastudio.materialdesign.util.LogUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * @author chenxiong
 * @email nobita0522@qq.com
 * @date 2019/01/17 14:27
 * @description 定义极光推送的广播类，用于接收极光推送的消息
 */
public class JPushReceiver extends BroadcastReceiver {
    private JPushPresenter jPushPresenter = null;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext=context;
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        if (jPushPresenter == null) {
            jPushPresenter = new JPushPresenter();
        }
        jPushPresenter.setContext(context);
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            // SDK 向 JPush Server 注册所得到的注册 全局唯一的 ID
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            LogUtil.e(JPushReceiver.class, "-Registration Id : " + regId);
            //send the Registration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            LogUtil.e(JPushReceiver.class, "-推送消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            jPushPresenter.doProcessPushMessage(bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            LogUtil.e(JPushReceiver.class, "-推送通知: " + bundle.getString(JPushInterface.EXTRA_ALERT));
            jPushPresenter.doProcessPusNotify(bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogUtil.e(JPushReceiver.class, "-点击推送的通知: " + bundle.getString(JPushInterface.EXTRA_ALERT));
            jPushPresenter.doOpenPusNotify(bundle);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            LogUtil.e(JPushReceiver.class,"-用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            LogUtil.e(JPushReceiver.class, "-" + intent.getAction() + " connected state change to " + connected);
        } else {
            LogUtil.e(JPushReceiver.class, "-Unhandled intent - " + intent.getAction());
        }
    }
}
