package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day13-Input.txt"));

        ArrayList<String> lines = new ArrayList<>();
        int total = 0;
        int index = 1;
        while (scan.hasNextLine()){
            String left = scan.nextLine();
            String right = scan.nextLine();
            if (scan.hasNextLine()) scan.nextLine();
            System.out.println(left);
            System.out.println(right);
            System.out.println();

            lines.add(left);
            lines.add(right);
            if (isRight(right, left) == null){
                System.out.println("SHOULDN'T BE NULL?");
            }
            if (isRight(right, left)){
                total += index;
            }

            index++;

        }

        System.out.println("Answer: " + isRight("[1,[2,[3,[4,[5,6,0]]]],8,9]", "[1,[2,[3,[4,[5,6,7]]]],8,9]"));
        System.out.println("Total: " + total);

        //implement bubble sort
        lines.add("[[2]]");
        lines.add("[[6]]");
        for (int i = 0; i < lines.size(); i++){
            for (int j = 0; j < lines.size()-1; j++){
                if (isRight(lines.get(j), lines.get(j+1))){
                    //swap(lines, i, j);
                    Collections.swap(lines, j+1, j);
                }
            }
        }
        int two = 0;
        int six = 0;
        for (int i = 0; i < lines.size(); i++){
            if (lines.get(i).equals("[[2]]")){
                two = i + 1;
            } else if (lines.get(i).equals("[[6]]")){
                six = i + 1;
            }
        }
        System.out.println("New answer: " + (two*six));
        scan.close();
    }

    public static Boolean isRight(String right, String left){
      /*  if ((left.equals("[]") || left.length() == 0) && right.length()  > 2){
            return true;
        }
        if (right.equals("[]")){
            return false;
        }*/

        String[] rightData = fancySplit(right);
        String[] leftData = fancySplit(left);

        int rightIndex = 0;
        int leftIndex = 0;
        Boolean result = null;
        while (rightIndex < rightData.length && leftIndex < leftData.length && result == null) {
            //System.out.println("Left: " + leftData[leftIndex]);
            //System.out.println("Right: " + rightData[rightIndex]);
            if (rightData[rightIndex].startsWith("[") && leftData[leftIndex].startsWith("[")) {
                result = isRight(rightData[rightIndex], leftData[leftIndex]);
            } else if (!rightData[rightIndex].startsWith("[") && leftData[leftIndex].startsWith("[")){
                //convert right to list, compare
                String newList = "[" + rightData[rightIndex] + "]";
                result = isRight(newList, leftData[leftIndex]);
            } else if (rightData[rightIndex].startsWith("[") && !leftData[leftIndex].startsWith("[")) {
                //convert left to list, compare
                String newList = "[" + leftData[leftIndex] + "]";
                result = isRight(rightData[rightIndex], newList);
            } else {
                //convert to int
                // if not same, return left < right
                int rightNumber = Integer.parseInt(rightData[rightIndex]);
                int leftNumber = Integer.parseInt(leftData[leftIndex]);
                if (rightNumber != leftNumber){
                    return leftNumber < rightNumber;
                }
            }
            rightIndex++;
            leftIndex++;
        }
        if (result == null && rightData.length != leftData.length){
            return leftData.length < rightData.length;
        }
        return result;
    }

    public static String[] fancySplit(String toParse){
        toParse = toParse.substring(1, toParse.length() - 1);
        if (toParse.length() == 0){
            return new String[0];
        }
        ArrayList<String> pieces = new ArrayList<>();
        int start = 0;
        int comma = -1;
        int numOpens = 0;
        int numCloses = 0;
        int curr = 0;
        while (curr < toParse.length()){
            char l = toParse.charAt(curr);
            if (l == ','){
                if (numOpens == 0){
                    pieces.add(toParse.substring(start, curr));
                    start = curr + 1;
                }
            } else if (l == '['){
                numOpens++;
            } else if (l == ']'){
                numOpens--;
            }
            curr++;
        }
        pieces.add(toParse.substring(start, curr));
        String[] toReturn =  pieces.toArray(new String[]{});
        return toReturn;
    }
}
