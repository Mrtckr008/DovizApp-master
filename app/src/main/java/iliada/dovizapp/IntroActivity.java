package iliada.dovizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons

        //addSlide(secondFragment);
        //addSlide(thirdFragment);
        //addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("ALIM & SATIM İŞLEMLERİ", "Exchanger ile güncel kurlar üstünden dilediğiniz gibi alım & satım işlemlerini gerçekleştirebilirsiniz !", R.drawable.den, Color.parseColor("#FFA726")));
        addSlide(AppIntroFragment.newInstance("GRAFİK ANALİZİ & KAR, ZARAR DURUMU", "Exchanger ile döviz değerlerinin belirli zaman aralıklarındaki değer grafiklerini analiz edebilir, yatırım stratejine yön verebilirsin !", R.drawable.den2, Color.parseColor("#FB8C00")));
        addSlide(AppIntroFragment.newInstance("Feel Better With EXCHANGER !", "Hemen Exchanger'a Kayıt Ol ! Stratejine yön ver,  Yatırımlarını geliştir, Geleceğe parlak Bak ! ", R.drawable.exc, Color.parseColor("#EF6C00")));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.TRANSPARENT);
       setSeparatorColor(Color.parseColor("#ee7600"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
//        setVibrate(true);
//        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.

        startActivity(new Intent(this,LoginActivity.class));
        finish();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        startActivity(new Intent(this,LoginActivity.class));
        finish();

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.

    }
}
