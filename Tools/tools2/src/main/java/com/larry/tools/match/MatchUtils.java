package com.larry.tools.match;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PC on 2016/11/14.
 */
public class MatchUtils {
    /**
     * 校验手机号是否正确
     *
     * @param phone
     * @return
     */
    public static boolean validPhone(String phone) {
        if (TextUtils.isEmpty(phone.replace(" ", ""))) {
            return false;
        } else if (phone.length() < 11) {
            return false;
        } else {
            String phoneRule = "^1[3|4|5|7|8][0-9]\\d{8}$";
            Pattern p = Pattern.compile(phoneRule);
            Matcher match = p.matcher(phone);
            if (!match.matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验是否包含特殊字符
     *
     * @param str
     * @return 包含返回true  不包含返回false
     */
    public static boolean checkSpecialChar(String str) {
        String regEx = "[`~!@#$%^&*()-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     *验证是否匹配邮箱
     *
     * @param email
     * @return
     */
    public boolean isEmail(String email) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
