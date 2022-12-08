package org.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day4-input.txt"));

        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);

            String firstPair = line.substring(0, line.indexOf(","));
            String secondPair = line.substring(line.indexOf(",") + 1);

            int dash = firstPair.indexOf("-");
            int firstMin = Integer.parseInt(firstPair.substring(0, dash));
            int firstMax = Integer.parseInt(firstPair.substring(dash + 1));

            dash = secondPair.indexOf("-");
            int secondMin = Integer.parseInt(secondPair.substring(0, dash));
            int secondMax = Integer.parseInt(secondPair.substring(dash + 1));

            /*
            First Part
            if (firstMin >= secondMin && firstMax <= secondMax){
                total++;
            } else if (secondMin >= firstMin && secondMax <= firstMax){
                total++;
            }
            */

            if (firstMin >= secondMin && firstMin <= secondMax){
                total++;
            } else if (secondMin >= firstMin && secondMin <= firstMax){
                total++;
            }


        }

        System.out.println("Answer: " + total);

        scan.close();
    }
}
