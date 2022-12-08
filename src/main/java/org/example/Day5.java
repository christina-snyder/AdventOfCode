package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day5-Stacks.txt"));
        int numStacks = 9;
        int numRows = 8;
        ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
        //Hardcoded a bit...
        for (int i = 0 ; i < numStacks; i++){
            map.add(new ArrayList<String>());
        }

        for (int i = 0; i < numRows; i++){
            String line = scan.nextLine();
            System.out.println(line);
            for (int j = 0; j < numStacks; j++){
                if (j*4 + 4 < line.length()) {
                    String cube = line.substring(j * 4, j * 4 + 4);
                    cube = cube.trim();
                    if (cube.length() > 0) {
                        map.get(j).add(cube.substring(1, 2));
                    }
                } else if (j * 4 < line.length()){
                    String cube = line.substring(j * 4);
                    cube = cube.trim();
                    if (cube.length() > 0) {
                        map.get(j).add(cube.substring(1, 2));
                    }
                }
            }
        }

        System.out.println(map);
        scan.close();
        scan = new Scanner(new File("src/main/java/org/example/Day5-Steps.txt"));

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);

            String[] data = line.split (" ");
            int amtToMove = Integer.parseInt(data[1]);
            int startStack = Integer.parseInt(data[3]);
            int endStack = Integer.parseInt(data[5]);

            ArrayList<String> starting = map.get(startStack - 1);
            ArrayList<String> ending = map.get(endStack - 1);

            /*
            Part ONE
            for (int i = 0; i < amtToMove; i++){
                ending.add(0, starting.remove(0));
            }
            */

            for (int i = amtToMove - 1; i >= 0; i--){
                ending.add(0, starting.remove(i));
            }
        }

        String result = "";
        for (ArrayList<String> stack : map){
            result+= stack.get(0);
        }
        System.out.println(result);
        scan.close();
    }
}
