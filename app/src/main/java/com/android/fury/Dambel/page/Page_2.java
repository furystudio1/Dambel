package com.android.fury.Dambel.page;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.util.IabHelper;
import com.android.billingclient.util.IabResult;
import com.android.billingclient.util.Inventory;
import com.android.billingclient.util.Purchase;
import com.android.billingclient.util.SkuDetails;
import com.android.fury.Dambel.App;
import com.android.fury.Dambel.Database.MyDatabaseHelper_lan;
import com.android.fury.Dambel.Database.MyDatabaseHelper_sound;
import com.android.fury.Dambel.Database.MyDatabaseHelper_vibre;
import com.android.fury.Dambel.Login;
import com.android.fury.Dambel.Not_net;
import com.android.fury.Dambel.PermissionHandler;
import com.android.fury.Dambel.R;
import com.android.fury.Dambel.Start_2;
import com.android.fury.Dambel.Suport.Suport_me;
import com.android.fury.Dambel.alert.SweetAlertDialog;
import com.android.fury.Dambel.app_net;
import com.android.fury.Dambel.food.food;
import com.android.fury.Dambel.loadtoast.LoadToast;
import com.android.fury.Dambel.sport.sport;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import it.sephiroth.android.library.tooltip.Tooltip;

/**
 * Created by ehsan on 8/2/2016.
 */
public class Page_2 extends Activity implements View.OnClickListener {


    private final int _splashTime = 1000;
    private final int _splashTime2 = 500;

    DrawerLayout drawerlayout;

    public static Activity fa;

    ImageView btn_back_page2, btn_menu;

    Button start_sport, start_food;

    MediaPlayer click;

    LinearLayout sports, diet, settings, help, exit, laghv;

    String menu_exit,AKey, about_alertdialog_6, about_alertdialog_7, about_alertdialog_8, about_alertdialog_9, no, yes, music_check, music_off_check, vibre_check, fars, en, de, ca, am, ch, language1,MNumber;

    Vibrator vb;

    MyDatabaseHelper_sound sound_data;
    MyDatabaseHelper_vibre vibre_data;

    int help_play, zaban;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    private Tooltip.TooltipView mCurrentTooltip;

    MyDatabaseHelper_lan language;

    Typeface type3;
    DisplayMetrics displayMetrics;

    TextView text_etebar;

    boolean open, openNew;

    // The helper object
    private IabHelper mHelper;

    ProgressDialog dialog;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Create the helper, passing it our context and the public key to verify signatures with Log.d(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(this, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVyidxffI5Lv+2c0u44SL9OfeF0Rbii0EDtNQkAmn4TiguKyk45MhMqlH9nOx03Z16RAdLSw/c7CAXZZtgJiJWhehS5p4ad2MaFCh+e0Mo5vFJi0Xkxu4M/5rBfGpt1MSc9uQ2Li5ZJu2X2NnbB54Jf67LVZjj91FfVJgcPsUXHwIDAQAB");

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {             // Oh noes, there was a problem
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) return;

                dialog.setMessage("بررسی اشتراک ...");
                // IAB is fully set up. Now
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    //
                }
            }
        });





        try {

            //start zaban
            language = new MyDatabaseHelper_lan(this);
            Cursor r_lan = language.showAllData();
            StringBuffer dadeha = new StringBuffer();
            while (r_lan.moveToNext()) {
                dadeha.append(r_lan.getString(1));
            }
            if (dadeha.toString().length() == 1) {
                //farsi

                Locale locale = new Locale("fa");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                language1 = "fa";

            } else if (dadeha.toString().length() == 2) {
                //english.usa.canada

                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                language1 = "en";

            } else if (dadeha.toString().length() == 3) {
                //chine

                Locale locale = new Locale("zh");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                language1 = "zh";

            } else if (dadeha.toString().length() == 6) {
                //german

                Locale locale = new Locale("de");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                language1 = "de";

            }

        } catch (Exception e) {
        }

        //start activ
        setContentView(R.layout.root_page_2);


        displayMetrics = getResources().getDisplayMetrics();


        try {

            one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
            one_play_editor = one_play_preferences.edit();
            help_play = one_play_preferences.getInt("Help_play_2", 1);
            openNew = one_play_preferences.getBoolean("openNew", true);
            MNumber = one_play_preferences.getString("MobileNumber", "");
            AKey = one_play_preferences.getString("AccessKey", "");

        } catch (Exception e) {
        }


        fa = this;


        try {

            //save music
            sound_data = new MyDatabaseHelper_sound(this);
            Cursor res = sound_data.showAllData();
            StringBuffer data = new StringBuffer();
            while (res.moveToNext()) {
                data.append(res.getString(1));
            }
            if (data.toString().length() == 4) {
                music_check = "true";
            } else if (data.toString().length() == 5) {
                music_check = "false";
            } else {
                music_check = "true";
            }
            music_off_check = "false";

        } catch (Exception e) {
        }

        try {

            //save vibre
            vibre_data = new MyDatabaseHelper_vibre(this);
            Cursor res_2 = vibre_data.showAllData();
            StringBuffer data_2 = new StringBuffer();
            while (res_2.moveToNext()) {
                data_2.append(res_2.getString(1));
            }
            if (data_2.toString().length() == 4) {
                vibre_check = "true";
            } else if (data_2.toString().length() == 5) {
                vibre_check = "false";
            } else {
                vibre_check = "false";
            }

        } catch (Exception e) {
        }


        //az bein bordn shekle scrollview
        ScrollView sView = (ScrollView) findViewById(R.id.scrollview_1);
        sView.setVerticalScrollBarEnabled(false);
        sView.setHorizontalScrollBarEnabled(false);

        btn_menu = (ImageView) findViewById(R.id.btn_menu);
        diet = (LinearLayout) findViewById(R.id.lin_menu_d);
        exit = (LinearLayout) findViewById(R.id.lin_menu_ex);
        settings = (LinearLayout) findViewById(R.id.lin_menu_sit);
        sports = (LinearLayout) findViewById(R.id.lin_menu_sp);
        help = (LinearLayout) findViewById(R.id.lin_menu_gu);
        laghv = (LinearLayout) findViewById(R.id.lin_menu_laghv);
        btn_back_page2 = (ImageView) findViewById(R.id.btn_back_page2);
        start_sport = (Button) findViewById(R.id.sport_start);
        start_food = (Button) findViewById(R.id.im_001);
        text_etebar = (TextView) findViewById(R.id.text_etebar);

        btn_menu.setOnClickListener(this);
        diet.setOnClickListener(this);
        sports.setOnClickListener(this);
        settings.setOnClickListener(this);
        laghv.setOnClickListener(this);
        exit.setOnClickListener(this);
        help.setOnClickListener(this);
        btn_back_page2.setVisibility(View.INVISIBLE);
        text_etebar.setVisibility(View.VISIBLE);
        start_sport.setOnClickListener(this);
        start_food.setOnClickListener(this);

        //sliding view
        drawerlayout = (DrawerLayout) findViewById(R.id.root_page_1);

        menu_exit = (String) getText(R.string.menu_exit2);
        no = (String) getText(R.string.no);
        yes = (String) getText(R.string.yes);
        about_alertdialog_6 = (String) getText(R.string.about_alertdialog_6);
        about_alertdialog_7 = (String) getText(R.string.about_alertdialog_7);

        fars = "fa";
        en = "en";
        de = "de";
        ca = "en";
        am = "en";
        ch = "zh";
        if (language1 == fars) {
            zaban = 1;

            type3 = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");

            start_sport.setTypeface(type3);
            start_food.setTypeface(type3);

        } else if (language1 == en) {
            zaban = 2;

            type3 = Typeface.createFromAsset(getAssets(), "en_font_1.otf");

            start_sport.setTypeface(type3);
            start_food.setTypeface(type3);

        } else if (language1 == de) {
            zaban = 3;

            type3 = Typeface.createFromAsset(getAssets(), "en_font_1.otf");

            start_sport.setTypeface(type3);
            start_food.setTypeface(type3);

        } else if (language1 == ca) {
            zaban = 2;

            type3 = Typeface.createFromAsset(getAssets(), "en_font_1.otf");

            start_sport.setTypeface(type3);
            start_food.setTypeface(type3);

        } else if (language1 == am) {
            zaban = 2;

            type3 = Typeface.createFromAsset(getAssets(), "en_font_1.otf");

            start_sport.setTypeface(type3);
            start_food.setTypeface(type3);

        } else if (language1 == ch) {
            zaban = 4;

        }


        if (openNew) {
            open = true;

            if (app_net.getInstance(Page_2.this).isOnline()) {

                open = false; //for test
                //new GetData().execute();

            } else {
                try {
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("عدم دسترسی به اینترنت")
                            .setContentText("لطفا برای به روز رسانی اطلاعات اینترنت خود را وصل کنید")
                            .setConfirmText("تلاش مجدد")
                            .showCancelButton(false)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {

                                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    if (vibre_check == music_off_check) {
                                        vb.cancel();
                                    } else {
                                        vb.vibrate(100);
                                    }

                                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                                    if (music_check == music_off_check) {
                                        click.stop();
                                    } else {
                                        click.start();
                                    }

                                    Intent uou = new Intent(Page_2.this, Start_2.class);
                                    startActivity(uou);
                                    Page_2.this.finish();

                                }
                            })
                            .show();
                } catch (Exception e) {
                }
            }

        } else {
            open = false;
        }


        if (help_play == 1) {

            String s1 = (String) getText(R.string.Help_start_2_1);
            String s2 = (String) getText(R.string.Help_start_2_2);
            String s3 = s1 + System.getProperty("line.separator") + "" + System.getProperty("line.separator") + s2;

            try {

                mCurrentTooltip = Tooltip.make(
                        Page_2.this,
                        new Tooltip.Builder(101)
                                .maxWidth((int) (displayMetrics.widthPixels / 2))
                                .anchor(start_sport, Tooltip.Gravity.TOP)
                                .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 0)
                                .text(s3)
                                .fitToScreen(false)
                                .fadeDuration(200)
                                .showDelay(50)
                                .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                                .withStyleId(R.style.ToolTipLayoutCustomStyle)
                                .withCallback(
                                        new Tooltip.Callback() {
                                            @Override
                                            public void onTooltipClose(
                                                    final Tooltip.TooltipView v, final boolean fromUser, final boolean containsTouch) {
                                                mCurrentTooltip = null;
                                            }

                                            @Override
                                            public void onTooltipFailed(Tooltip.TooltipView view) {
                                            }

                                            @Override
                                            public void onTooltipShown(Tooltip.TooltipView view) {
                                            }

                                            @Override
                                            public void onTooltipHidden(Tooltip.TooltipView view) {
                                            }
                                        })
                                .build()
                );
                mCurrentTooltip.show();

            } catch (Exception e) {
            }


        } else if (help_play == 2) {

            String s11 = (String) getText(R.string.Help_start_14_1);
            String s22 = (String) getText(R.string.Help_start_14_2);
            String s33 = s11 + System.getProperty("line.separator") + "" + System.getProperty("line.separator") + s22;

            try {

                mCurrentTooltip = Tooltip.make(
                        Page_2.this,
                        new Tooltip.Builder(101)
                                .maxWidth((int) (displayMetrics.widthPixels / 2))
                                .anchor(start_food, Tooltip.Gravity.BOTTOM)
                                .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 0)
                                .text(s33)
                                .fitToScreen(false)
                                .fadeDuration(200)
                                .showDelay(50)
                                .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                                .withStyleId(R.style.ToolTipLayoutCustomStyle)
                                .withCallback(
                                        new Tooltip.Callback() {
                                            @Override
                                            public void onTooltipClose(
                                                    final Tooltip.TooltipView v, final boolean fromUser, final boolean containsTouch) {
                                                mCurrentTooltip = null;
                                            }

                                            @Override
                                            public void onTooltipFailed(Tooltip.TooltipView view) {
                                            }

                                            @Override
                                            public void onTooltipShown(Tooltip.TooltipView view) {
                                            }

                                            @Override
                                            public void onTooltipHidden(Tooltip.TooltipView view) {
                                            }
                                        })
                                .build()
                );
                mCurrentTooltip.show();


            } catch (Exception e) {
            }


        }

        if (Build.VERSION.SDK_INT >= 22) {
            checkPermissions();
        }


        dialog = new ProgressDialog(Page_2.this);
        dialog.setMessage("بروزرسانی اطلاعات ...");
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_menu:
                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {
                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {
                }

                try {

                    if (drawerlayout.isDrawerOpen(Gravity.LEFT)) {
                        drawerlayout.closeDrawer(Gravity.LEFT);
                    } else {
                        drawerlayout.openDrawer(Gravity.LEFT);
                    }

                } catch (Exception e) {

                }

                break;

            case R.id.lin_menu_ex:

                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {

                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(about_alertdialog_7)
                            .setContentText(about_alertdialog_6)
                            .setCancelText(no)
                            .setConfirmText(yes)
                            .showCancelButton(true)
                            .setCancelClickListener(null)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {

                                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    if (vibre_check == music_off_check) {
                                        vb.cancel();
                                    } else {
                                        vb.vibrate(100);
                                    }

                                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                                    if (music_check == music_off_check) {
                                        click.stop();
                                    } else {
                                        click.start();
                                    }

                                    new Handler().postDelayed(new Thread() {
                                        @Override
                                        public void run() {
                                            super.run();

                                            App.close();
                                            System.exit(0);

                                        }
                                    }, _splashTime);
                                    sDialog.setTitleText(menu_exit)
                                            .setContentText(about_alertdialog_8)
                                            .setConfirmText(about_alertdialog_9)
                                            .showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                }
                            })
                            .show();
                } catch (Exception e) {

                }

                break;


            case R.id.lin_menu_sit:

                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {

                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {

                    if (!open) {
                        new Handler().postDelayed(new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                Intent about_2 = new Intent(Page_2.this, com.android.fury.Dambel.menu_view.setting.class);
                                about_2.putExtra("setting_back", 2);
                                Page_2.this.startActivity(about_2);
                                Page_2.this.finish();
                            }
                        }, _splashTime2);
                    } else {
                        Toast.makeText(Page_2.this, "لطفا صبر کنید تا اطلاعات به روز شود", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {

                }

                break;


            case R.id.lin_menu_gu:

                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {

                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {

                    if (!open) {
                        new Handler().postDelayed(new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                Intent suport = new Intent(Page_2.this, Suport_me.class);
                                startActivity(suport);
                            }
                        }, _splashTime2);
                    } else {
                        Toast.makeText(Page_2.this, "لطفا صبر کنید تا اطلاعات به روز شود", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                }
                break;

            case R.id.lin_menu_sp:

                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {

                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                if (!open) {
                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Intent sport_menu = new Intent(Page_2.this, sport.class);
                            sport_menu.putExtra("sport_back", 3);
                            Page_2.this.startActivity(sport_menu);
                        }
                    }, _splashTime2);
                } else {
                    Toast.makeText(Page_2.this, "لطفا صبر کنید تا اطلاعات به روز شود", Toast.LENGTH_SHORT).show();
                }

                try {

                } catch (Exception e) {

                }


                break;

            case R.id.sport_start:

                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {

                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }


                try {
                    if (!open) {
                        new Handler().postDelayed(new Thread() {
                            @Override
                            public void run() {
                                super.run();

                                Intent sport_menu = new Intent(Page_2.this, sport.class);
                                sport_menu.putExtra("sport_back", 3);
                                Page_2.this.startActivity(sport_menu);

                                if (help_play == 1) {

                                    if (null != mCurrentTooltip) {
                                        mCurrentTooltip.hide();
                                        mCurrentTooltip = null;
                                    }

                                    one_play_editor.putInt("Help_play_2", 2);
                                    one_play_editor.commit();
                                    Page_2.this.finish();

                                }

                            }
                        }, _splashTime2);
                    } else {
                        Toast.makeText(Page_2.this, "لطفا صبر کنید تا اطلاعات به روز شود", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {

                }


                break;

            case R.id.im_001:

                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {

                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                if (null != mCurrentTooltip) {
                    mCurrentTooltip.hide();
                    mCurrentTooltip = null;
                }

                try {

                    one_play_editor.putInt("Help_play_2", 3);
                    one_play_editor.commit();

                } catch (Exception e) {

                }

                if (!open) {
                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Intent sport_menu = new Intent(Page_2.this, food.class);
                            sport_menu.putExtra("zaban_fo", zaban);
                            Page_2.this.startActivity(sport_menu);
                        }
                    }, _splashTime2);
                } else {
                    Toast.makeText(Page_2.this, "لطفا صبر کنید تا اطلاعات به روز شود", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.lin_menu_laghv:

                setLaghv();

                break;
            case R.id.lin_menu_d:
                try {

                    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibre_check == music_off_check) {
                        vb.cancel();
                    } else {
                        vb.vibrate(100);
                    }

                } catch (Exception e) {

                }

                try {

                    click = MediaPlayer.create(Page_2.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {
                    if (!open) {
                        new Handler().postDelayed(new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                Intent food_ = new Intent(Page_2.this, food.class);
                                food_.putExtra("zaban_fo", zaban);
                                Page_2.this.startActivity(food_);
                            }
                        }, _splashTime2);
                    } else {
                        Toast.makeText(Page_2.this, "لطفا صبر کنید تا اطلاعات به روز شود", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {

                    Intent food_ = new Intent(Page_2.this, food.class);
                    food_.putExtra("zaban_fo", zaban);
                    Page_2.this.startActivity(food_);
                }

                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Toast.makeText(Page_2.this, getResources().getText(R.string.about_alertdialog_8), Toast.LENGTH_LONG).show();
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            try {

                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibre_check == music_off_check) {
                    vb.cancel();
                } else {
                    vb.vibrate(100);
                }

            } catch (Exception e) {

            }

            try {

                click = MediaPlayer.create(Page_2.this, R.raw.button);
                if (music_check == music_off_check) {
                    click.stop();
                } else {
                    click.start();
                }

            } catch (Exception e) {

            }

            try {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(about_alertdialog_7)
                        .setContentText(about_alertdialog_6)
                        .setCancelText(no)
                        .setConfirmText(yes)
                        .showCancelButton(true)
                        .setCancelClickListener(null)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                if (vibre_check == music_off_check) {
                                    vb.cancel();
                                } else {
                                    vb.vibrate(100);
                                }

                                click = MediaPlayer.create(Page_2.this, R.raw.button);
                                if (music_check == music_off_check) {
                                    click.stop();
                                } else {
                                    click.start();
                                }

                                new Handler().postDelayed(new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        Toast.makeText(Page_2.this, getResources().getText(R.string.about_alertdialog_8), Toast.LENGTH_LONG).show();
                                        App.close();
                                        finish();
                                    }
                                }, _splashTime);

                                sDialog.setTitleText(menu_exit)
                                        .setContentText(about_alertdialog_8)
                                        .setConfirmText(about_alertdialog_9)
                                        .showCancelButton(false)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            } catch (Exception e) {

            }

           /* Intent back_page = new Intent(Page_2.this, Page_1.class);
            Page_2.this.startActivity(back_page);
            finish();*/
        }
        return false;
    }


    @Override
    protected void onStart() {
        super.onStart();

        App.activity_page_2 = this;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        App.activity_page_2 = null;

    }

    public static String sha256(String base){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0 ; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    private class GetData extends AsyncTask<String, String, String> {

        LoadToast lt = new LoadToast(Page_2.this);
        private String url = "http://79.175.164.51:8093/api/App/SubscriberStatus";
        InputStream json;
        StringBuilder responseOutput;
        String mobil = MNumber;
        String AccessKey = AKey;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            lt.setText("در حال به روز رسانی ...");
            lt.setTranslationY(100);
            lt.setProgressColor(getResources().getColor(R.color.color30day));
            lt.show();
            if (mobil.length() != 11){
                lt.error();
                one_play_editor.putInt("one_play_app", 4);
                one_play_editor.apply();
                Intent _new = new Intent(Page_2.this, Start_2.class);
                startActivity(_new);
                finish();
            }

        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("MobileNumber", mobil));
                params.add(new BasicNameValuePair("ServiceCode", "FitShow"));
                params.add(new BasicNameValuePair("AccessKey", sha256("SubscriberStatusFitShow" + mobil)));
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                json = httpEntity.getContent();

                BufferedReader br = new BufferedReader(new InputStreamReader(json));
                String line = "";
                responseOutput = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return responseOutput.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    String mobileNumber = jsonObject.getString("MobileNumber");
                    String status = jsonObject.getString("Status");
                    int activationCode = jsonObject.getInt("DaysLeft");

                    if (Objects.equals(status, "Active")) {
                        lt.success();
                        text_etebar.setText( "اعتبار : "+ activationCode+ " روز" );
                        open = false;
                        one_play_editor.putBoolean("openNew", false);
                        one_play_editor.commit();
                    } else if (Objects.equals(status, "NotActive")) {
                        lt.error();
                        one_play_editor.putInt("one_play_app", 4);
                        one_play_editor.apply();
                        Intent _new = new Intent(Page_2.this, Start_2.class);
                        startActivity(_new);
                        finish();
                    } else {
                        lt.error();
                        Toast.makeText(Page_2.this, "خطا !", Toast.LENGTH_LONG).show();
                        finish();
                    }

                } catch (Exception e) {
                }

            } else {
                lt.error();
                Toast.makeText(getApplicationContext(), "ارتباط با سرور برقرار نشد", Toast.LENGTH_LONG).show();
                finish();
            }

        }
    }


    private void checkPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            new PermissionHandler().checkPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionHandler.OnPermissionResponse() {
                @Override
                public void onPermissionGranted() {
                    // permission granted
                    // your code
                }

                @Override
                public void onPermissionDenied() {
                    // User canceled permission
                    Toast.makeText(Page_2.this,"در صورت نپذیرفتن درخواست ها برنامه با مشکل مواجه می شود!", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            new PermissionHandler().checkPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionHandler.OnPermissionResponse() {
                @Override
                public void onPermissionGranted() {
                    // permission granted
                    // your code
                }

                @Override
                public void onPermissionDenied() {
                    // User canceled permission
                    Toast.makeText(Page_2.this,"در صورت نپذیرفتن درخواست ها برنامه با مشکل مواجه می شود!", Toast.LENGTH_LONG).show();
                }
            });
        }


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


    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                return;
            }


            Purchase purchase = inventory.getPurchase("Dambel_123");
            try {
                token = purchase.getToken();
            }catch (Exception e){

            }
            boolean mAutoRenewEnabled = purchase != null && purchase.isAutoRenewing();
            Log.e("auto" , String.valueOf(mAutoRenewEnabled));
            one_play_editor.putString("token", token);
            one_play_editor.apply();
            /** Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().*/

            dialog.dismiss();

            if (purchase == null){
                Toast.makeText(Page_2.this, "اشتراک شما به پایان رسیده است", Toast.LENGTH_LONG).show();
                one_play_editor.putInt("one_play_app", 4);
                one_play_editor.apply();
                Intent uou = new Intent(Page_2.this, Start_2.class);
                startActivity(uou);
                Page_2.this.finish();
            }

        }
    };



    public void setLaghv(){
        RequestQueue requestQueue = Volley.newRequestQueue(Page_2.this);
        String url = "https://seller.jhoobin.com/ws/androidpublisher/v2/applications/com.android.fury.Dambel/purchases/subscriptions/Dambel_123/tokens/" + token + ":cancel?access_token=186e6f65-160b-3337-a4a5-59762e7eedf0";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("get" , String.valueOf(response));

                Toast.makeText(Page_2.this, "اشتراک با موفقیت لغو شد", Toast.LENGTH_LONG).show();
                one_play_editor.putInt("one_play_app", 3);
                one_play_editor.apply();
                /*Intent uou = new Intent(Page_2.this, Start_2.class);
                startActivity(uou);*/
                Page_2.this.finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something
                Toast.makeText(Page_2.this, "اشتراک با موفقیت لغو شد", Toast.LENGTH_LONG).show();
                one_play_editor.putInt("one_play_app", 3);
                one_play_editor.apply();
                /*Intent uou = new Intent(Page_2.this, Start_2.class);
                startActivity(uou);*/
                Page_2.this.finish();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }


}
