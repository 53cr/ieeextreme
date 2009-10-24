import java.util.Scanner;
import java.math.BigInteger;


public class Main
{
  public static void p(String a){
    System.out.println(a);
  }
 
  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    int d, n;
    while(in.hasNextInt()){
      d = in.nextInt();
      n = in.nextInt();
      p(compute(d,n)); 
    }
 
  }
  public static String compute(int d, int y){
    return "/ " + Math.pow(6,d);
  }
}

