import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    String numberString = in.nextLine();

    int[] numbers = new int[numberString.length()];
    for(int i = 0; i < numberString.length(); i++) {
      numbers[i] = Integer.parseInt(Character.toString(numberString.charAt(i)));
    }

<<<<<<< HEAD:5/Main.java
    //System.out.printf("numberString.length: %d\n", numberString.length());
    int[] counts = new int[numberString.length()];
=======
//    System.out.printf("numberS.length: %d\n", numberS.length());
    int[] counts = new int[numberS.length()];
>>>>>>> e90e61b74f61bd3526e349f5219891db3061cf9b:5/Main.java
    ArrayList<Integer> primes = new ArrayList<Integer>();
    doit(0,numbers, counts, primes, -1,0);
    
    int total = 0;
    for(int i=0; i < counts.length; i++) {total += counts[i];}
    System.out.println(total);
    for(int i=0; i < counts.length; i++) { System.out.println(counts[i]);}

//    System.out.println("");
//    for(int i=0; i < primes.size(); i++) { System.out.println(primes.get(i));}

  }

  public static void doit(int head, int[] tail, int[] counts, ArrayList<Integer> primes, int length, int tailStart) {
    Integer headInteger = new Integer(head);
    if(prime(head) && !primes.contains(headInteger)) {
      primes.add(headInteger);
//      System.out.printf("Digit: %d, Len: %d\n",head,length+1);
      counts[length]++;
    }
    for(int i = tailStart; i < tail.length; i++) {
      doit(head*10+tail[i], tail, counts, primes, length + 1,i+1);
      doit(tail[i],tail,counts,primes,0,i+1);
    }
  }

  public static boolean prime(int N) {
    boolean isPrime = true;
    if (N < 2) isPrime = false;
    for (long i = 2; i*i <= N; i++) {
      if (N % i == 0) {
        isPrime = false;
        break;
      }
    }
    return isPrime;
  }
}
