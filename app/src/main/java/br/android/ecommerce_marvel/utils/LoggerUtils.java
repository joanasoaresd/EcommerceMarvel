package br.android.ecommerce_marvel.utils;

import android.util.Log;

import br.android.ecommerce_marvel.BuildConfig;

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
