import java.util.Scanner;
import java.util.Stack;
import java.awt.Point;

public class Main
{
  public static char floorData[][][];
  public static int maxRoom[];
  public static int maxComputers[];
    
  public static void main(String args[])
  {
    int i,j;
    Scanner in = new Scanner(System.in);
    int floors = in.nextInt();
    in.nextLine();
    String data;

    maxComputers = new int[floors];
    
    int height = 0;
    int width = 0;
    int l,x,y,z;
    
    char curr;
    
    floorData = new char[15][600][600];
    for (z=0; z<floors; ++z) {

      data = in.nextLine();
      for (j=0; data.length() >= 2; ++j) {
        
        floorData[z][j] = data.toCharArray();
        if (!in.hasNextLine()) break;
        data = in.nextLine();
      }

      // figure out width and height...
      for (j=0;j<600;j++) {
        if (floorData[z][j].length == 600) { 
          height = j;
          break;
        }
      }
      for (j = 0; j<height; ++j) {
        l = floorData[z][j].length;
        if (l > width) width = l;
      }

      // now trace around...

      maxComputers[z] = 0;
      
      for (x=0;x<width;++x){
        for(y=0;y<height;++y){
          try {
            char f = floorData[z][y][x];
            if (f == ' ' || f == 'S' || f == 'C') {
              traceRoom(x,y,z);
            }
          } catch (Exception e) {
            // this is outside the walls. ignore.
          }
        }
      }

      // End of floor.
    }
    int max = 0;
    int mfl = 0;

    for (int fl = 0; fl < floors; ++fl) {
      
      if (maxComputers[fl] > max) {
          max = maxComputers[fl];
          mfl = fl;
      }
    }

    System.out.printf("%d %d\n",mfl+1,max);

  }

  public static void traceRoom(int x, int y, int z) {
    Stack<Point> check = new Stack<Point>();
    check.push(new Point(x,y));
    Point p;
    
    int numComputers = 0;

    while (!check.empty()) {
      p = check.pop();
      try {
        char f = floorData[z][p.y][p.x];
        if (f == ' ' || f == 'S' || f == 'C') {
          check.push(new Point(p.x-1,p.y));
          check.push(new Point(p.x+1,p.y));
          check.push(new Point(p.x,p.y-1));
          check.push(new Point(p.x,p.y+1));
   
          check.push(new Point(p.x-1,p.y-1));
          check.push(new Point(p.x+1,p.y+1));
          check.push(new Point(p.x+1,p.y-1));
          check.push(new Point(p.x-1,p.y+1));
   
          if (f == 'C') numComputers++;
          floorData[z][p.y][p.x] = 'X';
        }
      } catch (Exception e) {
        // bleh.
      }
    }

    if (numComputers > maxComputers[z]) {
      maxComputers[z] = numComputers;
    }
  }
  
}
