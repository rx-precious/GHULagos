package com.preccytech.ghulagos;

/**
 * Created by Rx Precious on 8/26/2017.
 */

public class DevelopersList {
    private String mUsername;
    private String mUrl;
    private static String mPicture;

    public  DevelopersList(String username, String url, String picture){
        mUsername = username;
        mUrl = url;
        mPicture = picture;
    }

    public String getmUsername(){return mUsername;}
    public String getmUrl(){return mUrl;}
    public String getmPicture(){return mPicture;}

}
