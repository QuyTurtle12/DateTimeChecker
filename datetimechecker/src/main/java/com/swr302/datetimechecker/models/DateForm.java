package com.swr302.datetimechecker.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DateForm {
    @NotNull(message = "Day is required")
    @Pattern(regexp = "^\\d+$", message = "Input data for Day is incorrect format!")
    private String day;
    
    @NotNull(message = "Month is required")
    @Pattern(regexp = "^\\d+$", message = "Input data for Month is incorrect format!")
    private String month;
    
    @NotNull(message = "Year is required")
    @Pattern(regexp = "^\\d+$", message = "Input data for Year is incorrect format!")
    private String year;

    // Getters and setters
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}
