/*Multiple Catch Blocks
You are required to catch Arithmetic or number format exceptions if present using 
multiple catch blocks in the code for finding of 99 with the number entered by user, 
else print "n is a factor of 99" or "n is not a factor of 99". */

import java.util.Scanner;
public class MultiCatchBlocks {
    public static void main(String[] args) {
 Scanner scn = new Scanner(System.in);
        //write your answer here
        try {
            String input = scn.nextLine();
            int n = Integer.parseInt(input);
            if(99 % n == 0){
                System.out.println(n + " is a factor of 99");
            } else {
                System.out.println(n + " is a not a factor of 99");
            }
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception " + e);
        } catch (NumberFormatException e ) {
            System.out.println("Number Format Exception " + e);
        }
    }
}


