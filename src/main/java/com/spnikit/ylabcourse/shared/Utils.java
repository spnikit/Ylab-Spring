package com.spnikit.ylabcourse.shared;

public class Utils {

    public static int[] convertNumpadValuesToXYCoordinates(int numpadVal){

        switch (numpadVal) {
            case 1 -> {
                return new int[]{0, 0};
            }
            case 2 -> {
                return new int[]{1, 0};
            }
            case 3 -> {
                return new int[]{2, 0};
            }
            case 4 -> {
                return new int[]{0, 1};
            }
            case 5 -> {
                return new int[]{1, 1};
            }
            case 6 -> {
                return new int[]{2, 1};
            }
            case 7 -> {
                return new int[]{0, 2};
            }
            case 8 -> {
                return new int[]{1, 2};
            }
            case 9 -> {
                return new int[]{2, 2};
            }
            default -> {
                throw new IllegalArgumentException("value should be from 1 to 9");
            }
        }
    }
}
