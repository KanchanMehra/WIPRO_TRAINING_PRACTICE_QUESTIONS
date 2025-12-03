/*Equal character in string
You are given a string s. In one move you can change any character to another character.
You have to make a string which consists of the same character. Find the minimum move to do this task. */

import java.util.Scanner;

public class EqualCharInString {
    public static void main(String[] args) {

        //write your answer here
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] count = new int[26];

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            count[ch - 'a']++;
        }
        int maxFreq = 0;
        for(int i = 0; i < 26; i++){
            if(count[i] > maxFreq) {
                maxFreq = count[i];
            }
        }
        int minMoves = s.length() - maxFreq;
        System.out.println(minMoves);
    }
}


