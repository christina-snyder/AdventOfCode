package Archive_2021;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("src/main/java/Archive_2021/Day1-input.txt"));
        //int last = Integer.parseInt(scan.nextLine());
        int A = Integer.parseInt(scan.nextLine());
        int B = Integer.parseInt(scan.nextLine());
        int C = Integer.parseInt(scan.nextLine());
        int total = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println(line);

           /* int curr = Integer.parseInt(line);
            if (curr > last){
                total ++;
            }

            last = curr;*/

            int D = Integer.parseInt(line);

            if (A + B + C < B + C + D){
                total++;
            }

            A = B;
            B = C;
            C = D;

        }

        System.out.println("Answer: " + total);

        scan.close();
    }
}
