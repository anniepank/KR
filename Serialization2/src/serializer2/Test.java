package serializer2;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class Test {

    public static University createUni() {
        University uni = new University();
        Faculty famcs = uni.createFaculty(AppLocale.getString(AppLocale.famcs), 2);
        Enrollee a = uni.createEnrollee(AppLocale.getString(AppLocale.steven), famcs);
        Enrollee b = uni.createEnrollee(AppLocale.getString(AppLocale.lewis), famcs);
        Enrollee c = uni.createEnrollee(AppLocale.getString(AppLocale.antony), famcs);

        Professor prof = uni.createProfessor(AppLocale.getString(AppLocale.king));

        Exam math = uni.createExam(AppLocale.getString(AppLocale.math), prof);
        Exam language = uni.createExam(AppLocale.getString(AppLocale.language), prof);
        Exam physics = uni.createExam(AppLocale.getString(AppLocale.physics), prof);

        uni.mark(a, language, 5);
        uni.mark(a, math, 5);
        uni.mark(a, physics, 5);

        uni.mark(b, language, 4);
        uni.mark(b, math, 2);
        uni.mark(b, physics, 2);

        uni.mark(c, language, 4);
        uni.mark(c, math, 4);
        uni.mark(c, physics, 5);

        famcs.enroll();

        return uni;
    }

    static Locale createLocale(String[] args) {
        if ( args.length == 2 ) {
            return new Locale( args[0], args[1] );
        } else if( args.length == 4 ) {
            return new Locale( args[2], args[3] );
        }
        return null;
    }

    static void setupConsole(String[] args) {
        if ( args.length >= 2 ) {
            if ( args[0].compareTo("-encoding")== 0 ) {
                try {
                    System.setOut( new PrintStream( System.out, true, args[1] ));
                } catch ( UnsupportedEncodingException ex ) {
                    System.err.println( "Unsupported encoding: " + args[1] );
                    System.exit(1);
                }
            }
        }
    }

    public static void main(String[] args) {


        try {
            setupConsole( args );
            Locale loc = createLocale(args);
            if (loc == null) {
                System.err.println(
                        "Invalid argument(s)\n" +
                                "Syntax: [-encoding ENCODING_ID] language country\n" +
                                "Example: -encoding Cp855 be BY");
                System.exit(1);
            }
            AppLocale.set(loc);

            Connector connector = new Connector("band.dat");
            connector.write(createUni());

            University uni = connector.read();

            for (Faculty f : uni.faculties) {
                for (Enrollee e : f.getEnrollees()) {
                    System.out.println(e);
                }
            }

        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }
}
