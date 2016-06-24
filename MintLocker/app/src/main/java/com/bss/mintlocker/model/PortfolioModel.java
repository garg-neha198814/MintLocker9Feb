package com.bss.mintlocker.model;

/**
 * Created by bhawanisingh on 03/12/15.
 */
public class PortfolioModel {
    private String title;
    private String desc;
    private float valueCash;
    private float valueUSStock;
    private float valueNonUSStock;
    private float valueBonds;
    private float valueOthers;


    public PortfolioModel(String title, String desc, float valueCash, float valueUSStock, float valueNonUSStock, float valueBonds, float valueOthers) {
        this.title = title;
        this.desc = desc;
        this.valueCash = valueCash;
        this.valueUSStock = valueUSStock;
        this.valueNonUSStock = valueNonUSStock;
        this.valueBonds = valueBonds;
        this.valueOthers = valueOthers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getValueCash() {
        return valueCash;
    }

    public void setValueCash(float valueCash) {
        this.valueCash = valueCash;
    }

    public float getValueUSStock() {
        return valueUSStock;
    }

    public void setValueUSStock(float valueUSStock) {
        this.valueUSStock = valueUSStock;
    }

    public float getValueNonUSStock() {
        return valueNonUSStock;
    }

    public void setValueNonUSStock(float valueNonUSStock) {
        this.valueNonUSStock = valueNonUSStock;
    }

    public float getValueBonds() {
        return valueBonds;
    }

    public void setValueBonds(float valueBonds) {
        this.valueBonds = valueBonds;
    }

    public float getValueOthers() {
        return valueOthers;
    }

    public void setValueOthers(float valueOthers) {
        this.valueOthers = valueOthers;
    }
}
