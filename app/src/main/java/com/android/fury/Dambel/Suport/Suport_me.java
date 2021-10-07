package com.android.fury.Dambel.Suport;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.fury.Dambel.Database.MyDatabaseHelper_lan;
import com.android.fury.Dambel.Loading.BernoullisProgressView;
import com.android.fury.Dambel.PageStart.views.FlowingGradientClass;
import com.android.fury.Dambel.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fury on 1/29/2017.
 */
public class Suport_me extends Activity {
    MyDatabaseHelper_lan language;

    String mobile, email, name_user, text, text_kol;

    File root;

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

                Configuration conf = getResources().getConfiguration();
                conf.locale = new Locale("fa");
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                Resources resources = new Resources(getAssets(), metrics, conf);

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
                        getBaseContext().getResources().getDisplayMetrics());

            }
        } catch (Resources.NotFoundException e) {
            //...
        }


        setContentView(R.layout.suport_me);


        RelativeLayout rl_suport = (RelativeLayout) findViewById(R.id.rl_suport);
        ScrollView sc_suport = (ScrollView) findViewById(R.id.sc_suport);
        final CardView btn_send = (CardView) findViewById(R.id.btn_send);
        final EditText s_phone = (EditText) findViewById(R.id.s_phone);
        final EditText s_email = (EditText) findViewById(R.id.s_email);
        final EditText s_name = (EditText) findViewById(R.id.s_name);
        final EditText payam = (EditText) findViewById(R.id.payam);
        final BernoullisProgressView progress_support = (BernoullisProgressView) findViewById(R.id.progress_support);

        try {
            sc_suport.setVerticalScrollBarEnabled(false);
            sc_suport.setHorizontalScrollBarEnabled(false);
        } catch (Exception e) {
        }


        try {
            Typeface face = Typeface.createFromAsset(getAssets(), "fa_font_1.ttf");
            s_phone.setTypeface(face);
            s_email.setTypeface(face);
            s_name.setTypeface(face);
            payam.setTypeface(face);
        } catch (Exception e) {
        }


        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onRelativeLayout(rl_suport)
                .setTransitionDuration(4000)
                .start();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
        Date now = new Date();
        String hours = String.valueOf(now.getHours());
        String minutes = String.valueOf(now.getMinutes());
        String seconds = String.valueOf(now.getSeconds());
        String fileName = formatter.format(now);
        final String name_now = fileName + "_" + hours + "_" + minutes + "_" + seconds;


        btn_send.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    new Handler().postDelayed(new Thread() {

                        @Override
                        public void run() {
                            super.run();
                            btn_send.setCardElevation(20);
                        }
                    }, 200L);
                    btn_send.setCardElevation(15);
                } catch (Exception e) {
                }
                return false;
            }
        });


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    mobile = s_phone.getText().toString();
                    email = s_email.getText().toString();
                    name_user = s_name.getText().toString();
                    text = payam.getText().toString();
                } catch (Exception e) {
                    finish();
                }


                if (mobile.length() != 11 && email.length() == 0) {

                    try {
                        s_phone.setTextColor(getResources().getColor(R.color.color30day));
                        s_phone.setHintTextColor(getResources().getColor(R.color.color30day));
                        s_email.setTextColor(getResources().getColor(R.color.color30day));
                        s_email.setHintTextColor(getResources().getColor(R.color.color30day));
                    } catch (Exception e) {
                        finish();
                    }

                    try {
                        Toast.makeText(getApplicationContext(), R.string.p_or_e, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                    }

                } else if (name_user.length() == 0) {
                    try {
                        s_name.setTextColor(getResources().getColor(R.color.color30day));
                        s_name.setHintTextColor(getResources().getColor(R.color.color30day));
                    } catch (Exception e) {
                    }

                    try {
                        Toast.makeText(getApplicationContext(), R.string.not_name, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                    }

                } else if (text.length() == 0) {
                    try {
                        payam.setTextColor(getResources().getColor(R.color.color30day));
                        payam.setHintTextColor(getResources().getColor(R.color.color30day));
                    } catch (Exception e) {
                    }

                    try {
                        Toast.makeText(getApplicationContext(), R.string.not_text, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                    }

                } else {

                    try {
                        StringBuilder result = new StringBuilder();
                        if (email.length() > 0) {
                            result.append(email.charAt(0));
                            for (int i = 1; i < email.length(); i++) {
                                result.append(" ");
                                result.append(email.charAt(i));
                            }
                            email = result.toString();
                        }
                    } catch (Exception e) {
                    }


                    try {
                        text_kol = mobile + " /// " + "=" + email + "=" + " /// " + name_user + " /// " + " /// " + text;
                    } catch (Exception e) {
                        finish();
                    }

                    try {
                        generateNoteOnSD(getApplicationContext(), name_now, text_kol);
                    } catch (Exception e) {
                        finish();
                    }


                    /**StorageReference storageRef = storage.getReferenceFromUrl("gs://day-3e62e.appspot.com");

                     Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_tollbar);//your image
                     ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
                     bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
                     bmp.recycle();
                     byte[] byteArray = bYtE.toByteArray();


                     String path = "Support_30day_v/" + UUID.randomUUID() + ".png";
                     StorageReference storageReference = storage.getReference(path);

                     StorageMetadata storageMetadata = new StorageMetadata.Builder()
                     .setCustomMetadata("text", text_kol)
                     .build();

                     UploadTask uploadTask = storageReference.putBytes(byteArray, storageMetadata);*/

                    progress_support.setVisibility(View.VISIBLE);
                    btn_send.setVisibility(View.GONE);


                    try {
                        new Handler().postDelayed(new Thread() {

                            @Override
                            public void run() {
                                super.run();
                                progress_support.setVisibility(View.GONE);
                                btn_send.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), R.string.send_true, Toast.LENGTH_LONG).show();
                            }
                        }, 2000);
                    } catch (Exception e) {
                        progress_support.setVisibility(View.VISIBLE);
                    }

                    /**uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progress_support.setVisibility(View.GONE);
                    btn_send.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), R.string.send_true, Toast.LENGTH_LONG).show();
                    }
                    });*/

                }
            }
        });


    }


    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {

            root = new File(Environment.getExternalStorageDirectory(), "FitShow");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Toast.makeText(Suport_me.this, getResources().getText(R.string.about_alertdialog_8), Toast.LENGTH_LONG).show();
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();

        }
        return false;
    }

}
