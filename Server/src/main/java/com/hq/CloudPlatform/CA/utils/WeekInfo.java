package com.hq.CloudPlatform.CA.utils;

import java.util.Date;

public class WeekInfo {

    /**
     * 当前日期
     */
    private Date currentDate;

    /**
     * 当前所在星期的周一的日期
     */
    private Date monday;

    private Date tuesday;

    private Date wednesday;

    private Date thursday;

    private Date friday;

    private Date saturday;

    /**
     * 当前所在日期的周日的日期
     */
    private Date sunday;

    private int weekOfYear;

    private int dayOfWeek;

    private int year;

    public Date getMonday() {
        return monday;
    }

    public void setMonday(Date monday) {
        this.monday = monday;
    }

    public Date getSunday() {
        return sunday;
    }

    public void setSunday(Date sunday) {
        this.sunday = sunday;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getTuesday() {
        return tuesday;
    }

    public void setTuesday(Date tuesday) {
        this.tuesday = tuesday;
    }

    public Date getWednesday() {
        return wednesday;
    }

    public void setWednesday(Date wednesday) {
        this.wednesday = wednesday;
    }

    public Date getThursday() {
        return thursday;
    }

    public void setThursday(Date thursday) {
        this.thursday = thursday;
    }

    public Date getFriday() {
        return friday;
    }

    public void setFriday(Date friday) {
        this.friday = friday;
    }

    public Date getSaturday() {
        return saturday;
    }

    public void setSaturday(Date saturday) {
        this.saturday = saturday;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(int weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
