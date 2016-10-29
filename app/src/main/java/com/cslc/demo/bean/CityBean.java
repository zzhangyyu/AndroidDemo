package com.cslc.demo.bean;

/**
 * Created by Admin on 2016/10/28.
 */

public class CityBean {
    private String tag;
    private String city;

    public CityBean(String tag, String city) {
        this.tag = tag;
        this.city = city;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
