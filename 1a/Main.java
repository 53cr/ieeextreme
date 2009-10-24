import java.util.Scanner;

public class Main {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);

    int cases = in.nextInt();
    Peak p;

    Peak[] peaks   = new Peak[cases];
    Peak[] skyline = new Peak[11000];
    
    for (int i=0; i<cases; ++i) {
      p = new Peak(in.next(), in.nextInt(), in.nextInt(), in.nextInt());
      peaks[i] = p;

      if (skyline[p.x]==null // nothing at this south-north coord yet.
          || (p.y > skyline[p.x].y) // this is higher than the existing one.
          || (p.y == skyline[p.x].y && p.z < skyline[p.x].z)) // Same height, but this is more east.
        skyline[p.x] = p; 
    }

    for (int i=0; i<cases; ++i)
      for (int j=0; j<cases; ++j)
        if (i!=j) peaks[i].occlude(peaks[j]);
    
    for (int i=0; i<11000; ++i)
      if (skyline[i] != null && !skyline[i].hidden)
        System.out.println(skyline[i].s);
  }
}

class Peak {
  public String s;
  public int x,y,z;
  public Boolean hidden;
  
  public Peak(String s, int x, int z, int y) {
    this.s = s;
    this.x = x;
    this.y = y;
    this.z = z;
    this.hidden = false;
  }

  public void occlude(Peak other) {
    if (!this.hidden && (2*Math.abs(this.x - other.x) <= ((this.y - other.y))))
      other.hidden = true;
  }
  
}
