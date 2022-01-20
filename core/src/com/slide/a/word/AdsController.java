package com.slide.a.word;

/**
 * Created by user on 14.09.2017.
 */

public interface AdsController {

    public void showBannerAd();
    public void hideBannerAd();

    public void showOrLoadInterstital();
    public void showVideoAd();
    public boolean hasVideoReward();



    public void prefSetint(String key, int value);
    public void prefSetstring(String key, String value);
    public int prefGetinteger(String key);
    public String prefGetstring(String key);
    public void prefSetlong(String key, Long date);
    public long prefGetlong(String key);
    public boolean prefGetbool(String key);
    public void prefSetbool(String key, boolean bool);
    void toastMaker(String strings);

    void share(int score, boolean isGolden, float timer);
    void shareGame();

}