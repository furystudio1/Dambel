package com.android.fury.Dambel.sport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.android.fury.Dambel.food.food;
import com.android.fury.Dambel.page.Page_2;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.Locale;

import it.sephiroth.android.library.tooltip.Tooltip;

/**
 * Created by fury on 10/11/2016.
 */
public class sport extends Activity implements View.OnClickListener {

    private final int _splashTime = 1000;
    private final int _splashTime2 = 500;

    Intent s2;
    private Tooltip.TooltipView mCurrentTooltip;

    ImageView back_page, btn_menu;

    LinearLayout sports, diet, settings, help, exit, laghv;

    String menu_exit, language1, about_alertdialog_6, about_alertdialog_7, about_alertdialog_8, about_alertdialog_9, no, yes, music_check, music_off_check, vibre_check, fars, en, de, ca, am, ch;

    DrawerLayout drawerlayout;

    MediaPlayer click;

    Vibrator vb;

    MyDatabaseHelper_sound sound_data;
    MyDatabaseHelper_vibre vibre_data;

    SharedPreferences sport_namber;

    SharedPreferences.Editor nameEditor_sport_namber;


    TextView comingso, txt_sport_2_day, txt_sport_3_day, txt_sport_4_day, txt_sport_5_day, txt_sport_6_day, txt_sport_7_day, txt_sport_8_day, txt_sport_9_day, txt_sport_10_day, txt_sport_11_day, txt_sport_12_day, txt_sport_13_day, txt_sport_14_day, txt_sport_15_day, txt_sport_16_day, txt_sport_17_day, txt_sport_18_day, txt_sport_19_day, txt_sport_20_day, txt_sport_21_day, txt_sport_22_day, txt_sport_23_day, txt_sport_24_day, txt_sport_25_day, txt_sport_26_day, txt_sport_27_day, txt_sport_28_day, txt_sport_29_day, txt_sport_30_day, txt_sport_1_day;

    ImageView btn_2_sport_star, btn_3_sport_star, btn_4_sport_star, btn_5_sport_star, btn_6_sport_star, btn_7_sport_star, btn_8_sport_star, btn_9_sport_star, btn_10_sport_star, btn_11_sport_star, btn_12_sport_star, btn_13_sport_star, btn_14_sport_star, btn_15_sport_star, btn_16_sport_star, btn_17_sport_star, btn_18_sport_star, btn_19_sport_star, btn_20_sport_star, btn_21_sport_star, btn_22_sport_star, btn_23_sport_star, btn_24_sport_star, btn_25_sport_star, btn_26_sport_star, btn_27_sport_star, btn_28_sport_star, btn_29_sport_star, btn_30_sport_star, btn_1_sport_star;

    CardView com_son_sport;

    int help_play, zaban, sport_page;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    MyDatabaseHelper_lan language;

    int look_1,look_2,look_3;

    RelativeLayout card_view_sport_1,card_view_sport_2,card_view_sport_3,card_view_sport_4,card_view_sport_5,card_view_sport_6,card_view_sport_7,card_view_sport_8,card_view_sport_9,card_view_sport_10,card_view_sport_11,card_view_sport_12,card_view_sport_13,card_view_sport_14,card_view_sport_15,card_view_sport_16,card_view_sport_17,card_view_sport_18,card_view_sport_19,card_view_sport_20,card_view_sport_21,card_view_sport_22,card_view_sport_23,card_view_sport_24,card_view_sport_25,card_view_sport_26,card_view_sport_27,card_view_sport_28,card_view_sport_29,card_view_sport_30;

    DisplayMetrics displayMetrics;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


        look_1 = 1;
        look_2 = 1;
        look_3 = 1;

        //start activ
        setContentView(R.layout.root_sport);

        Bundle sitting_b = getIntent().getExtras();

        try{
            sport_page = sitting_b.getInt("sport_back");
        }catch (Exception e){

        }

        try {

            one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
            one_play_editor = one_play_preferences.edit();
            help_play = one_play_preferences.getInt("Help_play_3", 1);

        } catch (Exception e) {
        }

        token = one_play_preferences.getString("token", "");

        fars = "fa";
        en = "en";
        de = "de";
        ca = "en";
        am = "en";
        ch = "zh";
        if (language1 == fars) {
            zaban = 1;
        } else if (language1 == en) {
            zaban = 2;
        } else if (language1 == de) {
            zaban = 3;
        } else if (language1 == ca) {
            zaban = 2;
        } else if (language1 == am) {
            zaban = 2;
        } else if (language1 == ch) {
            zaban = 4;
        }

        try {

            sport_namber = PreferenceManager.getDefaultSharedPreferences(this);
            nameEditor_sport_namber = sport_namber.edit();

        } catch (Exception e) {
        }


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


        txt_sport_2_day = (TextView) findViewById(R.id.txt_sport_2_day);
        txt_sport_3_day = (TextView) findViewById(R.id.txt_sport_3_day);
        txt_sport_4_day = (TextView) findViewById(R.id.txt_sport_4_day);
        txt_sport_5_day = (TextView) findViewById(R.id.txt_sport_5_day);
        txt_sport_6_day = (TextView) findViewById(R.id.txt_sport_6_day);
        txt_sport_7_day = (TextView) findViewById(R.id.txt_sport_7_day);
        txt_sport_8_day = (TextView) findViewById(R.id.txt_sport_8_day);
        txt_sport_9_day = (TextView) findViewById(R.id.txt_sport_9_day);
        txt_sport_10_day = (TextView) findViewById(R.id.txt_sport_10_day);
        txt_sport_11_day = (TextView) findViewById(R.id.txt_sport_11_day);
        txt_sport_12_day = (TextView) findViewById(R.id.txt_sport_12_day);
        txt_sport_13_day = (TextView) findViewById(R.id.txt_sport_13_day);
        txt_sport_14_day = (TextView) findViewById(R.id.txt_sport_14_day);
        txt_sport_15_day = (TextView) findViewById(R.id.txt_sport_15_day);
        txt_sport_16_day = (TextView) findViewById(R.id.txt_sport_16_day);
        txt_sport_17_day = (TextView) findViewById(R.id.txt_sport_17_day);
        txt_sport_18_day = (TextView) findViewById(R.id.txt_sport_18_day);
        txt_sport_19_day = (TextView) findViewById(R.id.txt_sport_19_day);
        txt_sport_20_day = (TextView) findViewById(R.id.txt_sport_20_day);
        txt_sport_21_day = (TextView) findViewById(R.id.txt_sport_21_day);
        txt_sport_22_day = (TextView) findViewById(R.id.txt_sport_22_day);
        txt_sport_23_day = (TextView) findViewById(R.id.txt_sport_23_day);
        txt_sport_24_day = (TextView) findViewById(R.id.txt_sport_24_day);
        txt_sport_25_day = (TextView) findViewById(R.id.txt_sport_25_day);
        txt_sport_26_day = (TextView) findViewById(R.id.txt_sport_26_day);
        txt_sport_27_day = (TextView) findViewById(R.id.txt_sport_27_day);
        txt_sport_28_day = (TextView) findViewById(R.id.txt_sport_28_day);
        txt_sport_29_day = (TextView) findViewById(R.id.txt_sport_29_day);
        txt_sport_30_day = (TextView) findViewById(R.id.txt_sport_30_day);
        txt_sport_1_day = (TextView) findViewById(R.id.txt_sport_1_day);
        comingso = (TextView) findViewById(R.id.comingso);

        btn_2_sport_star = (ImageView) findViewById(R.id.btn_2_sport_star);
        btn_3_sport_star = (ImageView) findViewById(R.id.btn_3_sport_star);
        btn_4_sport_star = (ImageView) findViewById(R.id.btn_4_sport_star);
        btn_5_sport_star = (ImageView) findViewById(R.id.btn_5_sport_star);
        btn_6_sport_star = (ImageView) findViewById(R.id.btn_6_sport_star);
        btn_7_sport_star = (ImageView) findViewById(R.id.btn_7_sport_star);
        btn_8_sport_star = (ImageView) findViewById(R.id.btn_8_sport_star);
        btn_9_sport_star = (ImageView) findViewById(R.id.btn_9_sport_star);
        btn_10_sport_star = (ImageView) findViewById(R.id.btn_10_sport_star);
        btn_11_sport_star = (ImageView) findViewById(R.id.btn_11_sport_star);
        btn_12_sport_star = (ImageView) findViewById(R.id.btn_12_sport_star);
        btn_13_sport_star = (ImageView) findViewById(R.id.btn_13_sport_star);
        btn_14_sport_star = (ImageView) findViewById(R.id.btn_14_sport_star);
        btn_15_sport_star = (ImageView) findViewById(R.id.btn_15_sport_star);
        btn_16_sport_star = (ImageView) findViewById(R.id.btn_16_sport_star);
        btn_17_sport_star = (ImageView) findViewById(R.id.btn_17_sport_star);
        btn_18_sport_star = (ImageView) findViewById(R.id.btn_18_sport_star);
        btn_19_sport_star = (ImageView) findViewById(R.id.btn_19_sport_star);
        btn_20_sport_star = (ImageView) findViewById(R.id.btn_20_sport_star);
        btn_21_sport_star = (ImageView) findViewById(R.id.btn_21_sport_star);
        btn_22_sport_star = (ImageView) findViewById(R.id.btn_22_sport_star);
        btn_23_sport_star = (ImageView) findViewById(R.id.btn_23_sport_star);
        btn_24_sport_star = (ImageView) findViewById(R.id.btn_24_sport_star);
        btn_25_sport_star = (ImageView) findViewById(R.id.btn_25_sport_star);
        btn_26_sport_star = (ImageView) findViewById(R.id.btn_26_sport_star);
        btn_27_sport_star = (ImageView) findViewById(R.id.btn_27_sport_star);
        btn_28_sport_star = (ImageView) findViewById(R.id.btn_28_sport_star);
        btn_29_sport_star = (ImageView) findViewById(R.id.btn_29_sport_star);
        btn_30_sport_star = (ImageView) findViewById(R.id.btn_30_sport_star);
        btn_1_sport_star = (ImageView) findViewById(R.id.btn_1_sport_star);
        com_son_sport = (CardView) findViewById(R.id.com_son_sport);

        card_view_sport_1 = (RelativeLayout) findViewById(R.id.card_view_sport_1);
        card_view_sport_2 = (RelativeLayout) findViewById(R.id.card_view_sport_2);
        card_view_sport_3 = (RelativeLayout) findViewById(R.id.card_view_sport_3);
        card_view_sport_4 = (RelativeLayout) findViewById(R.id.card_view_sport_4);
        card_view_sport_5 = (RelativeLayout) findViewById(R.id.card_view_sport_5);
        card_view_sport_6 = (RelativeLayout) findViewById(R.id.card_view_sport_6);
        card_view_sport_7 = (RelativeLayout) findViewById(R.id.card_view_sport_7);
        card_view_sport_8 = (RelativeLayout) findViewById(R.id.card_view_sport_8);
        card_view_sport_9 = (RelativeLayout) findViewById(R.id.card_view_sport_9);
        card_view_sport_10 = (RelativeLayout) findViewById(R.id.card_view_sport_10);
        card_view_sport_11 = (RelativeLayout) findViewById(R.id.card_view_sport_11);
        card_view_sport_12 = (RelativeLayout) findViewById(R.id.card_view_sport_12);
        card_view_sport_13 = (RelativeLayout) findViewById(R.id.card_view_sport_13);
        card_view_sport_14 = (RelativeLayout) findViewById(R.id.card_view_sport_14);
        card_view_sport_15 = (RelativeLayout) findViewById(R.id.card_view_sport_15);
        card_view_sport_16 = (RelativeLayout) findViewById(R.id.card_view_sport_16);
        card_view_sport_17 = (RelativeLayout) findViewById(R.id.card_view_sport_17);
        card_view_sport_18 = (RelativeLayout) findViewById(R.id.card_view_sport_18);
        card_view_sport_19 = (RelativeLayout) findViewById(R.id.card_view_sport_19);
        card_view_sport_20 = (RelativeLayout) findViewById(R.id.card_view_sport_20);
        card_view_sport_21 = (RelativeLayout) findViewById(R.id.card_view_sport_21);
        card_view_sport_22 = (RelativeLayout) findViewById(R.id.card_view_sport_22);
        card_view_sport_23 = (RelativeLayout) findViewById(R.id.card_view_sport_23);
        card_view_sport_24 = (RelativeLayout) findViewById(R.id.card_view_sport_24);
        card_view_sport_25 = (RelativeLayout) findViewById(R.id.card_view_sport_25);
        card_view_sport_26 = (RelativeLayout) findViewById(R.id.card_view_sport_26);
        card_view_sport_27 = (RelativeLayout) findViewById(R.id.card_view_sport_27);
        card_view_sport_28 = (RelativeLayout) findViewById(R.id.card_view_sport_28);
        card_view_sport_29 = (RelativeLayout) findViewById(R.id.card_view_sport_29);
        card_view_sport_30 = (RelativeLayout) findViewById(R.id.card_view_sport_30);

        card_view_sport_1.setOnClickListener(this);
        card_view_sport_2.setOnClickListener(this);
        card_view_sport_3.setOnClickListener(this);
        card_view_sport_4.setOnClickListener(this);
        card_view_sport_5.setOnClickListener(this);
        card_view_sport_6.setOnClickListener(this);
        card_view_sport_7.setOnClickListener(this);
        card_view_sport_8.setOnClickListener(this);
        card_view_sport_9.setOnClickListener(this);
        card_view_sport_10.setOnClickListener(this);
        card_view_sport_11.setOnClickListener(this);
        card_view_sport_12.setOnClickListener(this);
        card_view_sport_13.setOnClickListener(this);
        card_view_sport_14.setOnClickListener(this);
        card_view_sport_15.setOnClickListener(this);
        card_view_sport_16.setOnClickListener(this);
        card_view_sport_17.setOnClickListener(this);
        card_view_sport_18.setOnClickListener(this);
        card_view_sport_19.setOnClickListener(this);
        card_view_sport_20.setOnClickListener(this);
        card_view_sport_21.setOnClickListener(this);
        card_view_sport_22.setOnClickListener(this);
        card_view_sport_23.setOnClickListener(this);
        card_view_sport_24.setOnClickListener(this);
        card_view_sport_25.setOnClickListener(this);
        card_view_sport_26.setOnClickListener(this);
        card_view_sport_27.setOnClickListener(this);
        card_view_sport_28.setOnClickListener(this);
        card_view_sport_29.setOnClickListener(this);
        card_view_sport_30.setOnClickListener(this);
        com_son_sport.setOnClickListener(this);


        int s1 = 1;
        int s2 = 1;
        int s3 = 1;
        int sport_1_1 = 11;
        int sport_1_2 = 12;
        int sport_1_3 = 13;
        int sport_1_4 = 14;
        int sport_1_5 = 15;
        int sport_1_6 = 16;
        int sport_1_7 = 17;
        int sport_1_8 = 18;
        int sport_1_9 = 19;
        int sport_1 = sport_namber.getInt("sport_number_show_set_1", 0);
        int sport_1_1_s = sport_namber.getInt("sport_number_show_11", 0);
        int sport_1_2_s = sport_namber.getInt("sport_number_show_12", 0);
        int sport_1_3_s = sport_namber.getInt("sport_number_show_13", 0);
        int sport_1_4_s = sport_namber.getInt("sport_number_show_14", 0);
        int sport_1_5_s = sport_namber.getInt("sport_number_show_15", 0);
        int sport_1_6_s = sport_namber.getInt("sport_number_show_16", 0);
        int sport_1_7_s = sport_namber.getInt("sport_number_show_17", 0);
        int sport_1_8_s = sport_namber.getInt("sport_number_show_18", 0);
        int sport_1_9_s = sport_namber.getInt("sport_number_show_19", 0);
        if (s1 == sport_1) {

            look_1 = 2;

            try {

                com_son_sport.setVisibility(View.VISIBLE);

            } catch (Exception e) {
            }


            if (sport_1_1 == sport_1_1_s) {

                try {

                    card_view_sport_1.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_1_sport_star.setVisibility(View.VISIBLE);
                    btn_1_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_1_day.setVisibility(View.GONE);

                } catch (Exception e) {
                }

            } else {

                try {

                    card_view_sport_1.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_1_sport_star.setVisibility(View.GONE);
                    txt_sport_1_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                }


            }

            if (sport_1_2 == sport_1_2_s) {

                try {

                    card_view_sport_2.setBackgroundResource(R.drawable.back_sport_start_open);

                    btn_2_sport_star.setVisibility(View.VISIBLE);
                    btn_2_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_2_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {
                    card_view_sport_2.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_1_sport_star.setVisibility(View.GONE);
                    txt_sport_2_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_1_3 == sport_1_3_s) {

                try {

                    card_view_sport_3.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_3_sport_star.setVisibility(View.VISIBLE);
                    btn_3_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_3_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_3.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_3_sport_star.setVisibility(View.GONE);
                    txt_sport_3_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_1_4 == sport_1_4_s) {

                try {

                    card_view_sport_4.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_4_sport_star.setVisibility(View.VISIBLE);
                    btn_4_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_4_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_4.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_4_sport_star.setVisibility(View.GONE);
                    txt_sport_4_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }
            if (sport_1_5 == sport_1_5_s) {

                try {

                    card_view_sport_5.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_5_sport_star.setVisibility(View.VISIBLE);
                    btn_5_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_5_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_5.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_5_sport_star.setVisibility(View.GONE);
                    txt_sport_5_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_1_6 == sport_1_6_s) {

                try {

                    card_view_sport_6.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_6_sport_star.setVisibility(View.VISIBLE);
                    btn_6_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_6_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_6.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_6_sport_star.setVisibility(View.GONE);
                    txt_sport_6_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_1_7 == sport_1_7_s) {

                try {

                    card_view_sport_7.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_7_sport_star.setVisibility(View.VISIBLE);
                    btn_7_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_7_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_7.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_7_sport_star.setVisibility(View.GONE);
                    txt_sport_7_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_1_8 == sport_1_8_s) {

                try {

                    card_view_sport_8.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_8_sport_star.setVisibility(View.VISIBLE);
                    btn_8_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_8_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_8.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_8_sport_star.setVisibility(View.GONE);
                    txt_sport_8_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_1_9 == sport_1_9_s) {

                try {

                    card_view_sport_9.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_9_sport_star.setVisibility(View.VISIBLE);
                    btn_9_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_9_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_9.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_9_sport_star.setVisibility(View.GONE);
                    txt_sport_9_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }


        } else {

            try {

                card_view_sport_2.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_2_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_2_day.setVisibility(View.GONE);

                card_view_sport_3.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_3_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_3_day.setVisibility(View.INVISIBLE);

                card_view_sport_4.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_4_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_4_day.setVisibility(View.INVISIBLE);

                card_view_sport_5.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_5_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_5_day.setVisibility(View.INVISIBLE);

                card_view_sport_6.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_6_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_6_day.setVisibility(View.INVISIBLE);

                card_view_sport_7.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_7_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_7_day.setVisibility(View.INVISIBLE);

                card_view_sport_8.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_8_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_8_day.setVisibility(View.INVISIBLE);

                card_view_sport_9.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_9_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_9_day.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                }


        }


        int sport_2_1 = 11;
        int sport_2_2 = 12;
        int sport_2_3 = 13;
        int sport_2_4 = 14;
        int sport_2_5 = 15;
        int sport_2_6 = 16;
        int sport_2_7 = 17;
        int sport_2_8 = 18;
        int sport_2_9 = 19;
        int sport_2_10 = 20;
        int sport_2 = sport_namber.getInt("sport_number_show_set_2", 0);
        int sport_2_1_s = sport_namber.getInt("sport_number_show_21", 0);
        int sport_2_2_s = sport_namber.getInt("sport_number_show_22", 0);
        int sport_2_3_s = sport_namber.getInt("sport_number_show_23", 0);
        int sport_2_4_s = sport_namber.getInt("sport_number_show_24", 0);
        int sport_2_5_s = sport_namber.getInt("sport_number_show_25", 0);
        int sport_2_6_s = sport_namber.getInt("sport_number_show_26", 0);
        int sport_2_7_s = sport_namber.getInt("sport_number_show_27", 0);
        int sport_2_8_s = sport_namber.getInt("sport_number_show_28", 0);
        int sport_2_9_s = sport_namber.getInt("sport_number_show_29", 0);
        int sport_2_10_s = sport_namber.getInt("sport_number_show_20", 0);

        if (s2 == sport_2) {

            look_2 = 2;


            if (sport_2_1 == sport_2_1_s) {

                try {


                    card_view_sport_10.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_10_sport_star.setVisibility(View.VISIBLE);
                    btn_10_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_10_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_10.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_10_sport_star.setVisibility(View.GONE);
                    txt_sport_10_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_2 == sport_2_2_s) {

                try {

                    card_view_sport_11.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_11_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_11_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_11.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_11_sport_star.setVisibility(View.GONE);
                    txt_sport_11_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_3 == sport_2_3_s) {

                try {

                    card_view_sport_12.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_12_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_12_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_12.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_12_sport_star.setVisibility(View.GONE);
                    txt_sport_12_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_4 == sport_2_4_s) {

                try {

                    card_view_sport_13.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_13_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_13_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_13.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_13_sport_star.setVisibility(View.GONE);
                    txt_sport_13_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }
            if (sport_2_5 == sport_2_5_s) {

                try {

                    card_view_sport_14.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_14_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_14_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_14.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_14_sport_star.setVisibility(View.GONE);
                    txt_sport_14_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_6 == sport_2_6_s) {

                try {

                    card_view_sport_15.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_15_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_15_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_15.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_15_sport_star.setVisibility(View.GONE);
                    txt_sport_15_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_7 == sport_2_7_s) {

                try {

                    card_view_sport_16.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_16_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_16_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_16.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_16_sport_star.setVisibility(View.GONE);
                    txt_sport_16_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_8 == sport_2_8_s) {

                try {

                    card_view_sport_17.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_17_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_17_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_17.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_17_sport_star.setVisibility(View.GONE);
                    txt_sport_17_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_9 == sport_2_9_s) {

                try {

                    card_view_sport_18.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_18_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_18_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_18.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_18_sport_star.setVisibility(View.GONE);
                    txt_sport_18_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_2_10 == sport_2_10_s) {

                try {

                    card_view_sport_19.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_19_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_19_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_19.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_19_sport_star.setVisibility(View.GONE);
                    txt_sport_19_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }


        } else {

            try {

                card_view_sport_11.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_11_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_11_day.setVisibility(View.INVISIBLE);

                card_view_sport_12.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_12_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_12_day.setVisibility(View.INVISIBLE);

                card_view_sport_13.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_13_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_13_day.setVisibility(View.INVISIBLE);

                card_view_sport_14.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_14_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_14_day.setVisibility(View.INVISIBLE);

                card_view_sport_15.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_15_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_15_day.setVisibility(View.INVISIBLE);

                card_view_sport_16.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_16_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_16_day.setVisibility(View.INVISIBLE);

                card_view_sport_17.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_17_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_17_day.setVisibility(View.INVISIBLE);

                card_view_sport_18.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_18_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_18_day.setVisibility(View.INVISIBLE);

                card_view_sport_19.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_19_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_19_day.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                }


        }


        int sport_3_1 = 11;
        int sport_3_2 = 12;
        int sport_3_3 = 13;
        int sport_3_4 = 14;
        int sport_3_5 = 15;
        int sport_3_6 = 16;
        int sport_3_7 = 17;
        int sport_3_8 = 18;
        int sport_3_9 = 19;
        int sport_3_10 = 20;
        int sport_3_11 = 30;
        int sport_3 = sport_namber.getInt("sport_number_show_set_3", 0);
        int sport_3_1_s = sport_namber.getInt("sport_number_show_31", 0);
        int sport_3_2_s = sport_namber.getInt("sport_number_show_32", 0);
        int sport_3_3_s = sport_namber.getInt("sport_number_show_33", 0);
        int sport_3_4_s = sport_namber.getInt("sport_number_show_34", 0);
        int sport_3_5_s = sport_namber.getInt("sport_number_show_35", 0);
        int sport_3_6_s = sport_namber.getInt("sport_number_show_36", 0);
        int sport_3_7_s = sport_namber.getInt("sport_number_show_37", 0);
        int sport_3_8_s = sport_namber.getInt("sport_number_show_38", 0);
        int sport_3_9_s = sport_namber.getInt("sport_number_show_39", 0);
        int sport_3_10_s = sport_namber.getInt("sport_number_show_30", 0);
        int sport_3_11_s = sport_namber.getInt("sport_number_show_40", 0);


        if (s3 == sport_3) {

            look_3 = 2;

            if (sport_3_1 == sport_3_1_s) {

                try {

                    card_view_sport_20.setBackgroundResource(R.drawable.back_sport_start_open);

                    btn_20_sport_star.setVisibility(View.VISIBLE);
                    btn_20_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_20_day.setVisibility(View.GONE);


                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_20.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_20_sport_star.setVisibility(View.GONE);
                    txt_sport_20_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_2 == sport_3_2_s) {

                try {

                    card_view_sport_21.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_21_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_21_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_21.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_21_sport_star.setVisibility(View.GONE);
                    txt_sport_21_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_3 == sport_3_3_s) {

                try {

                    card_view_sport_22.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_22_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_22_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_22.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_22_sport_star.setVisibility(View.GONE);
                    txt_sport_22_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_4 == sport_3_4_s) {

                try {

                    card_view_sport_23.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_23_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_23_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_23.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_23_sport_star.setVisibility(View.GONE);
                    txt_sport_23_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }
            if (sport_3_5 == sport_3_5_s) {

                try {

                    card_view_sport_24.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_24_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_24_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_24.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_24_sport_star.setVisibility(View.GONE);
                    txt_sport_24_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_6 == sport_3_6_s) {

                try {

                    card_view_sport_25.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_25_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_25_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_25.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_25_sport_star.setVisibility(View.GONE);
                    txt_sport_25_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_7 == sport_3_7_s) {

                try {

                    card_view_sport_26.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_26_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_26_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_26.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_26_sport_star.setVisibility(View.GONE);
                    txt_sport_26_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_8 == sport_3_8_s) {

                try {

                    card_view_sport_27.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_27_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_27_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_27.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_27_sport_star.setVisibility(View.GONE);
                    txt_sport_27_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_9 == sport_3_9_s) {

                try {

                    card_view_sport_28.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_28_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_28_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_28.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_28_sport_star.setVisibility(View.GONE);
                    txt_sport_28_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_10 == sport_3_10_s) {

                try {

                    card_view_sport_29.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_29_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_29_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_29.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_29_sport_star.setVisibility(View.GONE);
                    txt_sport_29_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }

            if (sport_3_11 == sport_3_11_s) {

                try {

                    card_view_sport_30.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_30_sport_star.setImageResource(R.drawable.circle_day_star);
                    txt_sport_30_day.setVisibility(View.GONE);

                } catch (Exception e) {
                    }


            } else {

                try {

                    card_view_sport_30.setBackgroundResource(R.drawable.back_sport_start_open);
                    btn_30_sport_star.setVisibility(View.GONE);
                    txt_sport_30_day.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    }


            }


        } else {

            try {

                card_view_sport_21.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_21_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_21_day.setVisibility(View.INVISIBLE);

                card_view_sport_22.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_22_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_22_day.setVisibility(View.INVISIBLE);

                card_view_sport_23.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_23_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_23_day.setVisibility(View.INVISIBLE);

                card_view_sport_24.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_24_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_24_day.setVisibility(View.INVISIBLE);

                card_view_sport_25.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_25_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_25_day.setVisibility(View.INVISIBLE);

                card_view_sport_26.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_26_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_26_day.setVisibility(View.INVISIBLE);

                card_view_sport_27.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_27_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_27_day.setVisibility(View.INVISIBLE);

                card_view_sport_28.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_28_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_28_day.setVisibility(View.INVISIBLE);

                card_view_sport_29.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_29_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_29_day.setVisibility(View.INVISIBLE);

                card_view_sport_30.setBackgroundResource(R.drawable.back_sport_start_look);
                btn_30_sport_star.setImageResource(R.drawable.lock_day);
                txt_sport_30_day.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                }


        }


//az bein bordn shekle scrollview
        ScrollView sView = (ScrollView) findViewById(R.id.scrollview_2);
        sView.setVerticalScrollBarEnabled(false);
        sView.setHorizontalScrollBarEnabled(false);

        //az bein bordn shekle scrollview
        ScrollView sView2 = (ScrollView) findViewById(R.id.scrollView_sport);
        sView2.setVerticalScrollBarEnabled(false);
        sView2.setHorizontalScrollBarEnabled(false);


        about_alertdialog_6 = (String) getText(R.string.about_alertdialog_6);
        about_alertdialog_7 = (String) getText(R.string.about_alertdialog_7);
        about_alertdialog_8 = (String) getText(R.string.about_alertdialog_8);
        about_alertdialog_9 = (String) getText(R.string.about_alertdialog_9);
        menu_exit = (String) getText(R.string.menu_exit2);
        no = (String) getText(R.string.no);
        yes = (String) getText(R.string.yes);


        diet = (LinearLayout) findViewById(R.id.lin_menu_d_1);
        exit = (LinearLayout) findViewById(R.id.lin_menu_ex_1);
        settings = (LinearLayout) findViewById(R.id.lin_menu_sit_1);
        sports = (LinearLayout) findViewById(R.id.lin_menu_sp_1);
        laghv = (LinearLayout) findViewById(R.id.lin_menu_laghv);
        help = (LinearLayout) findViewById(R.id.lin_menu_gu_1);


        diet.setOnClickListener(this);
        sports.setOnClickListener(this);
        laghv.setOnClickListener(this);
        settings.setOnClickListener(this);
        help.setOnClickListener(this);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    new SweetAlertDialog(sport.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(about_alertdialog_7)
                            .setContentText(about_alertdialog_6)
                            .setCancelText(no)
                            .setConfirmText(yes)
                            .showCancelButton(true)
                            .setCancelClickListener(null)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {


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


            }
        });


        drawerlayout = (DrawerLayout) findViewById(R.id.root_sport);

        back_page = (ImageView) findViewById(R.id.btn_back_page2);
        btn_menu = (ImageView) findViewById(R.id.btn_menu);

        back_page.setOnClickListener(this);
        btn_menu.setOnClickListener(this);

        if (help_play == 1) {

            displayMetrics = getResources().getDisplayMetrics();
            String s11 = (String) getText(R.string.Help_start_3_1);
            String s22 = (String) getText(R.string.Help_start_3_2);
            String s33 = s11 + System.getProperty("line.separator") + "" + System.getProperty("line.separator") + s22 ;

            try {

                mCurrentTooltip = Tooltip.make(
                        sport.this,
                        new Tooltip.Builder(101)
                                .maxWidth((int) (displayMetrics.widthPixels / 2))
                                .anchor(card_view_sport_1, Tooltip.Gravity.BOTTOM)
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

        on_touch(card_view_sport_1 );
        on_touch(card_view_sport_2 );
        on_touch(card_view_sport_3 );
        on_touch(card_view_sport_4 );
        on_touch(card_view_sport_5 );
        on_touch(card_view_sport_6 );
        on_touch(card_view_sport_7 );
        on_touch(card_view_sport_8 );
        on_touch(card_view_sport_9 );
        on_touch(card_view_sport_10);
        on_touch(card_view_sport_11);
        on_touch(card_view_sport_12);
        on_touch(card_view_sport_13);
        on_touch(card_view_sport_14);
        on_touch(card_view_sport_15);
        on_touch(card_view_sport_16);
        on_touch(card_view_sport_17);
        on_touch(card_view_sport_18);
        on_touch(card_view_sport_19);
        on_touch(card_view_sport_20);
        on_touch(card_view_sport_21);
        on_touch(card_view_sport_22);
        on_touch(card_view_sport_23);
        on_touch(card_view_sport_24);
        on_touch(card_view_sport_25);
        on_touch(card_view_sport_26);
        on_touch(card_view_sport_27);
        on_touch(card_view_sport_28);
        on_touch(card_view_sport_29);
        on_touch(card_view_sport_30);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
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

                    click = MediaPlayer.create(sport.this, R.raw.button);
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

                            if (sport_page == 2) {
                                Intent ssss = new Intent(sport.this, Page_2.class);
                                startActivity(ssss);
                            } else if (sport_page == 4) {
                                Intent ssss = new Intent(sport.this, food.class);
                                ssss.putExtra("zaban_fo", zaban);
                                startActivity(ssss);
                            } else {

                            }

                            finish();
                        }
                    }, _splashTime2);

                } catch (Exception e) {
                    }


                break;

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

                    click = MediaPlayer.create(sport.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }

                } catch (Exception e) {
                    }


                if (drawerlayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerlayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerlayout.openDrawer(Gravity.LEFT);
                }
                break;

            case R.id.lin_menu_sit_1:

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

                    click = MediaPlayer.create(sport.this, R.raw.button);
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
                            Intent about_2 = new Intent(sport.this, com.android.fury.Dambel.menu_view.setting.class);
                            about_2.putExtra("setting_back", 3);
                            sport.this.startActivity(about_2);
                            sport.this.finish();
                        }
                    }, _splashTime2);

                } catch (Exception e) {
                    }

                break;

            case R.id.lin_menu_gu_1:

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

                    click = MediaPlayer.create(sport.this, R.raw.button);
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
                            Intent suport = new Intent(sport.this, Suport_me.class);
                            startActivity(suport);
                        }
                    }, _splashTime2);

                } catch (Exception e) {
                }
                break;

            case R.id.lin_menu_sp_1:

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

                    click = MediaPlayer.create(sport.this, R.raw.button);
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
                        String go_music = (String) getText(R.string.sport_go_sport);
                        Toast.makeText(sport.this, go_music, Toast.LENGTH_LONG).show();
                    }
                }, _splashTime2);

                break;

            case R.id.lin_menu_d_1:
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

                    click = MediaPlayer.create(sport.this, R.raw.button);
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
                        Intent food_ = new Intent(sport.this, food.class);
                        food_.putExtra("zaban_fo", zaban);
                        sport.this.startActivity(food_);
                        sport.this.finish();
                    }
                }, _splashTime2);


                break;

            case R.id.card_view_sport_1:

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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }

                if (help_play == 1) {

                    if (null != mCurrentTooltip) {
                        mCurrentTooltip.hide();
                        mCurrentTooltip = null;
                    }

                    one_play_editor.putInt("Help_play_3", 2);
                    one_play_editor.commit();
                }


                    Intent s = new Intent(sport.this, sport_start.class);
                    s.putExtra("sport_v_number_day", 1);
                    s.putExtra("sport_v_number_day_set_1", 1);
                    s.putExtra("sport_v_volume1", 1);
                    s.putExtra("sport_v_por", 1);
                    startActivity(s);

                    sport.this.finish();

                break;

            case R.id.card_view_sport_2:
                if (look_1 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    Intent s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 2);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);

                    sport.this.finish();
                }

                break;

            case R.id.card_view_sport_3:
                if (look_1 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }

                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 3);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;

            case R.id.card_view_sport_4:
                if (look_1 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 4);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;

            case R.id.card_view_sport_5:
                if (look_1 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 5);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;

            case R.id.card_view_sport_6:
                if (look_1 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 6);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);

                    sport.this.finish();
                }
                break;

            case R.id.card_view_sport_7:
                if (look_1 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 7);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                    break;

                    case R.id.card_view_sport_8:
                        if (look_1 == 1){

                        }else {
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

                            click = MediaPlayer.create(sport.this, R.raw.button);
                            if (music_check == music_off_check) {
                                click.stop();
                            } else {
                                click.start();
                            }

                        } catch (Exception e) {
                        }


                        s2 = new Intent(sport.this, sport_start.class);
                        s2.putExtra("sport_v_number_day", 8);
                        s2.putExtra("sport_v_number_day_set_1", 1);
                        s2.putExtra("sport_v_volume1", 1);
                        s2.putExtra("sport_v_por", 1);
                        startActivity(s2);


                        sport.this.finish();
                }
                break;

            case R.id.card_view_sport_9:
                if (look_1 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 9);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_10:

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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 10);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();

                break;


            case R.id.card_view_sport_11:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 11);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_12:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 12);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;




            case R.id.card_view_sport_13:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 13);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();

                }
                break;


            case R.id.card_view_sport_14:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 14);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);

                    sport.this.finish();
                }
                break;



            case R.id.card_view_sport_15:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 15);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);

                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_16:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 16);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_17:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 17);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_18:

                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 18);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_19:
                if (look_2 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 19);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();

                }
                break;



            case R.id.card_view_sport_20:
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 20);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();

                break;

            case R.id.lin_menu_laghv:

                setLaghv();

                break;
            case R.id.card_view_sport_21:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 21);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_22:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 22);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_23:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 23);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_24:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 24);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_25:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 25);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_26:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 26);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_27:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 27);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_28:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 28);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_29:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 29);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;


            case R.id.card_view_sport_30:
                if (look_3 == 1){

                }else {
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

                        click = MediaPlayer.create(sport.this, R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }


                    s2 = new Intent(sport.this, sport_start.class);
                    s2.putExtra("sport_v_number_day", 30);
                    s2.putExtra("sport_v_number_day_set_1", 1);
                    s2.putExtra("sport_v_volume1", 1);
                    s2.putExtra("sport_v_por", 1);
                    startActivity(s2);


                    sport.this.finish();
                }
                break;

            case R.id.com_son_sport:

                final Intent sport_me = new Intent(this, Sport_me_start.class);

                try {
                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            startActivity(sport_me);
                            finish();
                        }
                    }, _splashTime2);

                } catch (Exception e) {


                    Toast.makeText(this, "متاسفانه حافظه رم بیش از حد پر است . حافظه را خالی کنید و دباره تلاش کنید", Toast.LENGTH_LONG).show();

                }


                break;


        }
    }


    public void on_touch(final RelativeLayout a){
        if (look_3 == 2) {
            a.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            a.setBackgroundResource(R.drawable.back_sport_start_open_on);
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            a.setBackgroundResource(R.drawable.back_sport_start_open);
                            break;
                        }
                    }
                    return false;
                }
            });
        }

            if (look_2 == 2) {
                a.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                a.setBackgroundResource(R.drawable.back_sport_start_open_on);
                                break;
                            }
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL: {
                                a.setBackgroundResource(R.drawable.back_sport_start_open);
                                break;
                            }
                        }
                        return false;
                    }
                });
            }

            if (look_1 == 2) {
                a.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                a.setBackgroundResource(R.drawable.back_sport_start_open_on);
                                break;
                            }
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL: {
                                a.setBackgroundResource(R.drawable.back_sport_start_open);
                                break;
                            }
                        }
                        return false;
                    }
                });
            }

        if (look_1 == 1){
            if (a == card_view_sport_1){
                a.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()){
                            case MotionEvent.ACTION_DOWN:{
                                a.setBackgroundResource(R.drawable.back_sport_start_open_on);
                                break;
                            }
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:{
                                a.setBackgroundResource(R.drawable.back_sport_start_open);
                                break;
                            }
                        }
                        return false;
                    }
                });
            }
        }

        if (look_2 == 1){
            if (a == card_view_sport_10){
                a.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()){
                            case MotionEvent.ACTION_DOWN:{
                                a.setBackgroundResource(R.drawable.back_sport_start_open_on);
                                break;
                            }
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:{
                                a.setBackgroundResource(R.drawable.back_sport_start_open);
                                break;
                            }
                        }
                        return false;
                    }
                });
            }
        }

        if (look_3 == 1){
            if (a == card_view_sport_20){
                a.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()){
                            case MotionEvent.ACTION_DOWN:{
                                a.setBackgroundResource(R.drawable.back_sport_start_open_on);
                                break;
                            }
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:{
                                a.setBackgroundResource(R.drawable.back_sport_start_open);
                                break;
                            }
                        }
                        return false;
                    }
                });
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Toast.makeText(sport.this, getResources().getText(R.string.about_alertdialog_8), Toast.LENGTH_LONG).show();
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
                click = MediaPlayer.create(sport.this, R.raw.button);
                if (music_check == music_off_check) {
                    click.stop();
                } else {
                    click.start();
                }

            } catch (Exception e) {

            }


            if (sport_page == 2) {
                try {
                    Intent ssss = new Intent(sport.this, Page_2.class);
                    startActivity(ssss);

                } catch (Exception e) {

                }

            } else if (sport_page == 4) {
                try {
                    Intent ssss = new Intent(sport.this, food.class);
                    ssss.putExtra("zaban_fo", zaban);
                    startActivity(ssss);

                } catch (Exception e) {

                }

            } else {

            }

            try {
                this.overridePendingTransition(R.anim.out,
                        R.anim.in);

            } catch (Exception e) {

            }

            finish();
        }
        return false;
    }


    @Override
    public void onStart() {
        super.onStart();

        App.activity_sport_start = this;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        App.activity_sport_start = null;

    }


    public void setLaghv(){
        RequestQueue requestQueue = Volley.newRequestQueue(sport.this);
        String url = "https://seller.jhoobin.com/ws/androidpublisher/v2/applications/com.android.fury.Dambel/purchases/subscriptions/Dambel_123/tokens/" + token + ":cancel?access_token=186e6f65-160b-3337-a4a5-59762e7eedf0";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("get" , String.valueOf(response));

                Toast.makeText(sport.this, "اشتراک با موفقیت لغو شد", Toast.LENGTH_LONG).show();
                one_play_editor.putInt("one_play_app", 3);
                one_play_editor.apply();
                /*Intent uou = new Intent(sport.this, Start_2.class);
                startActivity(uou);*/
                sport.this.finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something
                Toast.makeText(sport.this, "اشتراک با موفقیت لغو شد", Toast.LENGTH_LONG).show();
                one_play_editor.putInt("one_play_app", 3);
                one_play_editor.apply();
                /*Intent uou = new Intent(Page_2.this, Start_2.class);
                startActivity(uou);*/
                sport.this.finish();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }


}
