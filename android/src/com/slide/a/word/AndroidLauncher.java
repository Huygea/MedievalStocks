package com.slide.a.word;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class AndroidLauncher extends AndroidApplication implements AdsController {






//private static final String BANNER_AD_UNIT_ID = "ca-app-pub-5388457593887062/1753769026";

	public static final String PLAYSTORE_LINK= "https://play.google.com/store/apps/details?id=com.slide.a.word";
	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-5388457593887062/5752590569";//banner REAL

	//private static final String BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";//banner TESTER

	AdView bannerAd;
	static boolean rewardBool = false;

	private static final String APP_ID="ca-app-pub-5388457593887062~9883407267";
	//private static final String AD_UNIT_ID="ca-app-pub-5388457593887062/2823200311";

	//private static final String AD_UNIT_ID="ca-app-pub-3940256099942544/1033173712"; //inter

//ca-app-pub-3940256099942544/5224354917
	//private static final String AD_UNIT_ID_VID="ca-app-pub-3940256099942544/5224354917";//Tester

    private static final String AD_UNIT_ID_VID="ca-app-pub-5388457593887062/7967690361";//VID




	private RewardedVideoAd mAd;

	private InterstitialAd interstitialAd;


    public  SharedPreferences prefs;
    public SharedPreferences.Editor editor;


    public void prefSetint(String key, int value){


        editor.putInt(key,value);
        editor.apply();
    }

    public void prefSetstring(String key,String value){


        editor.putString(key,value);
        editor.apply();



    }


    public String prefGetstring(String key){

        return  prefs.getString(key,null);




    }

    public int prefGetinteger(String key ){

        return prefs.getInt(key,0);
    }

    public void prefSetlong(String key, Long date){
        editor.putLong(key,date);
        editor.apply();
    }
    public long prefGetlong(String key){


        return prefs.getLong(key,-1);
    }

    public void prefSetbool(String key, boolean bool){
    	editor.putBoolean(key,bool);
    	editor.apply();
	}
	public boolean prefGetbool(String key){
    	return prefs.getBoolean(key,false);
	}






    @Override
	protected void onCreate (Bundle savedInstanceState) {



		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);
		AdRequest request = new AdRequest.Builder()
				.addTestDevice("703AD1F1FB26D487D4BFF955B88B9191")  // An example device ID
				.build();

		prefs = getApplicationContext().getSharedPreferences("mypref", MODE_PRIVATE);
		editor = prefs.edit();





		MobileAds.initialize(this, APP_ID);

		mAd = MobileAds.getRewardedVideoAdInstance(this);
		mAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
			@Override
			public void onRewardedVideoAdLoaded() {

			}

			@Override
			public void onRewardedVideoAdOpened() {

			}

            @Override
            public void onRewardedVideoStarted() {

            }


            @Override
			public void onRewardedVideoAdClosed() {
				loadRewardedVideoAd();
			}

			@Override
			public void onRewarded(RewardItem rewardItem) {

				prefSetint("life",5);
				// call rewards method from here.
				loadRewardedVideoAd();  // Load for next Reward Point

			}

			@Override
			public void onRewardedVideoAdLeftApplication() {

			}

			@Override
			public void onRewardedVideoAdFailedToLoad(int i) {

				if(prefGetinteger("life")<1) {

					showOrLoadInterstital();
					prefSetint("life", 1);

				}

			}

			@Override
			public void onRewardedVideoCompleted() {

			}


		});
		loadRewardedVideoAd();

		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId("ca-app-pub-5388457593887062/9308692196");// REAL


		//interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); //TESTER

		showOrLoadInterstital();

		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// Toast.makeText(getApplicationContext(), "Finished Loading Interstitial", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onAdClosed() {
				// Toast.makeText(getApplicationContext(), "Closed Interstitial", Toast.LENGTH_SHORT).show();
			}
		});

		if(interstitialAd.isLoaded())

			showOrLoadInterstital();


		View gameView = initializeForView(new EntryPoint(this), config);



		RelativeLayout layout = new RelativeLayout(this);
		setupAds();

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		layout.addView(bannerAd, params);
		showBannerAd();

		//editor.clear();
		//editor.commit();

		setContentView(layout);
	}
	private void loadRewardedVideoAd() {
		mAd.loadAd(AD_UNIT_ID_VID, new AdRequest.Builder().build());
	}
	public void showVideoAd(){
		runOnUiThread(new Runnable() {
			public void run() {

				if (mAd.isLoaded()) {
					mAd.show();
				} else {
					loadRewardedVideoAd();
				}
			}
		});
	}

	public boolean hasVideoReward(){
		return mAd.isLoaded();
	}
	@Override
	protected void onResume() {
		super.onResume();
		mAd.resume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mAd.pause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mAd.destroy(this);
	}
	public void setupAds() {
		bannerAd = new AdView(this);
		bannerAd.setVisibility(View.INVISIBLE);
		bannerAd.setBackgroundColor(0xff000000); // black
		bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
		bannerAd.setAdSize(AdSize.SMART_BANNER);

	}

	public void toastMaker(final String strings){

		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(getBaseContext(), strings,
						Toast.LENGTH_LONG).show();
			}
		});





	}
	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.VISIBLE);
				AdRequest.Builder builder = new AdRequest.Builder();
				AdRequest ad = builder.build();
				bannerAd.loadAd(ad);
			}
		});
	}




	@Override
	public void showOrLoadInterstital() {
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					if (interstitialAd.isLoaded()) {
						interstitialAd.show();
						// Toast.makeText(getApplicationContext(), "Showing Interstitial", Toast.LENGTH_SHORT).show();
					}
					else {
						AdRequest interstitialRequest = new AdRequest.Builder().build();
						interstitialAd.loadAd(interstitialRequest);
						//Toast.makeText(getApplicationContext(), "Loading Interstitial", Toast.LENGTH_SHORT).show();
					}
				}
			});
		} catch (Exception e) {
		}
	}




	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.INVISIBLE);
			}
		});
	}


	public void likeApp(){


		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("https://play.google.com/whateveryoururlis"));
		this.startActivity(i);

	}


    public void shareGame() {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Slide-A-Word");
        String sAux;




            sAux = "An amazing word game with a unique playstyle, you should download this\n\n";
            sAux = sAux + PLAYSTORE_LINK +" \n\n";



        sharingIntent.putExtra(Intent.EXTRA_TEXT, sAux);
        startActivity(Intent.createChooser(sharingIntent, "Share the LOVE"));

    }



    public void share(int score, boolean isGolden, float timer) {

		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Slide-A-Word");
		String sAux;

		if(isGolden){
			sAux = "I have scored "+score+" in "+timer+" seconds with ALL GOLDEN letters try to beat me now! :)\n\n";
			sAux = sAux + PLAYSTORE_LINK +" \n\n";



		}

		else{
			sAux = "I have scored "+score+" in "+timer+" seconds try to beat me if you can! :)\n\n";
			sAux = sAux + PLAYSTORE_LINK +" \n\n";

		}


		sharingIntent.putExtra(Intent.EXTRA_TEXT, sAux);
		startActivity(Intent.createChooser(sharingIntent, "Share your score anywhere you want!"));

	}

}
