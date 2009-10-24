ingredients = %W| a b c d e f g h i j|.map(&:to_sym)
class Array
  def squish(other)
    crossover_max = [self.size, other.size].min
    crossover_max.downto(1) do |i|
      if self.last(i) == other.first(i)
        return self + other[i..-1]
      elsif other.last(i) == self.first(i)
        return other + self[i..-1]
      end
    end
    return []
  end
end

def generate_combinations(array, r)
  n = array.length
  indices = (0...r).to_a
  final = (n - r...n).to_a
  while indices != final
    yield indices.map {|k| array[k]}
    i = r - 1
    while indices[i] == n - r + i
      i -= 1
    end
    indices[i] += 1
    (i + 1...r).each do |j|
      indices[j] = indices[i] + j - i
    end
  end
  yield indices.map {|k| array[k]}
end

def valid(array,original)
  array = array.flatten.each_cons(5).to_a
  array = array.uniq.reject {|cons| cons.uniq.size != cons.size }
  array = array.map {|cons| cons.sort {|a,b| a.to_s <=> b.to_s} }
  array = array.sort {|a,b| a.map(&:to_s) <=> b.map(&:to_s) }
  array.uniq.size == original.uniq.reject {|cons| cons.uniq.size != cons.size }.map {|cons| cons.sort {|a,b| a.to_s <=> b.to_s} }.sort {|a,b| a.map(&:to_s) <=> b.map(&:to_s) }.size
end

original = []
generate_combinations(ingredients,5) { |c| original << c }

smallest = { :size => 1.0/0, :string => "" }

loop do
  all = original.clone.map(&:shuffle)
  all.size.times do
    a = all.shift
    merged, old = all.reduce([[],[],-1.0/0]) do |best,curr|
      original_size = a.size + curr.size
      merged = a.squish(curr)
      if !merged.empty?
        delta_size = original_size - merged.size
        if delta_size > best[-1]
          best = [ merged, curr, delta_size ]
        end
      end
      best
    end
    if merged != []
      all.delete(old)
      all << merged
#      puts "#{a.inspect} + #{old.inspect} => #{merged.inspect}"
    else
      all << a
    end
    if !valid(all,original)
      puts "#{a.inspect} + #{old.inspect} => #{merged.inspect}"
      quit
    end
  end
  candidate = all.join
  if candidate.length < smallest[:size]
    smallest[:string] = candidate
    smallest[:size] = candidate.length
    puts "#{candidate.length}: #{candidate}"
  end
end


