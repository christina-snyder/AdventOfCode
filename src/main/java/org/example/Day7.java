package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/org/example/Day7-Input.txt"));

        ArrayList<Directory> CWD = new ArrayList<>();
        ArrayList<Integer> collectedDirectories = new ArrayList<>();
        boolean collectMode = false;
        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);
            if (line.startsWith("$")){
                collectMode = false;
                if (line.startsWith("$ cd")){
                    String last = line.substring(line.lastIndexOf(" ") + 1);
                    if (last.equals("..")){
                        Directory d = CWD.remove(CWD.size() - 1);
                        collectedDirectories.add(d.amt);
                        if (d.amt <= 100000){
                            total += d.amt;
                        }
                    } else {
                        CWD.add(new Directory(last));
                    }
                } else if (line.startsWith("$ ls")){
                    collectMode = true;
                }
            } else if (line.startsWith("dir")){
                //collectedDirectories.add(0);
            } else {
                //add to all CWD
                int fileSize = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                for (Directory d : CWD){
                    d.amt += fileSize;
                }
            }


        }
        Collections.reverse(CWD);
        for (Directory d : CWD){
            collectedDirectories.add(d.amt);
        }

        //find the smallest number that's bigger than 30000000

        System.out.println("Answer: " + total);


        int totalSize = 70000000;
        int updateSize = 30000000;
        int mySize = collectedDirectories.get(collectedDirectories.size() - 1);/*calcSum(collectedDirectories)*/;
        System.out.println(mySize);
        int unusedSpace = totalSize - mySize;

        int needToDelete = updateSize - unusedSpace;
        //find the smallest number that's bigger than needToDelete

        System.out.println(needToDelete);
        int contender = Integer.MAX_VALUE;
        for (int x : collectedDirectories){
            if (x > needToDelete && x < contender) {
                contender = x;
            }
        }

        System.out.println("Contender: " + contender);

        scan.close();
    }

    public static int calcSum(ArrayList<Integer> l){
        int total = 0;
        for (int x : l){
            total += x;
        }
        return total;
    }
}


class Directory{
    int amt;
    String name;

    public Directory(String name){
        this.name = name;
        amt = 0;
    }

    public boolean equals(Object other){
        return name.equals(((Directory) other).name);
    }
}