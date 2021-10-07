package com.android.fury.Dambel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.android.fury.Dambel.Database.MyDatabaseHelper_lan;


public class Start_1 extends Activity {


    private final int _splashTime = 850;

    MyDatabaseHelper_lan language;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    int oneplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_1);



        try {

            one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
            one_play_editor = one_play_preferences.edit();
            oneplay = one_play_preferences.getInt("lang", 1);

        } catch (Exception e) {
        }





    }

    @Override
    protected void onStart() {
        super.onStart();
        try {

            new Handler().postDelayed(new Thread() {

                @Override
                public void run() {
                    super.run();


                    Intent uou = new Intent(Start_1.this, Start_2.class);
                    startActivity(uou);
                    Start_1.this.finish();
                }
            }, _splashTime);

        } catch (Exception e) {
        }


    }

}