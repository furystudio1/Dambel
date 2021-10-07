package com.android.fury.Dambel;

import android.app.Application;
import android.util.Log;

import net.jhoobin.amaroidsdk.Amaroid;
import net.jhoobin.jhub.CharkhoneSdkApp;

/**
 * Created by fury on 5/16/2017.
 */
public class AppBody extends Application {

    public static AppBody me;

    @Override
    public void onCreate() {
        super.onCreate();
        me = this;
        Amaroid.getInstance().submitEventPageView(this,"com.android.fury.Dambel");
        //Monolyitcs.init(this,"2A1934F4-528D-461E-9390-6A385519AE15","5baa5d58-7c43-4def-b240-246b6bcf1b88");
        CharkhoneSdkApp.initSdk(this, getSecrets(), true);
        Thread.setDefaultUncaughtExceptionHandler(UncaughtExHandler.getInstance());
    }

    public String[] getSecrets() {
        return getResources().getStringArray(R.array.secrets);
    }


}
