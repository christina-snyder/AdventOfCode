package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day9-Input.txt"));
        ArrayList<Coord> beenThere = new ArrayList<>();
        Coord head = new Coord(0, 0);
        Coord tail = new Coord(0, 0);
        beenThere.add(new Coord(0, 0));

        ArrayList<Coord> longRope = new ArrayList<>();
        for (int i =0 ; i <10; i++){
            longRope.add(new Coord(0, 0));
        }

        //boolean map- has been there
        //String map- curr pos
        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);

            //grab move
            String move = line.substring(0, 1);
            int amt = Integer.parseInt(line.substring(2));
            //move one at a time
            for (int count = 0; count < amt; count++){
                /*head.updatePos(move);
                if (!tail.isAdj(head)){
                    tail.moveToward(head);
                    if (!beenThere.contains(new Coord(tail.x, tail.y)))
                        beenThere.add(new Coord(tail.x, tail.y));
                }
                */

                longRope.get(0).updatePos(move);
                for (int i = 1; i < 10; i++){
                    Coord leader = longRope.get(i - 1);
                    Coord follow = longRope.get(i);
                    if (!follow.isAdj(leader)) {
                        follow.moveToward(leader);
                    }
                }
                tail = longRope.get(9);
                if (!beenThere.contains(new Coord(tail.x, tail.y)))
                    beenThere.add(new Coord(tail.x, tail.y));
                /*System.out.println("HEAD: " + head);
                System.out.println("TAIL: " + tail);
                System.out.println("--");
                */
            }
                //move head, update pos map
                //tail adj?
                    //no -> move tail, update pos map
                    //update boolean map



        }

        System.out.println("Answer: " + beenThere.size());

        scan.close();
    }
}

class Coord{
    int x, y;

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }


    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    public boolean equals(Object obj){
        Coord other = (Coord) obj;
        return x == other.x && y == other.y;
    }

    public boolean isAdj(Coord other){
        return Math.abs(x - other.x) <= 1 && Math.abs(y - other.y) <= 1;
    }

    public void updatePos(String dir){
        switch (dir){
            case "R":
                x++;
                return;
            case "L":
                x--;
                return;
            case "D":
                y--;
                return;
            case "U":
                y++;
        }
    }

    public void moveToward(Coord other){
        if (this.equals(other)){
            return;
        }
        if (x < 0 || y < 0){
            //System.out.println("NEGATIVESSS");
        }
        if (x == other.x){
            if (y > other.y){
                y--;
            } else {
                y++;
            }
        } else if (y == other.y){
            if (x > other.x){
                x--;
            } else {
                x++;
            }
        } else if (x > other.x){
            if (y > other.y){
                x--;
                y--;
            } else {
                x--;
                y++;
            }
        } else {
            //x < other.x
            if (y > other.y){
                y--;
            } else {
                y++;
            }
            x++;
        }
    }
}
