//name : ACM Programming Contest Template;
//# --

import java.util.Scanner;
import java.lang.Math.*;
import java.lang.Integer;

public class b
{
  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    int multiples = in.nextInt();
    int lower = in.nextInt();
    int upper = in.nextInt();

    in.nextLine();


    String numbers_raw = in.nextLine();

    int[] digits = new int[10];
    for(int i = 0; i < 10; i++) {digits[i] = -1;}

    for(int i = 0; i < Math.min(10,numbers_raw.length()); i++) {
      digits[i] = Integer.parseInt(numbers_raw.charAt(i)+"");
    }

    //System.out.printf("X: %d, A: %d, B: %d\n", multiples, lower, upper);
    //System.out.printf("Chars[0]: %d",digits[0]);

    int valid = 0;
    int mod = lower % multiples;
    if (mod != 0) 
      lower = lower - mod + multiples;
    for(int i = lower; i <= upper; i += multiples) {
      int n = i;
      //System.out.println("\n"+n);
      if(i%multiples==0) {
        while( n > 0 && contains(digits,n%10)) {
          n /= 10;
          //System.out.println(n);
        }
        if(n == 0) {
          valid += 1;
          //System.out.println(i);
        }
      }
    }

    System.out.println(valid);
  }

  public static boolean contains(int[] array, int n) {
    for(int i = 0; i < array.length; i++) {
      if(array[i] == n)
        return true;
    }
    return false;
  }
}
