package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day14-Input.txt"));

        int offset500 = 200;
        int depth = 200;
        int floor = 0;
        boolean[][] data = new boolean[depth][offset500 * 2 + 1];
        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);


            String[] moves = line.split(" -> ");
            String lastMove = moves[0];
            for (int i = 1; i < moves.length; i++) {
                String move = moves[i];
                String[] moveData = move.split(",");
                String[] lastMoveData = lastMove.split(",");
                int startI = Math.min(Integer.parseInt(lastMoveData[1]), Integer.parseInt(moveData[1]));
                int tempstartJ = Integer.parseInt(lastMoveData[0]) - 500 + offset500;

                int endI = Math.max(Integer.parseInt(lastMoveData[1]), Integer.parseInt(moveData[1]));
                int tempendJ = Integer.parseInt(moveData[0]) - 500 + offset500;

                int startJ = Math.min(tempstartJ, tempendJ);
                int endJ = Math.max(tempstartJ, tempendJ);

                floor = Math.max(floor, endI);

                for (int markI = startI; markI <= endI; markI++) {
                    for (int markJ = startJ; markJ <= endJ; markJ++) {
                        data[markI][markJ] = true;
                    }
                }
                lastMove = move;
            }
        }


        for (int j = 0 ; j < data[0].length; j++){
            data[floor + 2][j] = true;
        }


        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                if (i == 0 && j == offset500){
                    System.out.print("+");
                }else if (data[i][j]){
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        Point p = new Point(0, offset500);
        while (!data[1][offset500] || ! data[1][offset500 - 1] || !data[1][offset500 + 1]){
            goRest(p, data);
            total++;
            p = new Point(0, offset500);
        }



        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[0].length; j++){
                if (i == 0 && j == offset500){
                    System.out.print("+");
                }else if (data[i][j]){
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        System.out.println("Answer: " + total);

        scan.close();
    }

    // treating point like i, j. Don't let x and y confuse you
    public static boolean goRest(Point p, boolean[][] data){
        while (p.x < data.length && p.y < data[0].length){
            if (p.x + 1 == data.length){
                return false;
                //reached the abyss!
            }
            if (!data[p.x + 1][p.y]){
                p.x += 1;
            } else if (!data[p.x+1][p.y-1]){
                p.x +=1;
                p.y -= 1;
            } else if (!data[p.x+1][p.y+1]){
                p.x +=1;
                p.y += 1;
            } else {
                data[p.x][p.y] = true;
                return true;
                //we're at rest!
            }
        }
        return false; // we hit an edge...?
    }
}
