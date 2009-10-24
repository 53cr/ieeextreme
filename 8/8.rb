require 'rational'

class Integer
  def fact
    return 1 if self == 0
    (1..self).inject { |i,j| i*j }
  end
end

def choose(n,k)
  n.fact / (k.fact * (n-k).fact)
end

def compute(k,i)
  s=6
  Rational(1,s ** i) * (0..((k-i)/s)).inject(0) do |sum,n|
    sum + (-1 ** n) * choose(i,n) * choose(k-sn-1, i-1)
  end
end

while(line = gets)
  n,d = line.split(' ').map(&:to_i)
  puts compute(n,d)
  puts "#{n}, #{d}"
end
