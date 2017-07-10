package com.atasoyh.lastfmartistfinder.util;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class TextUtils {
    public static boolean isEmpty(String string) {
        if (string == null || string.equals(""))
            return true;
        return false;
    }
}
