import java.util.Scanner;

public class Main
{
  public static Peak[] skyline;

  public static Peak[] peaks;
  
  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    int cases = in.nextInt();
    in.nextLine();
    String data;

    peaks = new Peak[cases];
    
    Peak p;

    skyline = new Peak[10000];
    for (int i=0; i<10000; ++i) {
      skyline[i] = null;
    }
    
    for (int i=0; i<cases; ++i) {
      p = new Peak(in.next(), in.nextInt(), in.nextInt(), in.nextInt());
      peaks[i] = p;
      
      if (skyline[p.x]==null ||
          (p.y > skyline[p.x].y) || 
          (p.y == skyline[p.x].y && p.z < skyline[p.x].z))
      {
        skyline[p.x] = p;
      }

    }

    for (int i=0; i<cases; ++i) {
      for (int j=0; j<cases; ++j) {
        if (i!=j) {
          if (peaks[i].occludes(peaks[j])) {
            peaks[j].hidden = true;
          }
        }
      }
    }
    
    for (int i=0; i<10000; ++i) {
      if (skyline[i] != null && !skyline[i].hidden) {
        System.out.println(skyline[i].s);
      }
    }
    
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

  public Boolean occludes(Peak other) {
    switch((new Integer(2*Math.abs(this.x - other.x))).compareTo((this.y - other.y))) {
    case -1:
      return true;
    case 0:
      return (other.z < this.z);
    case 1:
      return false;
    }
    return false;
  }
  
}
