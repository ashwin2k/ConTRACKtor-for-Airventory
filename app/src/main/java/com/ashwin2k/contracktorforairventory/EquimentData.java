package com.ashwin2k.contracktorforairventory;

public class EquimentData {
    String datedue;
    String timedue;
    String item;
    String airport;

    public String getAirport() {
        return airport;
    }

    public EquimentData(String d, String t, String it,String ap){
        datedue=d;
        timedue=t;
        item=it;
        airport=ap;
    }

    public String getDatedue() {
        return datedue;
    }

    public String getTimedue() {
        return timedue;
    }

    public String getItem() {
        return item;
    }


}
