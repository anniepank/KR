package com.anna.String;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("/home/anya/IdeaProjects/Sem3/Lines.txt");

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String a = sc.nextLine();

                int amountOfSymbols = a.length();

                Map<Character, Integer> symbols = new HashMap<>();

                for (int i = 0; i < a.length(); i++) {
                    Character ch = a.charAt(i);

                    if (!symbols.containsKey(ch)) {
                        symbols.put(ch, 0);
                    }
                    symbols.put(ch, symbols.get(ch) + 1);
                }

                System.out.format("%32s%10s%10s", "Symbol", "Quantity", "Frequency");
                System.out.println();


                for (Character ch : symbols.keySet()) {
                    double frequency = (double)symbols.get(ch)  / amountOfSymbols * 100;

                    System.out.format("%32s%10d%10.2f", ch,
                            symbols.get(ch),
                            frequency);
                    System.out.println();
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
