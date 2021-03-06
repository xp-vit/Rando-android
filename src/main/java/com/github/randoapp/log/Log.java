package com.github.randoapp.log;

public class Log {

    public static void i(Class clazz, String... msgs) {
        android.util.Log.i(clazz.getName(), concatenate(msgs));
    }

    public static void d(Class clazz, String... msgs) {
        android.util.Log.d(clazz.getName(), concatenate(msgs));
    }

    public static void w(Class clazz, String... msgs) {
        android.util.Log.w(clazz.getName(), concatenate(msgs));
    }

    public static void e(Class clazz, String... msgs) {
        android.util.Log.e(clazz.getName(), concatenate(msgs));
    }

    public static void e(Class clazz, String comment, Throwable throwable) {
        android.util.Log.e(clazz.getName(), comment + " error:", throwable);
    }

    public static void v(Class clazz, String... msgs) {
        android.util.Log.v(clazz.getName(), concatenate(msgs));
    }

    private static String concatenate(String[] msgs) {
        if (msgs == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String msg : msgs) {
            sb.append(msg).append(" ");
        }
        return sb.toString();
    }

}
