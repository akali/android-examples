package com.tasbaque.recyclerview;

class Utils {
    public static String resize(String s) {
        if (s.length() > 10) {
            return s.substring(0, 10) + "...";
        }
        return s;
    }
}
