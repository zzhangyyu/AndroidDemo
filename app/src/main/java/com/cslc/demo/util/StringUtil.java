package com.cslc.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * zhangyu
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 是否为null或""
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 是否不为null或""
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 字符串为空,返回""
     *
     * @param value
     * @return
     */
    public static String getValue(String value) {
        return value == null ? "" : value;
    }

    /**
     * 隐藏手机号
     *
     * @param mobile
     * @return
     */
    public static String hideMobile(String mobile) {
        if (StringUtil.isEmpty(mobile)) {
            return "";
        }
        char[] mobiles = mobile.toCharArray();
        for (int i = 3; i < mobiles.length - 4; i++) {
            mobiles[i] = '*';
        }
        return new String(mobiles);
    }

    /**
     * 隐藏真实姓名
     *
     * @param realName
     * @return
     */
    public static String hideRealName(String realName) {
        if (StringUtil.isEmpty(realName)) {
            return "";
        }
        char[] realNames = realName.toCharArray();
        for (int i = 0; i < realNames.length - 1; i++) {
            realNames[i] = '*';
        }
        return new String(realNames);
    }

    /**
     * 隐藏银行卡号
     *
     * @param bankCard
     * @return
     */
    public static String hideBankCard(String bankCard) {
        if (StringUtil.isEmpty(bankCard)) {
            return "";
        }
        char[] bankCards = bankCard.toCharArray();
        for (int i = 4; i < bankCards.length - 4; i++) {
            bankCards[i] = '*';
        }
        return new String(bankCards);
    }

    /**
     * 隐藏身份证号
     *
     * @param idCard
     * @return
     */
    public static String hideIdCard(String idCard) {
        if (StringUtil.isEmpty(idCard)) {
            return "";
        }
        char[] idCards = idCard.toCharArray();
        for (int i = 3; i < idCards.length - 4; i++) {
            idCards[i] = '*';
        }
        return new String(idCards);
    }

    /**
     * 前面补零
     *
     * @param str
     * @return
     */
    public static String appendZero(String str) {
        return "00".substring(str.length()) + str;
    }

    /**
     * Stream转String
     *
     * @param is
     * @return
     */
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
