m,lower,upper = ARGV.shift.to_i, ARGV.shift.to_i, ARGV.shift.to_i
digits = ARGV.shift.split('').map(&:to_i)

#puts "m,lower,upper = #{m}, #{lower}, #{upper}"
#puts "digits = #{digits.inspect}"

valids = []
lower.upto(upper) do |i|
  if i % m == 0
    orig = i
    while i > 0 and digits.include?(i%10)
      i /= 10
    end
    if i == 0
#      puts orig
      valids << orig
    end
  end
end

puts valids.count

