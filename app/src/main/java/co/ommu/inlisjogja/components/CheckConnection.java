package co.ommu.inlisjogja.components;

/**
 * Created by KurniawanD on 4/29/2016.
 */
import android.content.Context;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
public class CheckConnection {
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        else
        return false;
    }
}
