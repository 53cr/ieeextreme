
struct fraction{
  long long num = 0;
  long long denom = 0;
};

typedef struct fraction Fraction;



long long main
{

  while(/*more input*/)
  {
    /*dummy*/ = input();
    

  }
}


Fraction makeFrac(long long num, long long denom)
{
  Fraction rtn;
  rtn.num = num;
  rtn.denom = denom;
  return simplify(rtn);
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

Fraction simplify(Fraction a)
{
  Fraction rtn;
  long long GCD = gcd(a.num, a.denom);
  rtn.num = a.num / GCD;
  rtn.denom = a.denom / GCD;

  return rtn;
}

long long gcd(a, b)
{
  long long tmp = 0;
  if (b > a)
    gcd(b, a);
  else
  {
    while(b != 0)
    {
      t = b;
      b = a % b;
      a = t;
    }
    return a;
  }
}
