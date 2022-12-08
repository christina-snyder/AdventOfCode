package org.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day3-Input.txt"));

        int total = 0;
        while (scan.hasNextLine()){
            String line1 = scan.nextLine();
            String line2 = scan.nextLine();
            String line3 = scan.nextLine();

            HashMap<Character, Integer> firstMap = new HashMap<>();

            for (char c : line1.toCharArray()){
                firstMap.put(c, 0);
            }
            HashMap<Character, Integer> secondMap = new HashMap<>();

            for (char c : line2.toCharArray()){
                secondMap.put(c, 0);
            }

            char missing = ' ';
            for (char c : line3.toCharArray()){
                if (firstMap.get(c) != null && secondMap.get(c) != null){
                    missing = c;
                }
            }

            int score = convertToSCore(missing);
            System.out.println(missing);
            System.out.println(score);
            total += score;
        }

        System.out.println("Answer: " + total);

        scan.close();
    }
    public static void partOne(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day3-Input.txt"));

        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);

            String first = line.substring(0, line.length() / 2);
            String second = line.substring(line.length() / 2);

            HashMap<Character, Integer> firstMap = new HashMap<>();

            for (char c : first.toCharArray()){
                firstMap.put(c, 0);
            }

            char missing = ' ';
            for (char c : second.toCharArray()){
                if (firstMap.get(c) != null){
                    missing = c;
                }
            }

            int score = convertToSCore(missing);
            System.out.println(missing);
            System.out.println(score);
            total += score;
        }

        System.out.println("Answer: " + total);

        scan.close();
    }

    public static int convertToSCore(char c){
        if (c >= 'a' && c <= 'z'){
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }
}
