package com.nobitastudio.materialdesign.jpush;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author chenxiong
 * @email nobita0522@qq.com
 * @date 2019/01/17 14:27
 * @description 设置极光推送的tag，alias需要的model类
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagAliasBean implements Serializable {

    private static final long serialVersionUID = 6343574309752862746L;

    private int action;
    private Set<String> tags;
    private String alias;
    private boolean isAliasAction;
}
