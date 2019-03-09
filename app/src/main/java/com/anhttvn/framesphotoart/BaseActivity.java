package com.anhttvn.framesphotoart;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.anhttvn.framesphotoart.util.InternetConnection;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class BaseActivity extends Activity {
    private InterstitialAd mInterstitialAd;
    private AdRequest mAdRequest;
    protected void Toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    protected void runFullBanner(){
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3840180634112397~9187759690");

        mAdRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("D7F715D75F72FD215E30ED47E4B8A56C").build();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(this.getString(R.string.banner_full_screen));
        if(InternetConnection.checkConnection(this)){
            mInterstitialAd.loadAd(mAdRequest);
        }

    }

    protected void showADS(){
        if(mInterstitialAd != null && mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }
    protected void runAdview(final AdView adv){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("D7F715D75F72FD215E30ED47E4B8A56C").build();

        adv.setVisibility(View.GONE);
        adv.loadAd(adRequest);
        adv.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adv.setVisibility(View.VISIBLE);
            }
        });
    }
}
