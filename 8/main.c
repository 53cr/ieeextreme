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
  Fraction rtn;
  long GCD = gcd(a.num, a.denom);
  rtn.num = a.num / GCD;
  rtn.denom = a.denom / GCD;

  return rtn;
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



int main()
{

/*   while(/\*more input*\/) */
/*   { */
/*     /\*dummy*\/ = input(); */
    

/*   } */

  //printf("%ld", sizeof(long));
  Fraction f = makeFrac(36, 81);
  
  printf("%ld/%ld\n", f.num, f.denom);
}
