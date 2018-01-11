package com.example.ashutosh.okhati;

/**
 * Created by Ashutosh on 1/10/2018.
 */

public class Data {
    public String title;
    public String message;
    public Double mlongitude;
    public Double mlatitude;


    public Data()
    {

    }
    public Data(String title, String message,Double mlongitude,Double mlatitude)
    {
        this.title=title;
        this.message=message;
        this.mlongitude=mlongitude;
        this.mlatitude=mlatitude;

    }

    public Double getLongitude(){
        return mlongitude;
    }
    public Double getLatitude(){
        return mlatitude;
    }
    public String gettitle(){
        return title;
    }
    public String getMessage()

    {
        return message;
    }

}
