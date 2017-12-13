import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class Test {
    public Test() {
    }

    public static Instrument[] createBand() {
        Instrument[] band = new Instrument[5];
        band[0] = new Percussion(AppLocale.getString("drum"));
        band[0].setInfo(AppLocale.getString("small_drum"));
        band[1] = new Stringed(AppLocale.getString("guitar"));
        band[1].setInfo(AppLocale.getString("solo_guitar"));
        band[2] = new Stringed(AppLocale.getString("guitar"));
        band[2].setInfo(AppLocale.getString("rhythm_guitar"));
        band[3] = new Stringed(AppLocale.getString("bass"));
        band[3].setInfo(AppLocale.getString("double_bass"));
        band[4] = new Wind(AppLocale.getString("sax"));
        band[4].setInfo(AppLocale.getString("tenor_sax"));
        return band;
    }

    static Locale createLocale(String[] args) {
        if (args.length == 2) {
            return new Locale(args[0], args[1]);
        } else {
            return args.length == 4 ? new Locale(args[2], args[3]) : null;
        }
    }

    static void setupConsole(String[] args) {
        if (args.length >= 2 && args[0].compareTo("-encoding") == 0) {
            try {
                System.setOut(new PrintStream(System.out, true, args[1]));
            } catch (UnsupportedEncodingException var2) {
                System.err.println("Unsupported encoding: " + args[1]);
                System.exit(1);
            }
        }

    }

    public static void main(String[] args) {
        try {
            setupConsole(args);
            Locale loc = createLocale(args);
            if (loc == null) {
                System.err.println("Invalid argument(s)\nSyntax: [-encoding ENCODING_ID] language country\nExample: -encoding Cp855 be BY");
                System.exit(1);
            }

            AppLocale.set(loc);
            Connector con = new Connector("band.dat");
            con.write(createBand());
            Instrument[] band = con.read();
            System.out.println(AppLocale.getString("the_band") + ":");
            Instrument[] arr$ = band;
            int len$ = band.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Instrument n = arr$[i$];
                System.out.println(n);
            }
        } catch (Exception var8) {
            System.err.println(var8);
        }
    }
}
