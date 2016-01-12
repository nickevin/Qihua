package com.qihua.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String DECIMAL_REGEXP = "^([+-]?)\\d*\\.\\d+$";
    private static final String POSITIVE_DECIMAL_REGEXP = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";
    private static final String NEGTIVE_DECIMAL_REGEXP = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";
    private static final String INTEGER_REGEXP = "^-?[1-9]\\d*$";
    private static final String POSTIVE_INTEGER_REGEXP = "^[1-9]\\d*$";
    private static final String NEGTIVE_INTEGER_REGEXP = "^-[1-9]\\d*$";
    private static final String NUMBER_REGEXP = "^([+-]?)\\d*\\.?\\d+$";
    private static final String POSTIVE_REGEXP = "^[1-9]\\d*|0$";
    private static final String NEGTIVE_REGEXP = "^-[1-9]\\d*|0$";
    private static final String ASCII_REGEXP = "^[\\x00-\\xFF]+$";
    private static final String CHINESE_REGEXP = "^[\\u4e00-\\u9fa5]+$";
    private static final String COLOR_REGEXP = "^[a-fA-F0-9]{6}$";
    private static final String ID_CARD_REGEXP = "^[1-9]([0-9]{14}|[0-9]{17})$";
    private static final String IP4_REGEXP = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";
    private static final String LETTER_REGEXP = "^[A-Za-z]+$";
    private static final String LOWERCASE_REGEXP = "^[a-z]+$";
    private static final String UPPERCASE_REGEXP = "^[A-Z]+$";
    private static final String MOBILE_REGEXP = "^0?(13|15|18|14|17)[0-9]{9}$";
    private static final String TELPHONE_REGEXP = "^[0-9\\-()（）]{7,18}$";
    private static final String URL_REGEXP = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";
    private static final String ZIP_CODE_REGEXP = "^\\d{6}$";
    private static final String EMAIL_REGEXP = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    public static boolean isDecimal(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(DECIMAL_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isPositiveDecimal(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(POSITIVE_DECIMAL_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isNegtiveDecimal(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(NEGTIVE_DECIMAL_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isInteger(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(INTEGER_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isPostiveInteger(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(POSTIVE_INTEGER_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isNegtiveInteger(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(NEGTIVE_INTEGER_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isNumber(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(NUMBER_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isPostive(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(POSTIVE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isNegtive(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(NEGTIVE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isAscii(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(ASCII_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isChinese(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(CHINESE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isColor(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(COLOR_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isIDCard(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(ID_CARD_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isIP4(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(IP4_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isLetter(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(LETTER_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isLowercase(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(LOWERCASE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isUppercase(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(UPPERCASE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isTelphone(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(TELPHONE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isURL(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(URL_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isZIPCode(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(ZIP_CODE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isEmail(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(EMAIL_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isMobile(String val) {
        boolean result = false;

        Pattern p = Pattern.compile(MOBILE_REGEXP);
        Matcher m = p.matcher(val);
        try {
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
