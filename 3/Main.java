import java.util.Scanner;
public class Main {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    long x = in.nextInt();
    long foo = (long)Math.pow(x+1, 2);
    for (int i=0; i<=x; ++i) {
      foo += 2*(long)Math.pow(i,2);
    }
    System.out.println(foo);
  }
}