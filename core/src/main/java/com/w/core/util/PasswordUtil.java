package com.w.core.util;

import cn.hutool.crypto.SecureUtil;

/**
 * 密码加密工具
 *
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/13
 */
public class PasswordUtil {

    /**
     * 加密字符串
     * @param charSequence
     * @return
     */
    public static String encrypt(String charSequence) {
        return "###" + SecureUtil.md5(charSequence);
    }

}
