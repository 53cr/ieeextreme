


def f(x)
  foo = (x+1)**2
  (0..x).each do |n|
    foo += 2*(n**2)
  end
  foo
end

