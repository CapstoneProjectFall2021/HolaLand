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
}
