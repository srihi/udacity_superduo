package it.jaschke.alexandria;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Dawit A. on 10/20/2015.
 */
public class Utility {

    public static final int SERVER_STATUS_OK = 0;
    public static final int SERVER_STATUS_SERVER_DOWN = 1;
    public static final int SERVER_STATUS_INVALID_RESPONSE = 2;
    public static final int SERVER_STATUS_UNKNOWN = 3;

    public static boolean checkForNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();

        return nf != null && nf.isConnectedOrConnecting();
    }

    public static boolean isNotNullNotEmptyNotWhiteSpace(
            final String string) {
        return string != null && !string.isEmpty() && !string.trim().isEmpty();
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SERVER_STATUS_OK, SERVER_STATUS_SERVER_DOWN, SERVER_STATUS_INVALID_RESPONSE, SERVER_STATUS_UNKNOWN})
    public @interface ServerStatus {
    }
}
