package com.android.fury.Dambel.sport;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.fury.Dambel.Database.MyDatabaseHelper_lan;
import com.android.fury.Dambel.Database.MyDatabaseHelper_music;
import com.android.fury.Dambel.Database.MyDatabaseHelper_voice;
import com.android.fury.Dambel.R;
import com.android.fury.Dambel.instagram;

import java.util.Locale;
import java.util.Random;

/**
 * Created by fury on 1/20/2017.
 */
public class Sport_me_end extends Activity {


    TextView btn_sport_end_1, txt_sport_end_4, txt_sport_end_2, txt_sport_end_5;

    MyDatabaseHelper_music music_data;
    MyDatabaseHelper_voice voice_data;

    String music_check_1, fars, music_off_check_1, voice_check_1, text_1, text_2;

    MediaPlayer music;

    ImageView im_aword_sport_end, im_aword_sport_end2, im_aword_sport_end3;

    int min, max, insta, text_ent_1, text_ent_2;

    private final int _splashTime = 500;

    MyDatabaseHelper_lan language;


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
                /*Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                fars = "fa";

            } else if (dadeha.toString().length() == 2) {
                //english.usa.canada


                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

            } else if (dadeha.toString().length() == 3) {
                //chine

                Locale locale = new Locale("zh");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

            } else if (dadeha.toString().length() == 6) {
                //german

                Locale locale = new Locale("de");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());*/

            }

        } catch (Exception e) {
        }


        setContentView(R.layout.sport_me_e);


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
                voice_check_1 = "false";
            }

        } catch (Exception e) {
        }


        try {

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

            music = MediaPlayer.create(Sport_me_end.this, R.raw.theaward);
            if (music_check_1 == music_off_check_1) {
                music.stop();
            } else {
                music.start();
            }

        } catch (Exception e) {
        }


        btn_sport_end_1 = (TextView) findViewById(R.id.btn_sport_end_1);
        txt_sport_end_4 = (TextView) findViewById(R.id.txt_sport_end_4);
        txt_sport_end_5 = (TextView) findViewById(R.id.txt_sport_end_5);
        txt_sport_end_2 = (TextView) findViewById(R.id.txt_sport_end_2);
        im_aword_sport_end = (ImageView) findViewById(R.id.im_aword_sport_end);
        im_aword_sport_end2 = (ImageView) findViewById(R.id.im_aword_sport_end2);
        im_aword_sport_end3 = (ImageView) findViewById(R.id.im_aword_sport_end3);


        min = 1;
        max = 7;

        Random y = new Random();
        try {

            text_ent_1 = y.nextInt(max - min + 1) + min;

        } catch (Exception e) {
            text_ent_1 = 5;
        }


        if (text_ent_1 == 1) {

            text_1 = "???????? ???????? ??????????";

        } else if (text_ent_1 == 2) {

            text_1 = "???? ???????? ?????????? ?????????? ????????";

        } else if (text_ent_1 == 3) {

            text_1 = "???? ???????? ???? ???????????? ???? ?????? ????????";

        } else if (text_ent_1 == 4) {

            text_1 = "???????? : ?????????? ?????????? ?? ???????????????? ????????";

        } else if (text_ent_1 == 5) {

            text_1 = "???? ???????? ???? ?????????? ?? ?????????? ?????? ??????????";

        } else if (text_ent_1 == 6) {

            text_1 = "???????? ???????????????? ?? ???????? ?????????? ???? ??????";

        } else if (text_ent_1 == 7) {

            text_1 = "???????? ???? ?????? ?????? ???????? ?????? ?????????? ??????";

        }

        txt_sport_end_4.setText(text_1);


        min = 1;
        max = 10;

        Random x = new Random();
        try {

            text_ent_2 = x.nextInt(max - min + 1) + min;

        } catch (Exception e) {
            text_ent_2 = 5;
        }

        if (text_ent_2 == 1) {

            text_2 = "?????????? ???? ???? ???????????? ???????? ?????????? ???????? ???????? ???? ?????? ???????????? ??????";

        } else if (text_ent_2 == 2) {

            text_2 = " ?????? ???? ???? ?????????? ???????? ???????????? ???? ???????? ???? ???????? ?????????????? ??????";

        } else if (text_ent_2 == 3) {

            text_2 = "?????? ?????? ???? ???????? ?????????? ?????????? ?????????????? ?????????? ?????????? ???????????? ???? ???? ?????????? ???? ?????????? ??????????";

        } else if (text_ent_2 == 4) {

            text_2 = "???????????? ???? ???? ??????????\u200C???? ????????\u200C???? ????\u200C???????? ?????? ???? ???? ???????? ????????\u200C???? ??????\u200C????????????";

        } else if (text_ent_2 == 5) {

            text_2 = " ???????????? ?????????? ???? ???????? ????????\u200C???? ?????????? ?????????? ???? ???? ???????? ??????????????";

        } else if (text_ent_2 == 6) {

            text_2 = "???? ???? ???????? ???? ???????? ?????????? ???? ?? ???? ???????? ???????? ???? .";

        } else if (text_ent_2 == 7) {

            text_2 = "???????? ???????? ?????????? ?????????? ???????????? ?????????? ???? ????????????";

        } else if (text_ent_2 == 8) {

            text_2 = "???????? ?????????? ?????? ???????? ???????????? ?????????????? ?????? ??????";

        } else if (text_ent_2 == 9) {

            text_2 = "?????? ?????????????? ?????????????? ???? ?????? ???? ?????????? ??????";

        } else if (text_ent_2 == 10) {

            text_2 = "?????? ???? ?????????? ???????????? ?????????? ???????? ???? ?????????? ?????? ???????????? ??????";

        }

        txt_sport_end_5.setText(text_2);


        try {

            new Handler().postDelayed(new Thread() {

                @Override
                public void run() {
                    super.run();

                    new Handler().postDelayed(new Thread() {

                        @Override
                        public void run() {
                            super.run();

                            im_aword_sport_end.setVisibility(View.VISIBLE);
                            im_aword_sport_end2.setVisibility(View.VISIBLE);

                            new Handler().postDelayed(new Thread() {

                                @Override
                                public void run() {
                                    super.run();

                                    im_aword_sport_end.setVisibility(View.INVISIBLE);
                                    im_aword_sport_end2.setVisibility(View.INVISIBLE);
                                    im_aword_sport_end3.setVisibility(View.VISIBLE);


                                    new Handler().postDelayed(new Thread() {

                                        @Override
                                        public void run() {
                                            super.run();

                                            im_aword_sport_end.setVisibility(View.INVISIBLE);
                                            im_aword_sport_end2.setVisibility(View.INVISIBLE);
                                            im_aword_sport_end3.setVisibility(View.INVISIBLE);

                                            new Handler().postDelayed(new Thread() {

                                                @Override
                                                public void run() {
                                                    super.run();

                                                    im_aword_sport_end.setVisibility(View.VISIBLE);
                                                    im_aword_sport_end2.setVisibility(View.INVISIBLE);
                                                    im_aword_sport_end3.setVisibility(View.INVISIBLE);

                                                    new Handler().postDelayed(new Thread() {

                                                        @Override
                                                        public void run() {
                                                            super.run();

                                                            im_aword_sport_end.setVisibility(View.INVISIBLE);
                                                            im_aword_sport_end2.setVisibility(View.VISIBLE);
                                                            im_aword_sport_end3.setVisibility(View.INVISIBLE);

                                                            new Handler().postDelayed(new Thread() {

                                                                @Override
                                                                public void run() {
                                                                    super.run();

                                                                    im_aword_sport_end.setVisibility(View.INVISIBLE);
                                                                    im_aword_sport_end2.setVisibility(View.INVISIBLE);
                                                                    im_aword_sport_end3.setVisibility(View.VISIBLE);

                                                                    new Handler().postDelayed(new Thread() {

                                                                        @Override
                                                                        public void run() {
                                                                            super.run();

                                                                            im_aword_sport_end.setVisibility(View.VISIBLE);
                                                                            im_aword_sport_end2.setVisibility(View.VISIBLE);
                                                                            im_aword_sport_end3.setVisibility(View.VISIBLE);

                                                                            new Handler().postDelayed(new Thread() {

                                                                                @Override
                                                                                public void run() {
                                                                                    super.run();

                                                                                    im_aword_sport_end.setVisibility(View.INVISIBLE);
                                                                                    im_aword_sport_end2.setVisibility(View.VISIBLE);
                                                                                    im_aword_sport_end3.setVisibility(View.INVISIBLE);

                                                                                    new Handler().postDelayed(new Thread() {

                                                                                        @Override
                                                                                        public void run() {
                                                                                            super.run();

                                                                                            im_aword_sport_end.setVisibility(View.INVISIBLE);
                                                                                            im_aword_sport_end2.setVisibility(View.INVISIBLE);
                                                                                            im_aword_sport_end3.setVisibility(View.VISIBLE);

                                                                                            new Handler().postDelayed(new Thread() {

                                                                                                @Override
                                                                                                public void run() {
                                                                                                    super.run();

                                                                                                    im_aword_sport_end.setVisibility(View.VISIBLE);
                                                                                                    im_aword_sport_end2.setVisibility(View.INVISIBLE);
                                                                                                    im_aword_sport_end3.setVisibility(View.INVISIBLE);

                                                                                                    new Handler().postDelayed(new Thread() {

                                                                                                        @Override
                                                                                                        public void run() {
                                                                                                            super.run();

                                                                                                            im_aword_sport_end.setVisibility(View.INVISIBLE);
                                                                                                            im_aword_sport_end2.setVisibility(View.INVISIBLE);
                                                                                                            im_aword_sport_end3.setVisibility(View.INVISIBLE);


                                                                                                        }
                                                                                                    }, _splashTime);

                                                                                                }
                                                                                            }, _splashTime);

                                                                                        }
                                                                                    }, _splashTime);

                                                                                }
                                                                            }, _splashTime);

                                                                        }
                                                                    }, _splashTime);

                                                                }
                                                            }, _splashTime);

                                                        }
                                                    }, _splashTime);

                                                }
                                            }, _splashTime);

                                        }
                                    }, _splashTime);

                                }
                            }, _splashTime);

                        }
                    }, _splashTime);


                }
            }, _splashTime);

        } catch (Exception e) {
        }


        min = 1;
        max = 8;

        Random r = new Random();
        try {

            insta = r.nextInt(max - min + 1) + min;

        } catch (Exception e) {
            insta = 5;
        }


        btn_sport_end_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (insta == 5) {

                    Intent ins = new Intent(Sport_me_end.this, instagram.class);
                    startActivity(ins);
                    finish();

                } else {

                    finish();

                }
            }
        });



    }
}
