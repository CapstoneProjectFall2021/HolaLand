package com.hola.holalandcore.util;

public class Format {

    public static String orderId(int id) {
        String strId = "" + id;
        int length = 6 - strId.length();

        while (length > 0) {
            strId = "0" + strId;
            --length;
        }
        return strId;
    }

    public static String phoneNumber(String strPhoneNumber) {
        return strPhoneNumber.substring(0, 4) + " "
                + strPhoneNumber.substring(4, 7) + " "
                + strPhoneNumber.substring(7, 10);
    }
}
