import java.util.*;
import java.math.*;

public class Main
{

  private class Peak implements Comparable
  {

    public int x;
    public int y;
    public int h;
    
    public Peak(String xp, String yp, String hp)
    {
      x = Integer.parseInt(xp);
      y = Integer.parseInt(yp);
      h = Integer.parseInt(hp);
    }

    public int distTo(Peak other)
    {
      return Math.abs(other.y - y);
    }

    public int heightDueTo(Peak other)
    {
      dist = this.distTo(other);
      return 2 * dist;
    }

    public int compareTo(Peak other)
    {
      return other.y - this.y;
    }
  }
  

//x.compareTo(y) < 0 if x < y 

  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    int cases = in.nextInt();
    String data;

    HashMap<String, Peak> peaks = new HashMap<String, Peak>();
    TreeSet<Peak> answer = new TreeSet<Peak>();
    
    
    for (int i=0; i<cases; ++i)
    {
      data = in.nextLine();
      String[] tmp = data.split("\\s+");

      peaks.put(tmp[0], new Peak(tmp[1],tmp[2], tmp[3]));
      
    }

    Iterator iter = peaks.keySet().iterator();
    String key = "";
    while(iter.hasNext())
    {
      key = iter.next();
      Iterator iter2 = peaks.keySet().remove(key).iterator();
      String key2 = "";
      Boolean blocked = false;
      while(!blocked && iter2.hasNext())
      {
        key = iter2.getNext();
        if( peaks.get(key2).heightDueTo() > peaks.get(key).h)
          blocked = true;
        
      }
      if (!blocked)
        answer.add(key);
    }
  }

  Iterator iter = answer.iterator();
  while(iter.hasNext())
  {
    String tmp = iter.next();
    System.out.println(tmp);
  }
}
