package net.consto.strategy;

/**
 * Created by moritz on 26.08.15.
 */
public class Tools {
    public static float roundTo(float value, int decimalPlace) {
        return (float) ((float) (Math.round(value * Math.pow(10, decimalPlace))) / Math.pow(10, decimalPlace));
    }

    public static double roundTo(double value, int decimalPlace) {
        return ((Math.round(value * Math.pow(10, decimalPlace))) / Math.pow(10, decimalPlace));
    }

    public static String roundToString(float value) {   //always 4 digits

        if (value >= 100000000) {//100 Million
            return String.valueOf((int)roundTo(value/1000000, 0))+"M";
        }
        if (value >= 10000000) {//10 Million
            return String.valueOf(roundTo(value/1000000, 1))+"M";
        }
        if (value >= 1000000) {//1 Million
            return String.valueOf(roundTo(value/1000000, 2))+"M";
        }
        if (value >= 100000) {//100 K
            return String.valueOf((int)roundTo(value/1000, 0))+"K";
        }
        if (value >= 10000) {//10 K
            return String.valueOf(roundTo(value/1000, 1))+"K";
        }
        if (value >= 1000) {//1K
            return String.valueOf(roundTo(value/1000, 2))+"K";

        }
        if (value >= 100) {
            return String.valueOf((int)roundTo(value, 0));
        }
        if (value >= 10) {
            return String.valueOf(roundTo(value, 1));
        }
        return String.valueOf(roundTo(value, 2));
    }
}
