package com.android.fury.Dambel.PageStart;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.android.fury.Dambel.Login;
import com.android.fury.Dambel.PermissionHandler;
import com.android.fury.Dambel.R;
import com.android.fury.Dambel.page.Page_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by fury on 1/28/2017.
 */
public class PageStart extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        String page_1_1 = (String) getText(R.string.start_app_1_1);
        String page_1_2 = (String) getText(R.string.start_app_1_2);
        String page_2_1 = (String) getText(R.string.start_app_2_1);
        String page_2_2 = (String) getText(R.string.start_app_2_2);
        String page_3_1 = (String) getText(R.string.start_app_3_1);
        String page_3_2 = (String) getText(R.string.start_app_3_2);
        String page_4_1 = (String) getText(R.string.start_app_4_1);
        String page_4_2_1 = (String) getText(R.string.start_app_4_2_1);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard(page_1_1, page_1_2, R.drawable.welcome);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard(page_2_1, page_2_2, R.drawable.thank_you);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard(page_3_1, page_3_2, R.drawable.fat);
        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard(page_4_1, page_4_2_1, R.drawable.uptodate);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("بزن بریم");

        showNavigationControls(true);

        setImageBackground(R.drawable.page_start);

        Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
        setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);

        if (Build.VERSION.SDK_INT >= 22) {
            new Handler().postDelayed(new Thread() {

                @Override
                public void run() {
                    super.run();
                    checkPermissions();
                }

                ;
            }, 1000);
        }


    }


    @Override
    public void onFinishButtonPressed() {

        //Intent uou = new Intent(PageStart.this, Login.class);
        Intent uou = new Intent(PageStart.this,Login.class);
        startActivity(uou);
        new Handler().postDelayed(new Thread() {

            @Override
            public void run() {
                super.run();
                PageStart.this.finish();
            }

            ;
        }, 1000);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkPermissions(){

        String[] per = {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

        new PermissionHandler().checkPermission(this, per, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                // permission granted
                // your code
            }

            @Override
            public void onPermissionDenied() {
                // User canceled permission
                Toast.makeText(PageStart.this,"در صورت نپذیرفتن درخواست ها برنامه با مشکل مواجه می شود!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent = new Intent("PERMISSION_RECEIVER");
        intent.putExtra("requestCode",requestCode);
        intent.putExtra("permissions",permissions);
        intent.putExtra("grantResults",grantResults);
        sendBroadcast(intent);
    }
}
