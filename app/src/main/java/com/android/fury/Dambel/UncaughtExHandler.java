package com.android.fury.Dambel;

import android.util.Log;

import net.jhoobin.amaroidsdk.Amaroid;

public class UncaughtExHandler implements Thread.UncaughtExceptionHandler {

    private static UncaughtExHandler uncaughtExHandler;
    private Thread.UncaughtExceptionHandler androidDefaultUncaughtExceptionHandler;

    public static UncaughtExHandler getInstance() {
        if (uncaughtExHandler == null)
            uncaughtExHandler = new UncaughtExHandler();
        return uncaughtExHandler;
    }

    private UncaughtExHandler() {
        androidDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        try {
            Amaroid.getInstance().submitEventException(AppBody.me,throwable);
        } catch (Throwable e) {
            Log.e("UncaughtExHandler", "Unable to submitEventException", e);
        } finally {
            androidDefaultUncaughtExceptionHandler.uncaughtException(thread, throwable);
        }
    }
}
