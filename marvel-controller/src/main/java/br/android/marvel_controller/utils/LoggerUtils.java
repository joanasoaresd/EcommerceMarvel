package br.android.marvel_controller.utils;

import android.util.Log;

import br.android.marvel_controller.BuildConfig;

public class LoggerUtils {
    private LoggerUtils() {
        throw new UnsupportedOperationException();
    }

    public static void log(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
}
