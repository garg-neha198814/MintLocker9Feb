package com.bss.mintlocker.model;

/**
 * Created by bhawanisingh on 04/12/15.
 */
public class ActivityModel {

    private String showdate;
    private String dateMain;

    private String heading;
    private String smalldate;

    private String largeAmount;
    private String smallAmount;
    private String showDownload;


    public ActivityModel(String showdate, String dateMain, String heading, String smalldate, String largeAmount, String smallAmount,String showDownload) {
        this.showdate = showdate;
        this.dateMain = dateMain;
        this.heading = heading;
        this.smalldate = smalldate;
        this.largeAmount = largeAmount;
        this.smallAmount = smallAmount;
        this.showDownload = showDownload;
    }

    public String getShowdate() {
        return showdate;
    }

    public void setShowdate(String showdate) {
        this.showdate = showdate;
    }

    public String getDateMain() {
        return dateMain;
    }

    public void setDateMain(String dateMain) {
        this.dateMain = dateMain;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSmalldate() {
        return smalldate;
    }

    public void setSmalldate(String smalldate) {
        this.smalldate = smalldate;
    }

    public String getLargeAmount() {
        return largeAmount;
    }

    public void setLargeAmount(String largeAmount) {
        this.largeAmount = largeAmount;
    }

    public String getSmallAmount() {
        return smallAmount;
    }

    public void setSmallAmount(String smallAmount) {
        this.smallAmount = smallAmount;
    }

    public String getShowDownload() {
        return showDownload;
    }

    public void setShowDownload(String showDownload) {
        this.showDownload = showDownload;
    }
}
