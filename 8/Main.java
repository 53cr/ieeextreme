import java.util.Scanner;
import java.math.BigInteger;


public class Main
{

  private static class Fraction
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

      return (new Fraction(newNum, newDenom)).simplify();
    }
    public Fraction multiply(Fraction other)
    {
      int a = this.num;
      int b = this.denom;
      int c = other.num;
      int d = other.denom;

      int newNum = a*c;
      int newDenom = b*d;

     
      return (new Fraction(newNum, newDenom)).simplify();
    }

    public Fraction simplify()
    {
      int a = this.num;
      int b = this.denom;

      int GCD = gcd(a,b);

      return new Fraction(a / GCD, b / GCD);
    }

    private int gcd(int a, int b)
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
      return a;    
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



  public static Fraction compute(int d, int n)
  {
    Fraction dieprobs[] = new Fraction[6];
    Fraction oldprobs[] = new Fraction[6];
    for(int i = 0; i < 6; i++)
    {
      dieprobs[i] = new Fraction(1,6);
      oldprobs[i] = new Fraction(1,6);

      System.out.println("dieprobs[i] = " + dieprobs[i]);
      System.out.println("oldprobs[i] = " + oldprobs[i]);
    }

    for(int i = 0; i < d-1; i++)
    {
      oldprobs = convolve(dieprobs, oldprobs);
    }
    n -= d;
    if(n < 0)
    {
      n = 0;
    }
    Fraction accum = new Fraction(0,1);

    System.out.println("oldprobs.length: " + oldprobs.length);
    
    for(int i = n; i < oldprobs.length; i++)
    {
      System.out.println("oldprobs[i]: " + oldprobs[i]);
      accum = accum.add(oldprobs[i]);
    }
    return accum;
  }

  
  public static Fraction[] convolve(Fraction[] xn, Fraction[] hn)
  {
    int i, j, k;
    Fraction[] yn = new Fraction[(xn.length + hn.length - 1)];

   
    // start convolution from out[kernelLength-1] to out[dataLength-1] (last)
    for(i = hn.length-1; i < yn.length; ++i)
    {
      yn[i] = new Fraction(0,1);                             // init to 0 before accumulate

      for(j = i, k = 0; k < hn.length; --j, ++k)
      {
        System.out.println("i: "+i+" j: "+j+" k: "+k);
        Fraction x;
        if(j >= 0 && j < xn.length)
          x = xn[j];
        else
          x = new Fraction(0,1);
        Fraction h;
        if(k>= 0 && j < hn.length)
          h = hn[k];
        else
          h = new Fraction(0,1);
        yn[i] = yn[i].add(x.multiply(h));
        //  System.out.println("yn[i] = " + yn[i]);
      }
      System.out.println("yn["+i+"] = " + yn[i]);
    }

    // convolution from out[0] to out[kernelLength-2]
    System.out.println("DEBUG: Before second loop");
    for(i = 0; i < hn.length - 1; ++i)
    {
      yn[i] = new Fraction(0,1);                             // init to 0 before sum

      for(j = i, k = 0; j >= 0; --j, ++k)
      {
        System.out.println("i: "+i+" j: "+j+" k: "+k);
        Fraction x;
        if(j >= 0 && j < xn.length)
          x = xn[j];
        else
          x = new Fraction(0,1);
        Fraction h;
        if(k >= 0 && k < hn.length)
          h = hn[k];
        else
          h = new Fraction(0,1);
        yn[i] = yn[i].add(x.multiply(h));
      }
      System.out.println("yn["+i+"] = " + yn[i]);
    }

//     for(int z= 0; z < yn.length; z++)
//       System.out.println("yn[" + z + "] = " + yn[z]);
    
    return yn;
  }



  
}

