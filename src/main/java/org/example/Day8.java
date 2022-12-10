package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day8-input.txt"));

        int numRows = 0;
        int numCols = 0;

        //make map

        //optimizations:
        //int[] max from top at index
        //currmax for row from left

        //if not visible from top or left, search right, search down
        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);
            numRows++;
            numCols = line.length();
        }

        int[][] data = new int[numRows][numCols];

        System.out.println("Answer: " + total);

        scan.close();
        scan = new Scanner(new File("src/main/java/org/example/Day8-input.txt"));
        int i = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            for (int j = 0; j < line.length(); j++){
                data[i][j] = line.charAt(j) - '0';
            }
            i++;
        }

        int max = 0;
        for ( i = 0; i < data.length; i++){
            for (int j = 0; j < data.length; j++){
                int score = calcScore(i, j, data);
                if (score > max){
                    max = score;
                }
                if (canBeSeen(i, j, data)){
                    total ++;
                }
            }
        }

        System.out.println(total);

        System.out.println("Max: " + max);

    }

    public static int calcScore(int i, int j, int[][] map){

        int up = 0;
        boolean canSeeUp = true;
        for (int row = i - 1; row >= 0 && canSeeUp; row--){
            up ++;
            if (map[row][j] < map[i][j]){
            } else {
                canSeeUp = false;
            }
        }

        int down = 0;
        boolean canSeeDown = true;
        for (int row = i + 1; row < map.length && canSeeDown; row++){
            down ++;
            if (map[row][j] < map[i][j]){
            } else {
                canSeeDown = false;
            }
        }

        int left = 0;
        boolean canSeeLeft = true;
        for (int col = j - 1; col >= 0 && canSeeLeft; col--){
            left ++;
            if (map[i][col] < map[i][j]){
            } else {
                canSeeLeft = false;
            }
        }

        int right = 0;
        boolean canSeeRight = true;
        for (int col = j + 1; col < map.length && canSeeRight; col++){
            right ++;
            if (map[i][col] < map[i][j]){
            } else {
                canSeeRight = false;
            }
        }

        return up * down * left * right;
    }

    public static boolean canBeSeen(int i, int j, int[][] map){
        if (i == 0 || j == 0 || i == map.length - 1 || j == map[0].length - 1){
            return true;
        } else{
            //from top
            boolean seen = true;
            for (int top = 0; top < i; top++){
                if (map[top][j] >= map[i][j]){
                    seen = false;
                }
            }
            if (seen){
                return true;
            }
            seen = true;
            //from left
            for (int left = 0; left < j; left++){
                if (map[i][left] >= map[i][j]){
                    seen = false;
                }
            }
            if (seen){
                return true;
            }
            seen = true;
            //from right
            for (int right = map[0].length - 1; right > j; right--){
                if (map[i][right] >= map[i][j]){
                    seen = false;
                }
            }
            if (seen){
                return true;
            }
            seen = true;
            //from bottom
            for (int bottom = map.length - 1; bottom > i; bottom --){
                if (map[bottom][j] >= map[i][j]){
                    seen = false;
                }
            }
            return seen;
        }
    }
}
