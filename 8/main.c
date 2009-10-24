#include <stdlib.h>
#include <stdio.h>

typedef struct fraction{
  long num;
  long denom;
} Fraction;

long gcd(long a, long b);


long gcd(long a, long b)
{
  long tmp = 0;
  if (b > a)
  {
    tmp = b;
    b = a;
    a = tmp;
  }  
  while(b != 0)
  {
    tmp = b;
    b = a % b;
    a = tmp;
  }
  return a;
}

Fraction simplify(Fraction a)
{
  if(a.num == 0 && a.denom == 0)
  {
    return a;
  }
  else
  {
    Fraction rtn;
    long GCD = gcd(a.num, a.denom);
    rtn.num = a.num / GCD;
    rtn.denom = a.denom / GCD;

    return rtn;
  }
}


Fraction makeFrac(long num, long denom)
{
  Fraction rtn;
  rtn.num = num;
  rtn.denom = denom;
  rtn = simplify(rtn);
  return rtn;
}

Fraction addFrac(Fraction a, Fraction b)
{
  Fraction rtn;
  rtn.num = a.num * b.denom + a.denom * b.num;
  rtn.denom = a.denom * b.denom;

  return simplify(rtn);
}


Fraction multFrac(Fraction a, Fraction b)
{
  Fraction rtn;
  rtn.num = a.num * b.num;
  rtn.denom = a.denom * b.denom;

  return simplify(rtn);
}

int convolve1D(int* xn, int* yn, int dataSize, int* hn, int hsize)
{
    int i, j, k;

   
    // start convolution from out[kernelSize-1] to out[dataSize-1] (last)
    for(i = hsize-1; i < dataSize; ++i)
    {
        yn[i] = 0;                             // init to 0 before accumulate

        for(j = i, k = 0; k < hsize; --j, ++k)
            yn[i] += xn[j] * hn[k];
    }

    // convolution from out[0] to out[kernelSize-2]
    for(i = 0; i < hsize - 1; ++i)
    {
        yn[i] = 0;                             // init to 0 before sum

        for(j = i, k = 0; j >= 0; --j, ++k)
            yn[i] += xn[j] * hn[k];
    }

    return 0;
}

int main()
{
  int D = 0;
  int N = 0;



/*   int[] arr1 = [0.1666, 0.1666, 0.1666, 0.1666, 0.1666, 0.1666]; */
  int in[] = {1,2,3,4,5,6};
  int out[] = {0,0,0,0,0,0,0,0,0,0,0};
  
  convolve1D(in, out, 6, in, 6);
  int i;
  for(i=0; i < 11; i++)
  {
    printf("out[%d] = %d\n",i, out[i]);
  }


  
  while(scanf("%D %D", &D, &H))
  {
    
    
    

  }
}
