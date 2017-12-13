package serializer2;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppLocale {

    private static final String strMsg = "Msg";
    private static ResourceBundle res;
    private static Locale loc = Locale.getDefault();


    static Locale get() {
        return AppLocale.loc;
    }

    static void set(Locale loc) {
        res = ResourceBundle.getBundle(AppLocale.strMsg, loc);
    }

    static String getString(String key) {
        String s =  AppLocale.res.getString(key);
        try {
            return new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    // Resource keys:

    public static final String name = "name";
    public static final String creation = "creation";
    public static final String famcs = "famcs";
    public static final String steven = "steven";
    public static final String lewis = "lewis";
    public static final String antony = "antony";
    public static final String king = "king";
    public static final String math = "math";
    public static final String language = "language";
    public static final String physics = "physics";
}