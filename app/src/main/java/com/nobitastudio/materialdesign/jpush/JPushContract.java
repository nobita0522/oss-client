package com.nobitastudio.materialdesign.jpush;

import android.os.Bundle;

/**
 * @author chenxiong
 * @email nobita0522@qq.com
 * @date 2019/01/17 14:26
 * @description 处理极光推送消息的接口
 */
public interface JPushContract {
    void doProcessPushMessage(Bundle bundle);

    void doProcessPusNotify(Bundle bundle);

    void doOpenPusNotify(Bundle bundle);
}
