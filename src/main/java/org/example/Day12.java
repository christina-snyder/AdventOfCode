package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day12-Input.txt"));

        int numRows = 41;

        int currRow = 0;
        int[][] data = null;
        Help start = null;

        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);

            if (data == null){
                data = new int[numRows][line.length()];
            }

            int index = 0;
            for (char c : line.toCharArray()){
                if (c == 'S'){
                    start = new Help(currRow, index);
                    data[currRow][index++] = 0;
                } else if (c == 'E'){
                    data[currRow][index++] = 26;
                } else {
                    data[currRow][index++] = c - 'a';
                }
            }

            currRow++;

        }

        System.out.println("Answer: " + doMath(data, start));

        int min = doMath(data, start);
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                if (data[i][j] == 0){
                    int temp = doMath(data, new Help(i, j));
                    if (temp < min){
                        min = temp;
                    }
                }
            }
        }
        System.out.println("new answer: " + min);



        scan.close();
    }

    public static int doMath(int[][] data, Help start){
        ArrayList<Help> q = new ArrayList<>();
        q.add(start);

        ArrayList<Integer> sizes = new ArrayList<>();

        ArrayList<Help> visited = new ArrayList<>();

        while (!q.isEmpty()){
            Help curr = q.remove(0);
            //System.out.println(curr.depth);
            //System.out.println("(" + curr.row + ", " + curr.col + ") : " + data[curr.row][curr.col]);
            //System.out.println(curr);
            //if curr is E, add the depth to sizes
            if (data[curr.row][curr.col] == 26){
                //System.out.println(curr.depth);
                return curr.depth;
            }
            if (visited.contains(curr)){
                // System.out.println("Visted");
            } else {
                visited.add(curr);
                String[] dir = {"left", "down", "up", "right"};
                for (String d : dir) {
                    Help toAdd = new Help(curr, d, data);
                    //System.out.println(d + toAdd);
                    if (!visited.contains(toAdd) && toAdd.isValid(data.length, data[0].length)) {
                        q.add(toAdd);
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}

class Help {
    int row;
    int col;

    int depth;

    boolean visited = false;


    ArrayList<Help> previous;

    public Help(int row, int col){
        this.row = row;
        this.col = col;
        depth = 0;
       // previous = new ArrayList<Help>();
    }
    public Help(Help og, String direction, int[][] data){
        // TODO: IF statement on direction to create myself

        // if the heights are too different, set row and col to -1
        depth = og.depth + 1;
        int ogHeight = data[og.row][og.col];
        if (direction.equals("left")){
            col = og.col - 1;
            if (col < 0 || (data[og.row][col] > ogHeight + 1 /*|| data[og.row][col] < ogHeight - 1*/)){
                row = -1;
            } else {
                row = og.row;
            }
        } else if (direction.equals("down")) {
            row = og.row + 1;
            if  (row >= data.length || data[row][og.col] > ogHeight + 1 /*|| data[row][og.col] < ogHeight - 1*/){
                col = -1;
            } else {
                col = og.col;
            }
        } else if (direction.equals("right")){
            col = og.col + 1;
            if (col >= data[0].length || data[og.row][col] > ogHeight + 1 /*|| data[og.row][col] < ogHeight - 1*/){
                row = -1;
            } else {
                row = og.row;
            }
        } else if (direction.equals("up")) {
            row = og.row - 1;
            if (row < 0 || data[row][og.col] > ogHeight + 1 /* || data[row][og.col] < ogHeight - 1*/){
                col = -1;
            } else {
                col = og.col;
            }
        } else {
            System.out.println("Something is broken");
        }

        if (isValid(data.length, data[0].length)){
           // previous = new ArrayList<>(og.previous);
            //previous.add(og);
        }

    }

    public boolean equals(Object o){
        Help h = (Help) o;
        return (h.row == row) && h.col == col;
    }

    public boolean isValid(int numRows, int numCols){
        boolean result =  row>=0&& row < numRows && col >= 0 && col < numCols;
        if (result == false){
            return false;
        }
        /*if (previous != null) {
            for (Help p : previous) {
                if (p.row == row && p.col == col) {
                    return false;
                }
            }
        }*/
        return true;
    }

    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0 ; i < depth; i++){
            toReturn.append("*");
        }
        return toReturn.toString();
    }

}