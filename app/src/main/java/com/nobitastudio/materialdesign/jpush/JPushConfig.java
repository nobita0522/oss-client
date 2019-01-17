package com.nobitastudio.materialdesign.jpush;

import android.content.Context;

import com.nobitastudio.materialdesign.util.ContextUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * @author chenxiong
 * @email nobita0522@qq.com
 * @date 2019/01/17 14:26
 * @description 极光推送方法类，外部调用所有有关极光推送的方法，都通过此类
 */
public class JPushConfig {
    private Context mContext;

    private JPushConfig() {
        mContext = ContextUtil.getContext();
    }

    private static class Holder {
        private static JPushConfig instance = new JPushConfig();
    }

    public static JPushConfig getInstance() {
        return Holder.instance;
    }

    /**
     * 初始化极光,在Application的oncreate()方法中调用
     **/
    public void initJpush() {
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(mContext);
    }

    /**
     * 添加tag
     **/
    public void addTag(String tag) {
        handleTag(tag, JPushHelper.ACTION_ADD);
    }

    /**
     * 设置tag
     **/
    public void setTag(String tag) {
        handleTag(tag, JPushHelper.ACTION_SET);
    }

    /**
     * 删除tag
     **/
    public void deleteTag(String tag) {
        handleTag(tag, JPushHelper.ACTION_DELETE);
    }

    /**
     * 获取所有tag
     **/
    public void getAllTags() {
        handleTag(null, JPushHelper.ACTION_GET);
    }

    /**
     * 清除所有tag
     **/
    public void cleanAllTags() {
        handleTag(null, JPushHelper.ACTION_CLEAN);
    }

    /**
     * 检测tag
     **/
    public void checkTag(Set<String> tags) {
        if (tags == null) {
            return;
        }
        TagAliasBean tagAliasBean = new TagAliasBean();
        tagAliasBean.setAction(JPushHelper.ACTION_CHECK);
        tagAliasBean.setAlias(null);
        tagAliasBean.setTags(tags);
        tagAliasBean.setAliasAction(false);
        JPushHelper.mSequence++;
        JPushHelper.getInstance().handleAction(JPushHelper.mSequence, tagAliasBean);
    }

    /**
     * 设置Alias
     **/
    public void setAlias(String alias) {
        if (StringUtils.isNotEmpty(alias)) {
            handleAlias(alias, JPushHelper.ACTION_SET);
        }
    }

    /**
     * 获取alias
     **/
    public void getAlias() {
        handleAlias(null, JPushHelper.ACTION_GET);
    }

    /**
     * 删除alias
     **/
    public void deleteAlias() {
        handleAlias(null, JPushHelper.ACTION_DELETE);
    }


    private void handleTag(String tag, int action) {
        Set<String> tags = new LinkedHashSet<>();
        if (StringUtils.isNotEmpty(tag)) {
            tags.add(tag);
        }
        TagAliasBean tagAliasBean = new TagAliasBean();
        tagAliasBean.setAction(action);
        tagAliasBean.setAlias(null);
        tagAliasBean.setTags(tags);
        tagAliasBean.setAliasAction(false);
        JPushHelper.mSequence++;
        JPushHelper.getInstance().handleAction(JPushHelper.mSequence, tagAliasBean);
    }

    private void handleAlias(String alias, int action) {
        TagAliasBean tagAliasBean = new TagAliasBean();
        tagAliasBean.setAction(action);
        tagAliasBean.setAlias(alias);
        tagAliasBean.setTags(null);
        tagAliasBean.setAliasAction(true);
        JPushHelper.mSequence++;
        JPushHelper.getInstance().handleAction(JPushHelper.mSequence, tagAliasBean);
    }

    /**
     * 停止极光服务
     **/
    public void stopJpush() {
        if (!JPushInterface.isPushStopped(mContext)) {
            JPushInterface.stopPush(mContext);
        }
    }

    /**
     * 恢复极光推送
     **/
    public void resumeJPush() {
        JPushInterface.resumePush(mContext);
    }
}
