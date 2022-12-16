package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day10-input.txt"));

        int total = 0;
        int cycle = 1;
        int register = 1;

        boolean adding = false;
        String line = "";

        StringBuilder s = new StringBuilder();

        while (scan.hasNextLine()){
            if (!adding){
                line = scan.nextLine();
            }

            if (timeToMath(cycle)) {
                //do math
                total += cycle * register;
            }

            int test = cycle % 40;

            System.out.println("CYCLE: " + cycle);
            System.out.println("sprite center: " + register);
            if (/*register - 1 == test   || */register +1 == test || register == test || register + 2 == test ){
                System.out.println("#");
                s.append("#");
            } else {
                System.out.println(".");
                s.append(".");
            }
            System.out.println(s);

            if (cycle % 40 == 0){
                System.out.println();
                s.append("\n");
            }
            if (!line.equals("noop")) {
                if (adding) {
                    //parse line
                    String[] data = line.split(" ");
                    //change register
                    register += Integer.parseInt(data[1]);
                    adding = false;
                } else {
                    adding = true;
                }
            }

            cycle++;


            //System.out.println(line);


        }

        System.out.println("Answer: " + total);
        System.out.println(s);
        scan.close();
    }

    public static boolean timeToMath(int cycle){
       return ((cycle - 20) % 40 == 0);
    }
}
