import java.util.Scanner;
import java.math.BigInteger;


public class Main
{

  private class Fraction
  {
    public int num;
    public int denom;

    public Fraction(int n, int d)
    {
      num = n;
      denom = d;

      if(n == 0)
      {
        num = 0;
        denom = 1;
      }
      
    }

    public Fraction add(Fraction other)
    {
      int a = this.num;
      int b = this.denom;
      int c = other.num;
      int d = other.denom;

      int newNum = (a*d) + (b*c);
      int newDenom = b*d;

      return (new Fraction(newNum, newDenom)).simplify;
    }
    public Fraction multiply(Fraction other)
    {
      int a = this.num;
      int b = this.denom;
      int c = other.num;
      int d = other.denom;

      int newNum = a*c;
      int newDenom = b*d;

     
      return (new Fraction(newNum, newDenom)).simplify;
    }

    public void simplify()
    {
      int a = this.num;
      int b = this.denom;

      int GCD = gcd(a,b);

      this.num /= gcd;
      this.denom /= gcd;
    }

    private gcd(int a, int b)
    {
      if (b > a)
      {
        int tmp = a;
        a = b;
        b = tmp;
      }
      while(b != 0)
      {
        int tmp = b;
        b = a % b;
        a = tmp;
      }
      return a     
    }

    public String toString()
    {
      String rtn = "";

      if (num == denom)
      {
        rtn = "1";
      }
      else if(num == 0)
      {
        rtn = "0";
      }
      else
      {
        rtn = num + "/" + denom;
      }
      return rtn;
    }
  }

  
  public static void p(String a)
  {
    System.out.println(a);
  }
 
  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    int d, n;
    while(in.hasNextInt())
    {
      d = in.nextInt();
      n = in.nextInt();
      System.out.println(compute(d,n));
    }
 
  }



  public static void compute(int d, int n)
  {
    Fraction dieprobs[] = new Fraction[6];
    Fraction oldprobs[] = new Fraction[6];
    for(int i = 0; i < 6; i++)
    {
      dieprobs[i] = new Fraction(1,6);
      oldprobs[i] = new Fraction(1,6);
    }

    for(int i = 0; i < d-1; i++)
    {
      oldprobs = convolve(dieprobs, oldprobs);
    }
    n -= d;
    Fraction accum = new Fraction(0,1);
    for(int i = n; i <= 6*d; i++)
    {
      accum = accum.add(oldprobs[i]);
    }
  }

  
  int[] convolve(int[] xn, int[] hn)
  {
    int i, j, k;
    int[] yn = new int[(xn.size + hn.size - 1)];

   
    // start convolution from out[kernelSize-1] to out[dataSize-1] (last)
    for(i = hn.size-1; i < xn.size; ++i)
    {
      yn[i] = new Fraction(0,1);                             // init to 0 before accumulate

      for(j = i, k = 0; k < hn.size; --j, ++k)
        yn[i] = yn[i].add(xn[j].multiply(hn[k]))
    }

    // convolution from out[0] to out[kernelSize-2]
    for(i = 0; i < hn.size - 1; ++i)
    {
      yn[i] = new Fraction(0,1);                             // init to 0 before sum

      for(j = i, k = 0; j >= 0; --j, ++k)
        yn[i] = yn.add(xn[j].multiply(hn[k]));
    }

    return yn;
  }



  
}

