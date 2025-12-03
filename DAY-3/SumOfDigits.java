/*Sum of All Numbers
Find the sum of the digits using recursion */

import java.util.Scanner;
public class SumOfDigits {
    public static int sumDigits(int n){
        if(n == 0){
            return 0;
    }
    return (n % 10) + sumDigits(n / 10);
}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num= sc.nextInt();
        int sum = sumDigits(num);
        System.out.println(sum);
        sc.close();
    }
}