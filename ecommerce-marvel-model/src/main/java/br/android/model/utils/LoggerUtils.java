package br.android.model.utils;

import android.util.Log;

import br.android.model.BuildConfig;

public class LoggerUtils {
    private LoggerUtils() {
        throw new UnsupportedOperationException();
    }

    public static void log(String tag, String msg) {
        if (BuildConfig.LOGGER) {
            Log.d(tag, msg);
        }
    }
}
