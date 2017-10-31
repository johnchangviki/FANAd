package com.example.ads.viki.fanadsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.ads.*;

/**
 * Created by yuchuan.chang on 11/10/17.
 */

public class AdsActivity extends AppCompatActivity {
    private static final String TAG = "AdsActivity";
    private InstreamVideoAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        Button btnInfo = (Button) findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpInfoActivity();
            }
        });
        initInstreamAd();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adView.loadAd();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    private int pxToDP(int px) {
        return (int) (px / this.getResources().getDisplayMetrics().density);
    }


    private void initInstreamAd() {
        // Get the Ad Container
        final RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.player_ad_container);
        final SurfaceView adViewContainer = (SurfaceView) findViewById(R.id.player_surfaceView);

        adView = new InstreamVideoAdView(
                AdsActivity.this,
                "327777834338912_348860468897315",
                new AdSize(
                        pxToDP(adViewContainer.getMeasuredWidth()),
                        pxToDP(adViewContainer.getMeasuredHeight())
                )
        );

        // set ad listener to handle events
        adView.setAdListener(new InstreamVideoAdListener() {
            @Override
            public void onAdLoaded(Ad ad) {
                Log.d(TAG, "onAdLoaded: ");
                // we have an ad so let's show it
                adView.show();
            }


            @Override
            public void onAdVideoComplete(Ad ad) {
                Log.d(TAG, "onAdVideoComplete: ");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.d(TAG, "onAdClicked: ");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.d(TAG, "onLoggingImpression: ");
            }
        });
        AdSettings.addTestDevice("67216f7668d61c4ec7e00c18e71a524f");
        adContainer.addView(adView);
    }

    protected void jumpInfoActivity() {
        Intent intent = new Intent(this,InfoActivity.class);
        startActivity(intent);
    }
}
