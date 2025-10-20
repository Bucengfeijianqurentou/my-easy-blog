package com.gb.myeasyblog.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {
    public static String md5(String password) {
        return DigestUtils.md5Hex(password);
    }
}
