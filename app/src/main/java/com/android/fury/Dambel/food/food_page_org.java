package com.android.fury.Dambel.food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fury.Dambel.App;
import com.android.fury.Dambel.Database.MyDatabaseHelper_lan;
import com.android.fury.Dambel.Database.MyDatabaseHelper_sound;
import com.android.fury.Dambel.Database.MyDatabaseHelper_vibre;
import com.android.fury.Dambel.Not_net;
import com.android.fury.Dambel.R;
import com.android.fury.Dambel.Start_2;
import com.android.fury.Dambel.Suport.Suport_me;
import com.android.fury.Dambel.alert.SweetAlertDialog;
import com.android.fury.Dambel.app_net;
import com.android.fury.Dambel.page.Page_2;
import com.android.fury.Dambel.sport.sport;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.astuetz.PagerSlidingTabStrip;

import org.json.JSONArray;

import java.util.Locale;

/**
 * Created by ehsan on 8/2/2016.
 */
public class food_page_org extends AppCompatActivity implements View.OnClickListener {


    private final int _splashTime = 1000;
    private final int _splashTime2 = 500;

    ImageView btn_back_page2, btn_menu;

    DrawerLayout drawerlayout;

    public static Activity fa;

    MediaPlayer click;

    LinearLayout sports, diet, settings, help, exit, laghv;

    String menu_exit, about_alertdialog_6, about_alertdialog_7, about_alertdialog_8, about_alertdialog_9, no, yes, music_check, music_off_check, vibre_check, fars, en, de, ca, am, ch;

    Vibrator vb;

    MyDatabaseHelper_sound sound_data;
    MyDatabaseHelper_vibre vibre_data;

    public static String d, c, language;
    public static int zaban;

    public static Resources resources;

    MyDatabaseHelper_lan language1;

    private boolean CHECKED_STATE_5;

    private SharedPreferences food_preferences;
    private SharedPreferences.Editor food_editor;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {

            //start zaban
            language1 = new MyDatabaseHelper_lan(this);
            Cursor r_lan = language1.showAllData();
            StringBuffer dadeha = new StringBuffer();
            while (r_lan.moveToNext()) {
                dadeha.append(r_lan.getString(1));
            }
            if (dadeha.toString().length() == 1) {
                //farsi

                Configuration conf = getResources().getConfiguration();
                conf.locale = new Locale("fa");
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                Resources resources = new Resources(getAssets(), metrics, conf);

                language = "fa";

            } else if (dadeha.toString().length() == 2) {
                //english.usa.canada

                Configuration conf = getResources().getConfiguration();
                conf.locale = new Locale("en");
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                Resources resources = new Resources(getAssets(), metrics, conf);

                language = "en";

            } else if (dadeha.toString().length() == 3) {
                //chine

                Configuration conf = getResources().getConfiguration();
                conf.locale = new Locale("zh");
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                Resources resources = new Resources(getAssets(), metrics, conf);

                language = "zh";

            } else if (dadeha.toString().length() == 6) {
                //german

                Configuration conf = getResources().getConfiguration();
                conf.locale = new Locale("de");
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                Resources resources = new Resources(getAssets(), metrics, conf);

                language = "de";

            }

        } catch (Exception e) {
        }


        //start activ
        setContentView(R.layout.root_food_page_org);

        d = (String) getText(R.string.det);
        c = (String) getText(R.string.cal_f);


        fars = "fa";
        en = "en";
        de = "de";
        ca = "en";
        am = "en";
        ch = "zh";
        if (language == fars) {
            zaban = 1;
        } else if (language == en) {
            zaban = 2;
        } else if (language == de) {
            zaban = 3;
        } else if (language == ca) {
            zaban = 2;
        } else if (language == am) {
            zaban = 2;
        } else if (language == ch) {
            zaban = 4;
        }

        try {

            Bundle colery = getIntent().getExtras();
            String colery_ = colery.getString("bmr_food_1");

            TextView s = (TextView) findViewById(R.id.colery_1);
            s.setText(colery_);

        } catch (Exception e) {
        }

        try {

            // Get the ViewPager and set it's PagerAdapter so that it can display items
            ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
            viewPager.setAdapter(new food_page_org_viewpager(getSupportFragmentManager()));

            // Give the PagerSlidingTabStrip the ViewPager
            PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            // Attach the view pager to the tab strip
            tabsStrip.setViewPager(viewPager);

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


        try {

            food_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
            food_editor = food_preferences.edit();
            CHECKED_STATE_5 = food_preferences.getBoolean("check_show_food", true);

        } catch (Exception e) {
        }


        token = food_preferences.getString("token", "");

        //az bein bordn shekle scrollview
        ScrollView sView = (ScrollView) findViewById(R.id.scrollview_1);
        sView.setVerticalScrollBarEnabled(false);
        sView.setHorizontalScrollBarEnabled(false);

        btn_menu = (ImageView) findViewById(R.id.btn_menu_2);
        diet = (LinearLayout) findViewById(R.id.lin_menu_d);
        exit = (LinearLayout) findViewById(R.id.lin_menu_ex);
        settings = (LinearLayout) findViewById(R.id.lin_menu_sit);
        laghv = (LinearLayout) findViewById(R.id.lin_menu_laghv);
        sports = (LinearLayout) findViewById(R.id.lin_menu_sp);
        help = (LinearLayout) findViewById(R.id.lin_menu_gu);
        btn_back_page2 = (ImageView) findViewById(R.id.btn_back_page2);


        btn_menu.setOnClickListener(this);
        diet.setOnClickListener(this);
        sports.setOnClickListener(this);
        settings.setOnClickListener(this);
        laghv.setOnClickListener(this);
        exit.setOnClickListener(this);
        help.setOnClickListener(this);
        btn_back_page2.setOnClickListener(this);

        //sliding view
        drawerlayout = (DrawerLayout) findViewById(R.id.root_food_page_org);

        menu_exit = (String) getText(R.string.menu_exit2);
        no = (String) getText(R.string.no);
        yes = (String) getText(R.string.yes);
        about_alertdialog_6 = "میخواهی بروی ؟";
        about_alertdialog_7 = "آیا مطمئن هستید؟";
        about_alertdialog_8 = "بعدا میبینمت";
        about_alertdialog_9 = "خداحافظ :)";



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_menu_2:
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

                    click = MediaPlayer.create(food_page_org.this, R.raw.button);
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

            case R.id.btn_back_page2:

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

                    click = MediaPlayer.create(food_page_org.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {

                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            food_page_org.this.overridePendingTransition(R.anim.out,
                                    R.anim.in);
                            finish();
                        }
                    }, _splashTime2);

                } catch (Exception e) {

                    finish();
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

                    click = MediaPlayer.create(food_page_org.this, R.raw.button);
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

                                        click = MediaPlayer.create(food_page_org.this, R.raw.button);
                                        if (music_check == music_off_check) {
                                            click.stop();
                                        } else {
                                            click.start();
                                        }

                                    } catch (Exception e) {

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

                    App.close();
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

                    click = MediaPlayer.create(food_page_org.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {

                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Intent about_2 = new Intent(food_page_org.this, com.android.fury.Dambel.menu_view.setting.class);
                            about_2.putExtra("setting_back", 5);
                            food_page_org.this.startActivity(about_2);
                            finish();
                        }
                    }, _splashTime2);

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

                    click = MediaPlayer.create(food_page_org.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {

                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Intent suport = new Intent(food_page_org.this, Suport_me.class);
                            startActivity(suport);
                        }
                    }, _splashTime2);

                } catch (Exception e) {
                }
                break;
            case R.id.lin_menu_laghv:

                setLaghv();

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

                    click = MediaPlayer.create(food_page_org.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }

                try {

                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Intent sport_menu = new Intent(food_page_org.this, sport.class);
                            sport_menu.putExtra("sport_back", 2);
                            food_page_org.this.startActivity(sport_menu);
                            finish();
                        }
                    }, _splashTime2);

                } catch (Exception e) {

                }


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

                    click = MediaPlayer.create(food_page_org.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {

                }


                if (CHECKED_STATE_5) {

                    try {

                        new Handler().postDelayed(new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                finish();
                            }
                        }, _splashTime2);

                    } catch (Exception e) {
                        finish();
                    }

                } else {
                    try {
                        new Handler().postDelayed(new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                String go_music = (String) getText(R.string.food_go_food);
                                Toast.makeText(food_page_org.this, go_music, Toast.LENGTH_LONG).show();
                            }
                        }, _splashTime2);
                    } catch (Exception e) {

                    }
                }


                break;


            //finish scorllview


            //start page


        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Toast.makeText(food_page_org.this, getResources().getText(R.string.about_alertdialog_8), Toast.LENGTH_LONG).show();
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

                click = MediaPlayer.create(food_page_org.this, R.raw.button);
                if (music_check == music_off_check) {
                    click.stop();
                } else {
                    click.start();
                }

            } catch (Exception e) {

            }

            try {

                food_page_org.this.overridePendingTransition(R.anim.out,
                        R.anim.in);

            } catch (Exception e) {

            }
            finish();
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        App.activity_food_page_org = this;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.activity_food_page_org = null;

    }


    public void setLaghv(){
        RequestQueue requestQueue = Volley.newRequestQueue(food_page_org.this);
        String url = "https://seller.jhoobin.com/ws/androidpublisher/v2/applications/com.android.fury.Dambel/purchases/subscriptions/Dambel_123/tokens/" + token + ":cancel?access_token=186e6f65-160b-3337-a4a5-59762e7eedf0";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("get" , String.valueOf(response));
                Toast.makeText(food_page_org.this, "اشتراک با موفقیت لغو شد", Toast.LENGTH_LONG).show();
                food_editor.putInt("one_play_app", 4);
                food_editor.apply();
                /*Intent uou = new Intent(food_page_org.this, Start_2.class);
                startActivity(uou);*/
                food_page_org.this.finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something
            }
        });

        requestQueue.add(jsonArrayRequest);
    }


}