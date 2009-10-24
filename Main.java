import java.util.Scanner;

public class Main
{
  public static void main(String args[])
  {
    int i,j;
    Scanner in = new Scanner(System.in);
    int floors = in.nextInt();
    String data;

    String floorData[] = new String[600];

    System.out.println(floors);
    
    for (i=0; i<floors; ++i) {
      floorData = new String[600];
      
      for (j=0;data = in.nextLine(); data != ""; ++j) {
        floorData[j] = data;
      }

      System.out.println(floorData.length);
      
      // End of floor.
    }
    
  }
}
