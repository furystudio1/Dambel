package com.android.fury.Dambel.sport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fury.Dambel.Database.MyDatabaseHelper_lan;
import com.android.fury.Dambel.Database.MyDatabaseHelper_music;
import com.android.fury.Dambel.Database.MyDatabaseHelper_sound;
import com.android.fury.Dambel.Database.MyDatabaseHelper_vibre;
import com.android.fury.Dambel.Database.MyDatabaseHelper_voice;
import com.android.fury.Dambel.R;
import com.android.fury.Dambel.alert.SweetAlertDialog;
import com.android.fury.Dambel.app_net;
import com.android.fury.Dambel.page.Page_2;

import java.util.Locale;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import it.sephiroth.android.library.tooltip.Tooltip;

public class sport_v extends Activity implements View.OnClickListener {

    private ScheduledThreadPoolExecutor mDialogDaemon, mDialogDaemon_time;

    TextView txt_sport_v_time, txt_sport_dj;

    ImageView btn_stop_resume_sport, btn_volume_sport, batery, btn_sport_v_back, im_sport_v_time_2, im_sport_v_time_3, im_sport_v_time_4, im_sport_v_time_5, im_sport_v_time_6, im_sport_v_time_7, im_sport_v_time_8, im_sport_v_time_9, im_sport_v_time_10, im_sport_v_time_11, im_sport_v_time_12, im_sport_v_time_hid, im_sport_v_time_hid2, im_sports_1;

    String time, music_check_1, music_off_check_1, voice_check_1, voice_off_check_1, music_check, music_off_check, vibre_check, language, fa, en, de;

    int s, a, sport_page, f, sport_page_set, time_stop, time_stop2, btn_stop, volome_off, por_1, timeout, timestart;

    int nuve_play;

    MyDatabaseHelper_music music_data;
    MyDatabaseHelper_voice voice_data;
    MyDatabaseHelper_sound sound_data;
    MyDatabaseHelper_vibre vibre_data;

    int time_1 = 1;
    int time_2 = 1;
    int time_3 = 1;
    int time_4 = 1;

    MediaPlayer music, click, music_go, music_complate, music_puase, music_resumed;

    Vibrator vb;

    Intent d;

    private final int _splashTime = 500;
    private final int _splashTime60 = 60000;
    private final int _splashTime_hp_1 = 1500;

    final Handler handler = new Handler();
    Thread thread;

    CardView card_view_hp;

    AudioManager audioManager;

    SharedPreferences sport_namber;

    SharedPreferences.Editor nameEditor_sport_namber;

    int help_play, seda;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    MyDatabaseHelper_lan language1;
    private Tooltip.TooltipView mCurrentTooltip,mCurrentTooltip1,mCurrentTooltip2,mCurrentTooltip3,mCurrentTooltip4,mCurrentTooltip5,mCurrentTooltip6;
    DisplayMetrics displayMetrics;

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
                Locale locale = new Locale("fa");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                language = "fa";
            } else if (dadeha.toString().length() == 2) {
                //english.usa.canada
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                language = "en";
            } else if (dadeha.toString().length() == 3) {
                //chine
                Locale locale = new Locale("zh");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                language = "zh";
            } else if (dadeha.toString().length() == 6) {
                //german
                Locale locale = new Locale("de");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                language = "de";
            }

        } catch (Exception e) {
        }


        //start activ
        setContentView(R.layout.sport_v);

        try {
            one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
            one_play_editor = one_play_preferences.edit();
            help_play = one_play_preferences.getInt("Help_play_5", 1);
        } catch (Exception e) {
        }
        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
        }

        try {
            sport_namber = PreferenceManager.getDefaultSharedPreferences(this);
            nameEditor_sport_namber = sport_namber.edit();
        } catch (Exception e) {
        }

        try {
            //save music
            music_data = new MyDatabaseHelper_music(this);
            Cursor res3 = music_data.showAllData();
            StringBuffer data3 = new StringBuffer();
            while (res3.moveToNext()) {
                data3.append(res3.getString(1));
            }
            if (data3.toString().length() == 4) {
                music_check_1 = "true";
            } else if (data3.toString().length() == 5) {
                music_check_1 = "false";
            } else {
                music_check_1 = "true";
            }
            music_off_check_1 = "false";
        } catch (Exception e) {
        }


        try {
            //save voice
            voice_data = new MyDatabaseHelper_voice(this);
            Cursor res1 = voice_data.showAllData();
            StringBuffer data1 = new StringBuffer();
            while (res1.moveToNext()) {
                data1.append(res1.getString(1));
            }
            if (data1.toString().length() == 4) {
                voice_check_1 = "true";
            } else if (data1.toString().length() == 5) {
                voice_check_1 = "false";
            } else {
                voice_check_1 = "true";
            }
            voice_off_check_1 = "false";
        } catch (Exception e) {
        }


        fa = "fa";
        en = "en";
        de = "de";


        if (language == fa) {
            try {
                music_go = MediaPlayer.create(sport_v.this, R.raw.go_fa);
                if (voice_check_1 == voice_off_check_1) {
                    music_go.stop();
                } else {
                    music_go.start();
                }
            } catch (Exception e) {
            }
        } else if (language == en) {
            try {
                music_go = MediaPlayer.create(sport_v.this, R.raw.go_en);
                if (voice_check_1 == voice_off_check_1) {
                    music_go.stop();
                } else {
                    music_go.start();
                }
            } catch (Exception e) {
            }

        } else if (language == de) {
            try {
                music_go = MediaPlayer.create(sport_v.this, R.raw.go_de);
                if (voice_check_1 == voice_off_check_1) {
                    music_go.stop();
                } else {
                    music_go.start();
                }
            } catch (Exception e) {
            }
        } else {
            try {
                music_go = MediaPlayer.create(sport_v.this, R.raw.go_en);
                if (voice_check_1 == voice_off_check_1) {
                    music_go.stop();
                } else {
                    music_go.start();
                }
            } catch (Exception e) {
                }
        }

        final Bundle sport_v1 = getIntent().getExtras();

        txt_sport_v_time = (TextView) findViewById(R.id.txt_sport_v_time);
        txt_sport_dj = (TextView) findViewById(R.id.txt_sport_dj);
        batery = (ImageView) findViewById(R.id.batery);
        im_sport_v_time_hid = (ImageView) findViewById(R.id.im_sport_v_time_hid);
        im_sport_v_time_2 = (ImageView) findViewById(R.id.im_sport_v_time_2);
        im_sport_v_time_3 = (ImageView) findViewById(R.id.im_sport_v_time_3);
        im_sport_v_time_4 = (ImageView) findViewById(R.id.im_sport_v_time_4);
        im_sport_v_time_5 = (ImageView) findViewById(R.id.im_sport_v_time_5);
        im_sport_v_time_6 = (ImageView) findViewById(R.id.im_sport_v_time_6);
        im_sport_v_time_7 = (ImageView) findViewById(R.id.im_sport_v_time_7);
        im_sport_v_time_8 = (ImageView) findViewById(R.id.im_sport_v_time_8);
        im_sport_v_time_9 = (ImageView) findViewById(R.id.im_sport_v_time_9);
        im_sport_v_time_10 = (ImageView) findViewById(R.id.im_sport_v_time_10);
        im_sport_v_time_11 = (ImageView) findViewById(R.id.im_sport_v_time_11);
        im_sport_v_time_12 = (ImageView) findViewById(R.id.im_sport_v_time_12);
        im_sport_v_time_hid2 = (ImageView) findViewById(R.id.im_sport_v_time_hid2);
        im_sports_1 = (ImageView) findViewById(R.id.im_sports_1);
        card_view_hp = (CardView) findViewById(R.id.card_view_hp);
        btn_stop_resume_sport = (ImageView) findViewById(R.id.btn_stop_resume_sport);
        btn_volume_sport = (ImageView) findViewById(R.id.btn_volume_sport);
        btn_sport_v_back = (ImageView) findViewById(R.id.btn_sport_v_back);

        btn_stop_resume_sport.setOnClickListener(this);
        btn_volume_sport.setOnClickListener(this);
        btn_sport_v_back.setOnClickListener(this);

        volome_off = sport_v1.getInt("sport_v_volume1");


        try {
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        } catch (Exception e) {
            }

        timestart = 0;
        btn_stop = 1;
        time_stop = 1;
        time_stop2 = 1;
        s = 61;

        if (mDialogDaemon_time != null) {
            try {
                mDialogDaemon_time.shutdown();
                mDialogDaemon_time = null;
            } catch (Exception e) {
                }
        }
        try {
            mDialogDaemon_time = new ScheduledThreadPoolExecutor(1);
        } catch (Exception e) {
            }

        try {
            mDialogDaemon_time.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (time_stop2 == 1) {
                                int timestart_sport = timestart;
                                timestart = 1000 + timestart_sport;

                                a = s - 1;
                                time = String.valueOf(a);

                                txt_sport_v_time.setText(time);

                                if (a == 5) {
                                    new Handler().postDelayed(new Thread() {
                                        @Override
                                        public void run() {
                                            super.run();
                                            txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day));
                                        }
                                    }, _splashTime);

                                    txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day_hide));

                                } else if (a == 4) {
                                    new Handler().postDelayed(new Thread() {

                                        @Override
                                        public void run() {
                                            super.run();
                                            txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day));
                                        }
                                    }, _splashTime);

                                    txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day_hide));

                                } else if (a == 3) {
                                    new Handler().postDelayed(new Thread() {

                                        @Override
                                        public void run() {
                                            super.run();
                                            txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day));
                                        }
                                    }, _splashTime);

                                    txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day_hide));

                                } else if (a == 2) {
                                    new Handler().postDelayed(new Thread() {

                                        @Override
                                        public void run() {
                                            super.run();
                                            txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day));
                                        }
                                    }, _splashTime);

                                    txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day_hide));

                                } else if (a == 1) {
                                    new Handler().postDelayed(new Thread() {

                                        @Override
                                        public void run() {
                                            super.run();
                                            txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day));
                                        }
                                    }, _splashTime);

                                    txt_sport_v_time.setTextColor(getResources().getColor(R.color.color30day_hide));

                                }
                                s = a;
                            }
                            // Do something worthwhile
                        }
                    });
                }
            }, 0L, 1000L, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            }
        // This process will execute immediately, then execute every 3 seconds.


        por_1 = sport_v1.getInt("sport_v_por");
        if (por_1 == 1) {
            try {
                im_sport_v_time_hid.setImageResource(R.color.color30day_hide);
            } catch (Exception e) {
                }
        } else if (por_1 == 2) {
            try {
                im_sport_v_time_hid.setImageResource(R.color.color30day);
                im_sport_v_time_2.setImageResource(R.color.background_sport);
                im_sport_v_time_3.setImageResource(R.color.color30day_hide);
            } catch (Exception e) {
                }
        } else if (por_1 == 3) {
            try {
                im_sport_v_time_hid.setImageResource(R.color.color30day);
                im_sport_v_time_2.setImageResource(R.color.background_sport);
                im_sport_v_time_3.setImageResource(R.color.color30day);
                im_sport_v_time_4.setImageResource(R.color.background_sport);
                im_sport_v_time_5.setImageResource(R.color.color30day_hide);
            } catch (Exception e) {
                }
        } else if (por_1 == 4) {
            try {
                im_sport_v_time_hid.setImageResource(R.color.color30day);
                im_sport_v_time_2.setImageResource(R.color.background_sport);
                im_sport_v_time_3.setImageResource(R.color.color30day);
                im_sport_v_time_4.setImageResource(R.color.background_sport);
                im_sport_v_time_5.setImageResource(R.color.color30day);
                im_sport_v_time_6.setImageResource(R.color.background_sport);
                im_sport_v_time_7.setImageResource(R.color.color30day_hide);
            } catch (Exception e) {
                }
        } else if (por_1 == 5) {
            try {
                im_sport_v_time_hid.setImageResource(R.color.color30day);
                im_sport_v_time_2.setImageResource(R.color.background_sport);
                im_sport_v_time_3.setImageResource(R.color.color30day);
                im_sport_v_time_4.setImageResource(R.color.background_sport);
                im_sport_v_time_5.setImageResource(R.color.color30day);
                im_sport_v_time_6.setImageResource(R.color.background_sport);
                im_sport_v_time_7.setImageResource(R.color.color30day);
                im_sport_v_time_8.setImageResource(R.color.background_sport);
                im_sport_v_time_9.setImageResource(R.color.color30day_hide);
            } catch (Exception e) {
                }
        } else if (por_1 == 6) {
            try {
                im_sport_v_time_hid.setImageResource(R.color.color30day);
                im_sport_v_time_2.setImageResource(R.color.background_sport);
                im_sport_v_time_3.setImageResource(R.color.color30day);
                im_sport_v_time_4.setImageResource(R.color.background_sport);
                im_sport_v_time_5.setImageResource(R.color.color30day);
                im_sport_v_time_6.setImageResource(R.color.background_sport);
                im_sport_v_time_7.setImageResource(R.color.color30day);
                im_sport_v_time_8.setImageResource(R.color.background_sport);
                im_sport_v_time_9.setImageResource(R.color.color_red);
                im_sport_v_time_10.setImageResource(R.color.background_sport);
                im_sport_v_time_11.setImageResource(R.color.color30day_hide);
            } catch (Exception e) {
                }
        } else if (por_1 == 7) {
            try {
                im_sport_v_time_hid.setImageResource(R.color.color30day);
                im_sport_v_time_2.setImageResource(R.color.background_sport);
                im_sport_v_time_3.setImageResource(R.color.color30day);
                im_sport_v_time_4.setImageResource(R.color.background_sport);
                im_sport_v_time_5.setImageResource(R.color.color30day);
                im_sport_v_time_6.setImageResource(R.color.background_sport);
                im_sport_v_time_7.setImageResource(R.color.color30day);
                im_sport_v_time_8.setImageResource(R.color.background_sport);
                im_sport_v_time_9.setImageResource(R.color.color_red);
                im_sport_v_time_10.setImageResource(R.color.background_sport);
                im_sport_v_time_11.setImageResource(R.color.color_red);
                im_sport_v_time_12.setImageResource(R.color.background_sport);
                im_sport_v_time_hid2.setImageResource(R.color.color30day_hide);
            } catch (Exception e) {
                }
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


        sport_page = sport_v1.getInt("sport_v_number_day");
        sport_page_set = sport_v1.getInt("sport_v_number_day_set_1");

        if (sport_page == 1) {
            f = 1;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();


                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 1, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 2, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 3, 1, 3);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(5, 7, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }
            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 3, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();


                    end_sport_voise("sport_number_show_11", 11);
                    nameEditor_sport_namber.putInt("sport_number_show_set_1", 1);
                    nameEditor_sport_namber.apply();

                    music_online(2, 9, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 2) {
            f = 2;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(1, 3);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);

                    card_start();


                } catch (Exception e) {
                    }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 1, 3, 4);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(3, 4, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 3, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_4_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_4_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(1, 1, 1, 3);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_5_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_5_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 5, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_12", 12);

                    music_online(5, 7, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 3) {
            f = 3;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_11_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_11_2);
                                    }

                                    // Do something worthwhile
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(3, 4, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(3, 14, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(4, 6, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(5, 7, 1, 3);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 5, 4, 1);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_13", 13);

                    music_online(3, 4, 4, 1);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 4) {
            f = 4;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {

                try {

                    end_page();

                    music_online(5, 7, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_4_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_4_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(5, 7, 1, 3);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_5_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_5_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 14, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(3, 5, 4, 1);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 1, 3, 4);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_14", 14);

                    music_online(3, 4, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_11_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_11_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 5) {
            f = 5;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(3, 14, 3, 4);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 5, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(3, 4, 3, 4);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 10, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_15", 15);

                    music_online(1, 3, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }
            }


        } else if (sport_page == 6) {
            f = 6;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 14, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 10, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_9_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_9_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(4, 6, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(5, 7, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_4_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_4_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_16", 16);

                    music_online(1, 1, 2, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_5_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_5_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 7) {
            f = 7;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 3, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(3, 4, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 5, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {
                    end_page();

                    music_online(2, 9, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_9_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_9_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 1, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_11_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_11_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_17", 17);

                    music_online(1, 10, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            }


        } else if (sport_page == 8) {
            f = 8;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(3, 4);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);

                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(5, 7, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(3, 4, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_15_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_15_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(3, 5, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_11_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_11_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(4, 6, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_18", 18);

                    music_online(3, 14, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 9) {
            f = 9;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 3, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 10, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_15_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_15_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(5, 7, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 9, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_19", 19);

                    music_online(3, 5, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_9_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_9_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 10) {
            f = 10;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_9_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_9_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 2, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 10, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_15_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_15_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(4, 6, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_21", 11);

                    nameEditor_sport_namber.putInt("sport_number_show_set_2", 1);
                    nameEditor_sport_namber.apply();

                    music_online(1, 3, 2, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_11_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_11_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            }


        } else if (sport_page == 11) {
            f = 11;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(3, 4, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(4, 6, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(5, 7, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 9, 2, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 10, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_22", 12);

                    music_online(2, 11, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    }

            }


        } else if (sport_page == 12) {
            f = 12;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {
                    }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(5, 7, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_4_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_4_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {

                end_page();

                music_online(1, 1, 1, 3);


                if (mDialogDaemon != null) {
                    mDialogDaemon.shutdown();
                    mDialogDaemon = null;
                }
                mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                // This process will execute immediately, then execute every 3 seconds.
                mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (time_stop == 1) {
                                    time_stop = 2;
                                    im_sports_1.setImageResource(R.drawable.sport_5_1);
                                } else if (time_stop == 2) {
                                    time_stop = 1;
                                    im_sports_1.setImageResource(R.drawable.sport_5_2);
                                }
                            }
                        });
                    }
                }, 0L, 1200L, TimeUnit.MILLISECONDS);


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 2, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(1, 3, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_9_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_9_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 4, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_11_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_11_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_23", 13);

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 13) {
            f = 13;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(3, 5, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 10, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_12_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_12_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_14_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_14_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(5, 7, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_4_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_4_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 1, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_5_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_5_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_24", 14);

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            }


        } else if (sport_page == 14) {
            f = 14;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);

                    card_start();

                } catch (Exception e) {

                }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 3, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 9, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 10, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 11, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_12_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_12_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_15_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_15_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_25", 15);

                    music_online(3, 14, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 15) {
            f = 15;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 11, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 9, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(4, 6, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(3, 14, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 1, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_4_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_4_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_26", 16);

                    music_online(5, 7, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_5_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_5_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            }


        } else if (sport_page == 16) {
            f = 16;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);

                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(3, 5, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 10, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(3, 4, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_17_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_17_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_17_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 11, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_15_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_15_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_27", 17);

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_14_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_14_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 17) {
            f = 17;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 3, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(4, 6, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 10, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_12_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_12_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_9_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_9_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_28", 18);

                    music_online(2, 12, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_8_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_8_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 18) {
            f = 18;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }
            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 2, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 3, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_8_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_8_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(3, 5, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 14, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_29", 19);

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_10_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_10_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 19) {
            f = 19;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 1, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 10, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_8_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_8_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(4, 6, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 11, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_10_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_10_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 14, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_20", 20);

                    music_online(5, 7, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 20) {
            f = 20;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 1, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 2, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(1, 10, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_10_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_10_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 11, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_301", 21);

                    nameEditor_sport_namber.putInt("sport_number_show_set_3", 1);
                    nameEditor_sport_namber.apply();

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_12_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_12_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 21) {
            f = 21;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(5, 7, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_3_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_3_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(4, 6, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 4, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 10, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 11, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_32", 12);

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 22) {
            f = 22;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 11, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 12, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_8_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_8_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 3, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(1, 2, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_10_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_10_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 14, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_33", 13);

                    music_online(2, 13, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_12_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_12_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 23) {
            f = 23;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_14_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_14_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 10, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_2_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_2_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(1, 1, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 14, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_9_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_9_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_34", 14);

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_12_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_12_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }

        } else if (sport_page == 24) {
            f = 24;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 11, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_2_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_2_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 5, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(1, 3, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(1, 10, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_10_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_10_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_35", 15);

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 25) {
            f = 25;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }

            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_2_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_2_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(5, 7, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 14, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 12, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_10_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_10_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 11, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_8_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_8_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_36", 16);

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 26) {
            f = 26;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(2, 11, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_14_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_14_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_16_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_16_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 5, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 12, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_17_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_17_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_17_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_17_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 11, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_8_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_8_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_37", 17);

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 27) {
            f = 27;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 1, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(1, 2, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_2_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_2_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_2_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 3, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_14_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_14_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_16_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_16_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(3, 14, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_17_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_17_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_17_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_17_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_38", 18);

                    music_online(2, 12, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 28) {
            f = 28;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(1, 10, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_14_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_14_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_16_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_16_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(3, 14, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_8_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_8_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_8_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(5, 7, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 12, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_39", 19);

                    music_online(3, 5, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 29) {
            f = 29;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(3, 4, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_14_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_14_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 12, 2, 2);

                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_16_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_16_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(2, 1, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 13, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {
                    end_page();

                    music_online(3, 14, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_30", 20);


                    music_online(5, 7, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }


        } else if (sport_page == 30) {
            f = 30;
            if (sport_page_set == 1) {
                try {

                    end_page();

                    music_offline(4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_13_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_13_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_13_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);


                    card_start();

                } catch (Exception e) {

                }


            } else if (sport_page_set == 2) {
                try {

                    end_page();

                    music_online(3, 5, 1, 3);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_6_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_6_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_6_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }

            } else if (sport_page_set == 3) {
                try {

                    end_page();

                    music_online(2, 9, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_1_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_1_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 4) {
                try {

                    end_page();

                    music_online(1, 10, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_7_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_7_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 5) {
                try {

                    end_page();

                    music_online(2, 11, 4, 1);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_16_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_16_1);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_16_3);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 6) {
                try {

                    end_page();

                    music_online(2, 12, 2, 2);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_18_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_18_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }


            } else if (sport_page_set == 7) {
                try {

                    end_page_sport();

                    end_sport_voise("sport_number_show_40", 30);

                    music_online(2, 13, 3, 4);


                    if (mDialogDaemon != null) {
                        mDialogDaemon.shutdown();
                        mDialogDaemon = null;
                    }
                    mDialogDaemon = new ScheduledThreadPoolExecutor(1);
                    // This process will execute immediately, then execute every 3 seconds.
                    mDialogDaemon.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (time_stop == 1) {
                                        time_stop = 2;
                                        im_sports_1.setImageResource(R.drawable.sport_17_1);
                                    } else if (time_stop == 2) {
                                        time_stop = 3;
                                        im_sports_1.setImageResource(R.drawable.sport_17_2);
                                    } else if (time_stop == 3) {
                                        time_stop = 4;
                                        im_sports_1.setImageResource(R.drawable.sport_17_3);
                                    } else if (time_stop == 4) {
                                        time_stop = 1;
                                        im_sports_1.setImageResource(R.drawable.sport_17_2);
                                    }
                                }
                            });
                        }
                    }, 0L, 1200L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }

        }



        if (help_play == 1) {
            final String s1 = (String) getText(R.string.Help_start_8_1);
            final String s11 = (String) getText(R.string.Help_start_9_1);
            final String s22 = (String) getText(R.string.Help_start_9_2);
            String s3333 = s11 + System.getProperty("line.separator") + "" + System.getProperty("line.separator") + s22 ;
            final String s222 = (String) getText(R.string.Help_start_10_2);
            final String s22222 = (String) getText(R.string.Help_start_12_2);
            final String s111111 = (String) getText(R.string.Help_start_13_1);
            final String s222222 = (String) getText(R.string.Help_start_13_2);
            String s33333333 = s111111 + System.getProperty("line.separator") + "" + System.getProperty("line.separator") + s222222 ;
            final String s2222 = (String) getText(R.string.Help_start_11_2);

            final LinearLayout cv_al_1 = (LinearLayout) findViewById(R.id.cv_al_1);
            displayMetrics = getResources().getDisplayMetrics();

            mCurrentTooltip = Tooltip.make(
                    sport_v.this,
                    new Tooltip.Builder(101)
                            .maxWidth((int) (displayMetrics.widthPixels / 2))
                            .anchor(txt_sport_v_time, Tooltip.Gravity.BOTTOM)
                            .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 2000)
                            .text(s1)
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
                                            mCurrentTooltip1.show();
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

            mCurrentTooltip1 = Tooltip.make(
                    sport_v.this,
                    new Tooltip.Builder(101)
                            .maxWidth((int) (displayMetrics.widthPixels / 2))
                            .anchor(cv_al_1, Tooltip.Gravity.BOTTOM)
                            .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 5000)
                            .text(s3333)
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
                                            mCurrentTooltip1 = null;
                                            mCurrentTooltip2.show();
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



            mCurrentTooltip2 = Tooltip.make(
                    sport_v.this,
                    new Tooltip.Builder(101)
                            .maxWidth((int) (displayMetrics.widthPixels / 2))
                            .anchor(btn_volume_sport, Tooltip.Gravity.LEFT)
                            .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 4000)
                            .text(s222)
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
                                            mCurrentTooltip2 = null;
                                            mCurrentTooltip3.show();

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


            mCurrentTooltip3 = Tooltip.make(
                    sport_v.this,
                    new Tooltip.Builder(101)
                            .maxWidth((int) (displayMetrics.widthPixels / 2))
                            .anchor(im_sports_1, Tooltip.Gravity.BOTTOM)
                            .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 4000)
                            .text(s2222)
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
                                            mCurrentTooltip3 = null;
                                            mCurrentTooltip4.show();

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


            mCurrentTooltip4 = Tooltip.make(
                    sport_v.this,
                    new Tooltip.Builder(101)
                            .maxWidth((int) (displayMetrics.widthPixels / 2))
                            .anchor(btn_stop_resume_sport, Tooltip.Gravity.TOP)
                            .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 4000)
                            .text(s22222)
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
                                            mCurrentTooltip4 = null;
                                            mCurrentTooltip5.show();

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

            mCurrentTooltip5 = Tooltip.make(
                    sport_v.this,
                    new Tooltip.Builder(101)
                            .maxWidth((int) (displayMetrics.widthPixels / 2))
                            .anchor(btn_sport_v_back, Tooltip.Gravity.RIGHT)
                            .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 0)
                            .text(s33333333)
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
                                            mCurrentTooltip5 = null;

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


        }



    }


    public void card_start() {

        try {
            if (audioManager.isWiredHeadsetOn()) {
            } else {
                new Handler().postDelayed(new Thread() {

                    @Override
                    public void run() {
                        super.run();
                        new Handler().postDelayed(new Thread() {

                            @Override
                            public void run() {
                                super.run();
                                card_view_hp.setVisibility(View.VISIBLE);
                                card_view_hp.setAnimation(AnimationUtils.loadAnimation(sport_v.this, R.anim.in));
                                new Handler().postDelayed(new Thread() {

                                    @Override
                                    public void run() {
                                        super.run();
                                        new Handler().postDelayed(new Thread() {

                                            @Override
                                            public void run() {
                                                super.run();
                                                card_view_hp.setAnimation(AnimationUtils.loadAnimation(sport_v.this, R.anim.out));
                                                card_view_hp.setVisibility(View.INVISIBLE);
                                            }
                                        }, _splashTime_hp_1);
                                    }
                                }, _splashTime_hp_1);
                            }
                        }, _splashTime_hp_1);
                    }
                }, _splashTime_hp_1);
            }
        } catch (Exception e) {
        }

    }


    public void end_sport_voise(final String key, final int namber) {

        try {

            handler.postDelayed(thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    if (language == fa) {
                        music_complate = MediaPlayer.create(sport_v.this, R.raw.workout_completed_fa);
                        music_complate.start();
                    } else if (language == en) {
                        music_complate = MediaPlayer.create(sport_v.this, R.raw.workout_completed_en);
                        music_complate.start();
                    } else if (language == de) {
                        music_complate = MediaPlayer.create(sport_v.this, R.raw.workout_completed_de);
                        music_complate.start();
                    } else {
                        music_complate = MediaPlayer.create(sport_v.this, R.raw.workout_completed_en);
                        music_complate.start();
                    }
                    nameEditor_sport_namber.putInt(key, namber);
                    nameEditor_sport_namber.commit();
                }
            }, 58000);

        } catch (Exception e) {
        }
    }


    public void end_page() {

        try {
            handler.postDelayed(thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    d = new Intent(sport_v.this, sport_relax.class);
                    d.putExtra("sport_v_number_day_r", sport_page);
                    d.putExtra("sport_v_number_day_set_1_r", sport_page_set);
                    d.putExtra("sport_v_volome", volome_off);
                    d.putExtra("sport_v_por1", por_1);
                    startActivity(d);
                    stopService(new Intent(sport_v.this, Back_Music_Online.class));
                    stopService(new Intent(sport_v.this, Back_Music.class));
                    sport_v.this.finish();
                }
            }, _splashTime60);
        } catch (Exception e) {
        }

    }


    public void end_page_sport() {

        try {

            handler.postDelayed(thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    d = new Intent(sport_v.this, sport_end.class);
                    d.putExtra("sport_v_number_day_r", sport_page);
                    startActivity(d);
                    if (nuve_play == 1){
                        stopService(new Intent(sport_v.this, Back_Music.class));
                    }else if (nuve_play == 2){
                        stopService(new Intent(sport_v.this, Back_Music_Online.class));
                    }
                    sport_v.this.finish();
                }
            }, _splashTime60);

        } catch (Exception e) {
        }
    }

    public void music_offline(int n, int m) {

        if (n == 1) {
            try {
                txt_sport_dj.setText(R.string.sport_dj1_offline);
            } catch (Exception e) {
            }
        } else if (n == 2) {
            try {
                txt_sport_dj.setText(R.string.sport_dj2_offline);
            } catch (Exception e) {
            }
        } else if (n == 3) {
            try {
                txt_sport_dj.setText(R.string.sport_dj3_offline);
            } catch (Exception e) {
            }
        } else if (n == 4) {
            try {
                txt_sport_dj.setText(R.string.sport_dj2);
            } catch (Exception e) {
            }
        } else if (n == 5) {
            try {
                txt_sport_dj.setText(R.string.sport_dj5);
            } catch (Exception e) {
            }
        }

        nuve_play = 1;
        one_play_editor.putInt("nov_music_offline", m);
        one_play_editor.putString("music_check_offline", music_check_1);
        one_play_editor.putString("music_off_check_offline", music_off_check_1);
        one_play_editor.commit();

        startService(new Intent(this, Back_Music.class));

    }



    public void music_online(int n, int m, int nn, int mm) {
        if (music_check_1 == music_off_check_1) {
        } else {
            if (app_net.getInstance(this).isOnline()) {
                if (n == 1) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj1);
                    } catch (Exception e) {
                    }
                } else if (n == 2) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj2);
                    } catch (Exception e) {
                    }
                } else if (n == 3) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj3);
                    } catch (Exception e) {
                    }
                } else if (n == 4) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj4);
                    } catch (Exception e) {
                    }
                } else if (n == 5) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj5);
                    } catch (Exception e) {
                    }
                }

                nuve_play = 2;
                one_play_editor.putInt("nov_music_offline", m);
                one_play_editor.putInt("nov_music_online", 1);
                one_play_editor.putString("music_check_offline", music_check_1);
                one_play_editor.putString("music_off_check_offline", music_off_check_1);
                one_play_editor.commit();

                startService(new Intent(this, Back_Music_Online.class));

            } else {
                if (nn == 1) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj1_offline);
                    } catch (Exception e) {
                    }
                } else if (nn == 2) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj1);
                    } catch (Exception e) {
                    }
                } else if (nn == 3) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj3_offline);
                    } catch (Exception e) {
                    }
                } else if (nn == 4) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj1);
                    } catch (Exception e) {
                    }
                } else if (nn == 5) {
                    try {
                        txt_sport_dj.setText(R.string.sport_dj5);
                    } catch (Exception e) {
                    }
                }

                nuve_play = 2;
                one_play_editor.putInt("nov_music_online_mm", mm);
                one_play_editor.putInt("nov_music_online", 2);
                one_play_editor.putString("music_check_offline", music_check_1);
                one_play_editor.putString("music_off_check_offline", music_off_check_1);
                one_play_editor.commit();

                startService(new Intent(this, Back_Music_Online.class));

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_stop_resume_sport:

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
                    click = MediaPlayer.create(sport_v.this, R.raw.button);
                    click.prepare();
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }
                } catch (Exception e) {

                }


                if (btn_stop == 1) {
                    if (voice_check_1 == voice_off_check_1) {
                    } else {
                        if (language == fa) {
                            try {
                                music_puase = MediaPlayer.create(sport_v.this, R.raw.workout_paused_fa);
                                music_puase.prepare();
                                music_puase.start();
                            } catch (Exception e) {

                            }
                        } else if (language == en) {
                            try {
                                music_puase = MediaPlayer.create(sport_v.this, R.raw.workout_paused_en);
                                music_puase.prepare();
                                music_puase.start();
                            } catch (Exception e) {

                            }
                        } else if (language == de) {
                            try {
                                music_puase = MediaPlayer.create(sport_v.this, R.raw.workout_paused_de);
                                music_puase.prepare();
                                music_puase.start();
                            } catch (Exception e) {

                            }
                        } else {
                            try {
                                music_puase = MediaPlayer.create(sport_v.this, R.raw.workout_paused_en);
                                music_puase.prepare();
                                music_puase.start();
                            } catch (Exception e) {

                            }
                        }
                    }
                    btn_stop = 2;
                    try {
                        btn_stop_resume_sport.setImageResource(R.drawable.btn_volume_sport);
                    } catch (Exception e) {

                    }

                    time_stop2 = 2;
                    time_stop = 5;
                    timeout = _splashTime60;
                    try {
                        handler.removeCallbacks(thread);
                    } catch (Exception e) {

                    }

                    if (music_check_1 == music_off_check_1) {
                    } else {
                        try {
                            if (nuve_play == 1){
                                Back_Music.music.pause();
                            }else if (nuve_play == 2){
                                Back_Music_Online.music.pause();
                            }
                        } catch (Exception e) {

                        }
                    }

                } else if (btn_stop == 2) {

                    if (music_check_1 == music_off_check_1) {
                    } else {
                        if (seda == 1) {
                        } else {
                            try {
                                if (nuve_play == 1){
                                    Back_Music.music.start();
                                }else if (nuve_play == 2){
                                    Back_Music_Online.music.start();
                                }
                            } catch (Exception e) {

                            }
                        }
                    }

                    if (voice_check_1 == voice_off_check_1) {
                    } else {
                        if (language == fa) {
                            try {
                                music_resumed = MediaPlayer.create(sport_v.this, R.raw.workout_resumed_fa);
                                music_resumed.prepare();
                                music_resumed.start();
                            } catch (Exception e) {

                            }

                        } else if (language == en) {
                            try {
                                music_resumed = MediaPlayer.create(sport_v.this, R.raw.workout_resumed_en);
                                music_resumed.prepare();
                                music_resumed.start();
                            } catch (Exception e) {

                            }

                        } else if (language == de) {
                            try {
                                music_resumed = MediaPlayer.create(sport_v.this, R.raw.workout_resumed_de);
                                music_resumed.prepare();
                                music_resumed.start();
                            } catch (Exception e) {

                            }

                        } else {
                            try {
                                music_resumed = MediaPlayer.create(sport_v.this, R.raw.workout_resumed_en);
                                music_resumed.prepare();
                                music_resumed.start();
                            } catch (Exception e) {

                            }
                        }
                    }

                    btn_stop = 1;
                    time_stop = 1;
                    time_stop2 = 1;
                    try {
                        btn_stop_resume_sport.setImageResource(R.drawable.btn_stop_sport);
                    } catch (Exception e) {

                    }
                    try {
                        handler.postDelayed(thread, 60000 - timestart);
                    } catch (Exception e) {

                    }

                }


                break;

            case R.id.btn_volume_sport:

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
                    click = MediaPlayer.create(sport_v.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }
                } catch (Exception e) {

                }

                if (volome_off == 1) {
                    volome_off = 2;

                    try {
                        btn_volume_sport.setImageResource(R.drawable.volume_off);
                    } catch (Exception e) {

                    }

                    try {
                        if (nuve_play == 1){
                            Back_Music.music.pause();
                        }else if (nuve_play == 2){
                            Back_Music_Online.music.pause();
                        }
                    } catch (Exception e) {

                    }

                    seda = 1;

                } else if (volome_off == 2) {
                    volome_off = 1;
                    try {
                        btn_volume_sport.setImageResource(R.drawable.volume_high);
                    } catch (Exception e) {

                    }

                    try {
                        if (nuve_play == 1){
                            Back_Music.music.start();
                        }else if (nuve_play == 2){
                            Back_Music_Online.music.start();
                        }
                    } catch (Exception e) {

                    }

                    seda = 2;

                }
                break;

            case R.id.btn_sport_v_back:
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
                    click = MediaPlayer.create(sport_v.this, R.raw.button);
                    if (music_check == music_off_check) {
                        click.stop();
                    } else {
                        click.start();
                    }
                } catch (Exception e) {

                }

                if (help_play == 1) {
                    try {
                        if (null != mCurrentTooltip) {
                            mCurrentTooltip.hide();
                            mCurrentTooltip = null;
                        }
                        if (null != mCurrentTooltip1) {
                            mCurrentTooltip1.hide();
                            mCurrentTooltip1 = null;
                        }
                        if (null != mCurrentTooltip2) {
                            mCurrentTooltip2.hide();
                            mCurrentTooltip2 = null;
                        }
                        if (null != mCurrentTooltip3) {
                            mCurrentTooltip3.hide();
                            mCurrentTooltip3 = null;
                        }
                        if (null != mCurrentTooltip4) {
                            mCurrentTooltip4.hide();
                            mCurrentTooltip4 = null;
                        }
                        if (null != mCurrentTooltip5) {
                            mCurrentTooltip5.hide();
                            mCurrentTooltip5 = null;
                        }
                        if (null != mCurrentTooltip6) {
                            mCurrentTooltip6.hide();
                            mCurrentTooltip6 = null;
                        }
                        one_play_editor.putInt("Help_play_5", 3);
                        one_play_editor.commit();
                    } catch (Exception e) {

                        one_play_editor.putInt("Help_play_5", 3);
                        one_play_editor.commit();
                    }
                }

                String sport_day_back_1 = (String) getText(R.string.sport_day_back_1);
                String sport_day_back_2 = (String) getText(R.string.sport_day_back_2);
                String sport_day_back_3 = (String) getText(R.string.sport_day_back_3);
                String sport_day_back_4 = (String) getText(R.string.sport_day_back_4);

                try {

                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(sport_day_back_1)
                            .setContentText(sport_day_back_2)
                            .setCancelText(sport_day_back_4)
                            .setConfirmText(sport_day_back_3)
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

                                    click = MediaPlayer.create(sport_v.this, R.raw.button);
                                    if (music_check == music_off_check) {
                                        click.stop();
                                    } else {
                                        click.start();
                                    }

                                    handler.removeCallbacks(thread);

                                    if (help_play == 1) {

                                        Intent ss = new Intent(sport_v.this, Page_2.class);
                                        startActivity(ss);
                                        if (nuve_play == 1){
                                            stopService(new Intent(sport_v.this, Back_Music.class));
                                        }else if (nuve_play == 2){
                                            stopService(new Intent(sport_v.this, Back_Music_Online.class));
                                        }
                                        sport_v.this.finish();

                                    } else {
                                        if (nuve_play == 1){
                                        stopService(new Intent(sport_v.this, Back_Music.class));
                                    }else if (nuve_play == 2){
                                            stopService(new Intent(sport_v.this, Back_Music_Online.class));
                                    }
                                        sport_v.this.finish();
                                    }
                                }
                            })
                            .show();

                } catch (Exception e) {
                    handler.removeCallbacks(thread);

                    if (help_play == 1) {

                        Intent ss = new Intent(sport_v.this, Page_2.class);
                        startActivity(ss);
                        if (nuve_play == 1){
                            stopService(new Intent(sport_v.this, Back_Music.class));
                        }else if (nuve_play == 2){
                            stopService(new Intent(sport_v.this, Back_Music_Online.class));
                        }
                        sport_v.this.finish();

                    } else {
                        if (nuve_play == 1){
                            stopService(new Intent(sport_v.this, Back_Music.class));
                        }else if (nuve_play == 2){
                            stopService(new Intent(sport_v.this, Back_Music_Online.class));
                        }
                        sport_v.this.finish();
                    }
                }

                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (btn_stop == 1) {

            btn_stop = 2;
            try {
                btn_stop_resume_sport.setImageResource(R.drawable.btn_volume_sport);

            } catch (Exception e) {

            }

            time_stop2 = 2;
            time_stop = 5;
            timeout = _splashTime60;
            try {
                handler.removeCallbacks(thread);
            } catch (Exception e) {

            }

            if (music_check_1 == music_off_check_1) {
            } else {
                try {
                    if (nuve_play == 1){
                        Back_Music.music.pause();
                    }else if (nuve_play == 2){
                        Back_Music_Online.music.pause();
                    }
                } catch (Exception e) {

                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(thread);
        if (nuve_play == 1){
            stopService(new Intent(sport_v.this, Back_Music.class));
        }else if (nuve_play == 2){
            stopService(new Intent(sport_v.this, Back_Music_Online.class));
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Toast.makeText(sport_v.this, getResources().getText(R.string.about_alertdialog_8), Toast.LENGTH_LONG).show();
            handler.removeCallbacks(thread);
            if (nuve_play == 1){
                stopService(new Intent(sport_v.this, Back_Music.class));
            }else if (nuve_play == 2){
                stopService(new Intent(sport_v.this, Back_Music_Online.class));
            }
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
                click = MediaPlayer.create(sport_v.this, R.raw.button);
                if (music_check == music_off_check) {
                    click.stop();
                } else {
                    click.start();
                }
            } catch (Exception e) {

            }


            String sport_day_back_1 = (String) getText(R.string.sport_day_back_1);
            String sport_day_back_2 = (String) getText(R.string.sport_day_back_2);
            String sport_day_back_3 = (String) getText(R.string.sport_day_back_3);
            String sport_day_back_4 = (String) getText(R.string.sport_day_back_4);

            try {

                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(sport_day_back_1)
                        .setContentText(sport_day_back_2)
                        .setCancelText(sport_day_back_4)
                        .setConfirmText(sport_day_back_3)
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

                                click = MediaPlayer.create(sport_v.this, R.raw.button);
                                if (music_check == music_off_check) {
                                    click.stop();
                                } else {
                                    click.start();
                                }

                                handler.removeCallbacks(thread);
                                if (nuve_play == 1){
                                    stopService(new Intent(sport_v.this, Back_Music.class));
                                }else if (nuve_play == 2){
                                    stopService(new Intent(sport_v.this, Back_Music_Online.class));
                                }
                                sport_v.this.finish();
                            }
                        })
                        .show();

            } catch (Exception e) {
                handler.removeCallbacks(thread);
                if (nuve_play == 1){
                    stopService(new Intent(sport_v.this, Back_Music.class));
                }else if (nuve_play == 2){
                    stopService(new Intent(sport_v.this, Back_Music_Online.class));
                }
                sport_v.this.finish();
            }


        }
        return false;
    }

}
