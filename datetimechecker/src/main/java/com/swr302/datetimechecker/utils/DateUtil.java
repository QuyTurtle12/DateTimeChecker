package com.swr302.datetimechecker.utils;

public class DateUtil {

    private static final int[] DAYS_IN_MONTHS = {
        31, // January
        28, // February
        31, // March
        30, // April
        31, // May
        30, // June
        31, // July
        31, // August
        30, // September
        31, // October
        30, // November
        31  // December
    };

    public static boolean isValidDate(int year, int month, int day) {
        // Check for leap year
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        if (isLeapYear && month == 2) {
            return day >= 1 && day <= 29;
        } else {
            return day >= 1 && day <= DAYS_IN_MONTHS[month - 1];
        }
    }

    public static int getDaysInMonth(int year, int month) {
        if (month < 1 || month > 12) {
            throw new IndexOutOfBoundsException("Invalid month: " + month);
        }
        if (month == 2 && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) {
            return 29; // Leap year
        }
        return DAYS_IN_MONTHS[month - 1];
    }
}