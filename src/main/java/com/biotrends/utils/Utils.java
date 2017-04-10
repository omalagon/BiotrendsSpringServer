package com.biotrends.utils;

/**
 * @author Oscar Malagon
 * @since 15/01/2017.
 */
public final class Utils {

    private Utils(){}

    public static String booleanToYesNoString(boolean value){
        if(value){
            return "SI";
        }

        return "NO";
    }

    public static Boolean stringToBoolean(String value){
        if(null != value){
            return "SI".equalsIgnoreCase(value);
        }

        return false;
    }
}
