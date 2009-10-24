import java.util.Scanner;
import java.math.BigInteger;


public class Main
{
  
  private static class Fraction
  {
    BigInteger num;
    BigInteger denom;

    public Fraction()
    {
      num = BigInteger.ZERO;
      denom = BigInteger.ONE;
    }
    
    public Fraction(BigInteger a, BigInteger b)
    {
      if(a.equals(BigInteger.ZERO))
      {
        num = BigInteger.ZERO;
        denom = BigInteger.ONE;
      }
      else
      {
        BigInteger GCD = a.gcd(b);
        num = a.divide(GCD);
        denom = b.divide(GCD);
      }
    }
    
    public Fraction add(Fraction other)
    {
      BigInteger a = num;
      BigInteger b = denom;
      BigInteger c = other.num;
      BigInteger d = other.denom;

      BigInteger newNum = a.add(d).multiply(b.add(c));
      BigInteger newDenom = b.multiply(d);
      BigInteger GCD = newNum.gcd(newDenom);
      return new Fraction(newNum.divide(GCD), newDenom.divide(GCD));
    }

    public Fraction multiply(Fraction other)
    {
      BigInteger a = num;
      BigInteger b = denom;
      BigInteger c = other.num;
      BigInteger d = other.denom;

      BigInteger newNum = a.multiply(c);
      BigInteger newDenom = b.multiply(d);

      BigInteger GCD = newNum.gcd(newDenom);
      return new Fraction(newNum.divide(GCD), newDenom.divide(GCD));
    }

    public void print()
    {
      String printstr = "";
      if(num.equals(BigInteger.ZERO))
      {
        printstr = "0";
      }
      else if(num.equals(BigInteger.ONE) && denom.equals(BigInteger.ONE))
      {
        printstr = "1";
      }
      else
      {
        printstr = num.toString();
        printstr += "/";
        printstr += denom.toString();
      }
      System.out.println(printstr);
    }
  }

  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    in.useDelimiter("\n");

    while(in.hasNext())
    {
      String instr = in.next();
      String[] inNums = instr.split("\\s+");
      int D = Integer.parseInt(inNums[0]);
      int N = Integer.parseInt(inNums[1]);
       
      Fraction calcVal = new Fraction();
      for(int i = N; i <= 6*D; i++)
      {
        calcVal = calcVal.add(calc(D, i));
      }

      calcVal.print();
    }
  }
  
  public static Fraction calc(int D, int N)
  {

    Fraction powVal =
      new Fraction(
        new BigInteger("1"), new BigInteger("6").pow(D));

    Fraction sumVal = new Fraction();

    for(int i = 0; i <= ((N-D)/6); i++)
    {
      int coef = -1;
      if(i % 2 == 0)
      {
        coef = 1;
      }
      BigInteger middle = bin(D, i);
      BigInteger end = bin(N - (6*i) - 1, N-1);
      BigInteger total = middle.add(end);
      total = total.add(new BigInteger(String.valueOf(coef)));

      sumVal.add( new Fraction(total, BigInteger.ONE));
    }
    return sumVal.multiply(powVal);
  }

  public static BigInteger bin(int n, int r)
  {
    if(r > n)
      return BigInteger.ZERO;

    if(r > (n/2))
      r = n-r;

    BigInteger accum = BigInteger.ONE;

    for(int i = 1; i <= r; i++)
    {
      accum =
        accum.multiply(new BigInteger( String.valueOf(n-r+i))).divide(
          new BigInteger(String.valueOf(i)));
    }

    return accum;
  }
}

