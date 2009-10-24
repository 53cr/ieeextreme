class Integer
  def isPrime?
    if self <2
      return false
    elsif self < 4
      return true
    elsif self % 2 == 0
      return false
    elsif self < 9
      return true
    elsif self % 3 == 0
      return false
    else
      r = (self**0.5).floor
      f = 5
      f.step r,6 do |g|
        if self % g == 0
          return false
        end
        if self % (g + 2) == 0
          return false
        end
      end
      return true
    end
  end
end

def doit(head,tail,totals,primes,length)
  if head.isPrime? and !primes.include? head
    primes << head
    totals[length] += 1;
  end
  0.upto(tail.length-1) do |i|
    new_tail = tail[i+1..-1]
    doit(head*10+tail[i], new_tail,totals, primes, length+1)
  end
end


nums = gets.strip.split('').map(&:to_i)

counts = Array.new(10,0) 
primes = []
doit(0,nums,counts,primes,-1)
puts counts.inject(&:+)
0.upto(nums.count-1) {|i| puts counts[i]}
