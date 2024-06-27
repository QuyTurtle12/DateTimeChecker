package com.swr302.datetimechecker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.swr302.datetimechecker.utils.DateUtil;

public class DaysInMonthTest {

    @Test
    public void testDaysInMonth() {
        assertEquals(31, DateUtil.getDaysInMonth(2020, 1)); // January
        assertEquals(29, DateUtil.getDaysInMonth(2020, 2)); // February in a leap year
        assertEquals(28, DateUtil.getDaysInMonth(2021, 2)); // February in a non-leap year
        assertEquals(31, DateUtil.getDaysInMonth(2020, 3)); // March
        assertEquals(30, DateUtil.getDaysInMonth(2020, 4)); // April
        assertEquals(31, DateUtil.getDaysInMonth(2021, 5)); // May
        assertEquals(30, DateUtil.getDaysInMonth(2021, 6)); // June
        assertEquals(31, DateUtil.getDaysInMonth(2021, 7)); // July
        assertEquals(31, DateUtil.getDaysInMonth(2021, 8)); // August
        assertEquals(30, DateUtil.getDaysInMonth(2019, 9)); // September
        assertEquals(31, DateUtil.getDaysInMonth(2019, 10)); // October
        assertEquals(30, DateUtil.getDaysInMonth(2019, 11)); // November
        assertEquals(31, DateUtil.getDaysInMonth(2019, 12)); // December
    }

    @Test
    public void testInvalidMonth() {
        assertThrows(IndexOutOfBoundsException.class, () -> DateUtil.getDaysInMonth(2020, 0));  // Invalid month 0
        assertThrows(IndexOutOfBoundsException.class, () -> DateUtil.getDaysInMonth(2020, 13)); // Invalid month 13
    }
}