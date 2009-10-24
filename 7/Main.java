import java.util.Scanner;
import java.util.Stack;

public class Main
{
  public static char[][] city;
  public static int[][] scores;
  public static int range;
  public static int width;
  public static int height;
  
  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    range = in.nextInt();
    in.nextLine();
    height = in.nextInt();
    width = in.nextInt();
    in.nextLine();

    city = new char[height][1];

    for (int i=0; i<height; ++i) {
      city[i] = in.nextLine().toCharArray();
    }

    //printCity();
    
    for (int x=0; x<width; ++x) {
      for (int y=0; y<height; ++y) {
        if (city[y][x] == 'P') {
          mask(x,y);
        }
      }
    }

    //printCity();

    int cov_max = 0;
    int best_x = 0;
    int best_y = 0;
    int bleh;
    for (int y=0; y<height; ++y) {
      for (int x=0; x<width; ++x) {
        if (city[y][x] == '.') {
          bleh = coverage(x,y);
          if (bleh > cov_max) {
            cov_max = bleh;
            best_x = x;
            best_y = y;
          }
        }
      }
    }

    System.out.printf("%d %d\n%d\n", best_y+1, best_x+1, cov_max);
  }

  public static void mask(int x, int y) {
    
    for (int i=x-range; i<=x+range; ++i) {
      for (int j=Math.max(y-range+(x-i),y-range-(x-i)); j<=Math.min(y+range-(x-i),y+range+(x-i)); ++j) {
        try {
          if (city[j][i] == 'R' || city[j][i] == 'P') {
            city[j][i] = 'X';
          }
        } catch (Exception e) {}
      }
    }

  }
  
  public static int coverage(int x, int y) {
    int covered = 0;
    for (int i=x-range; i<=x+range; ++i) {
      for (int j=Math.max(y-range+(x-i),y-range-(x-i)); j<=Math.min(y+range-(x-i),y+range+(x-i)); ++j) {
        try {
          if (city[j][i] == 'R')
            covered++;
        } catch (Exception e) {}
      }
    }
    return covered;
  }

  public static void printCity() {
    for (int i=0; i<height; ++i) {
      for (int j=0; j<width; ++j) {
        System.out.print(city[i][j]);
      }
      System.out.println("");
    }
    System.out.println("--------------------");
  }
  
}

class Tuple3 {
  public int x, y, z;

  public Tuple3(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

}