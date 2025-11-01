/*Reverse each word in a string
Write a function to reverse each word in a string. */

import java.util.*;

public class ReverseEachWord {
    public static String revEachWord(String s) {
        String[] words = s.split(" ");
        String result = "";

        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            String rev = "";

            for(int j = word.length() - 1; j >= 0; j--){
                rev += word.charAt(j);
            }
            result = result + rev;
            if(i < words.length - 1){
                result = result + " ";
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(revEachWord(str));
    }
}
    