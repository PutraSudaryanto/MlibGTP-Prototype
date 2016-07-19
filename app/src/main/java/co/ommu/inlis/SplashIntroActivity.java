package co.ommu.inlis;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class SplashIntroActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);
        setButtonCtaVisible(true);
        setButtonCtaTintMode(BUTTON_CTA_TINT_MODE_BACKGROUND);
        TypefaceSpan labelSpan = new TypefaceSpan(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? "sans-serif-medium" : "sans serif");
        SpannableString label = SpannableString.valueOf("Get started");
        label.setSpan(labelSpan, 0, label.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        setButtonCtaLabel(label);

        setPageScrollDuration(500);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setPageScrollInterpolator(android.R.interpolator.fast_out_slow_in);

        addSlide(new SimpleSlide.Builder()
                .title("Menus right on your screen")
                .description("Looking for something to eat? Just browse the feed wherever you are.")
                .image(R.drawable.intro_1)
                .background(R.color.color_canteen)
                .backgroundDark(R.color.color_dark_canteen)
                .layout(R.layout.slide_canteen)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Cash on delivery")
                .description("Order and we\\'ll deliver the menu right in front of your door.")
                .image(R.drawable.intro_2)
                .background(R.color.color_canteen)
                .backgroundDark(R.color.color_dark_canteen)
                .layout(R.layout.slide_canteen)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Choose what you like")
                .description("Bookmark a menu you wanted to save and keep track of its status whether it\\'s available or not.")
                .image(R.drawable.intro_3)
                .background(R.color.color_canteen)
                .backgroundDark(R.color.color_dark_canteen)
                .layout(R.layout.slide_canteen)
                .build());

        //autoplay(2500, INFINITE);
    }

}
