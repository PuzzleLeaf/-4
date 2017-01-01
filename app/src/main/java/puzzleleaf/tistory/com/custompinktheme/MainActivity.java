package puzzleleaf.tistory.com.custompinktheme;


import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    private static final String KAKAOTALK_SETTINGS_THEME_URI = "kakaotalk://settings/theme/";
    private static final String MARKET_URI = "market://details?id=";
    private static final String KAKAO_TALK_PACKAGE_NAME = "com.kakao.talk";
    private boolean isCurrentVersionKakaoTalk = true;
    private AdView mAdView;//배너 광고
    private InterstitialAd mInterstitialAd;//전면 광고


    private Button applyButton;
    private Button makeTheme;
    private Button makeIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //전면 광고
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //applyButton();
            }
        });
        applyButton = (Button) findViewById(R.id.apply_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyButton();
            }
        });

        //테마 만들기 버튼 클릭
        makeTheme = (Button)findViewById(R.id.makeBtn);
        makeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitial();
            }
        });

        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(adRequest);
        }

        //아이콘으로 지정
        makeIcon = (Button)findViewById(R.id.Iconinfo);
        makeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IconInfo.class);
                startActivity(intent);
            }
        });
    }
    private void showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this,"로딩중 입니다.",Toast.LENGTH_LONG).show();
        }
    }

    private void applyButton() {
        if (isCurrentVersionKakaoTalk && isInstalledKakaoTalk()) {
            applyTheme();
            return;
        }
        else
            goToMarket();
    }

    private  boolean isInstalledKakaoTalk(){
        try {
            getPackageManager().getPackageInfo(KAKAO_TALK_PACKAGE_NAME, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void applyTheme() {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(KAKAOTALK_SETTINGS_THEME_URI + getPackageName()));
        try {
            startActivity(intent);
            finish();
        } catch (Exception e) {
            isCurrentVersionKakaoTalk = false;
            applyButton();
        }
    }

    private void goToMarket() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URI + KAKAO_TALK_PACKAGE_NAME));
        startActivity(intent);
        finish();
    }

}
