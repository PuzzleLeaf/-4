package puzzleleaf.tistory.com.custompinktheme;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class Splash extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;//전면 광고
    //Splash 화면이 떠 있을 시간 지정
    private static final String KAKAOTALK_SETTINGS_THEME_URI = "kakaotalk://settings/theme/";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

        int temp = IconData.getAdCount(this);
        IconData.saveAdCount(this,++temp);

        //무료버전
        if(IconData.getValue(this)==1) {
            if(temp%20 == 0)//10번에 한번 광고가 호출
                loadAd();
            else{
                startKaKaoTalk();
            }
        }
        else {
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    mInterstitialAd.show();
                }

                @Override
                public void onAdClosed() {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequest);
            }
        }
    }

    private void loadAd()
    {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                startKaKaoTalk();
            }
        });
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }
    private void startKaKaoTalk()
    {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(KAKAOTALK_SETTINGS_THEME_URI + getPackageName()));
        startActivity(intent);
        finish();
    }


}
