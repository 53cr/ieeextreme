import java.util.Scanner;
import java.util.LinkedList;

public class Main
{
  public static int[][] votes;
  public static String[] contestants;
  public static int[] eliminated;
  public static int nc;
  public static int nv;
  
  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    nv = in.nextInt();
    nc = in.nextInt();
    String cont;
    in.nextLine();

    contestants = new String[nc];
    eliminated = new int[nc];
    
    for (int i=0; i<nc; ++i) {
      contestants[i] = in.next();
    }

    in.nextLine();

    votes = new int[nv][nc];
    
    for (int voter=0; voter<nv; ++voter) {
      for (int i=0; i<nc; ++i) {
        cont = in.next();
        for (int j=0; j<nc; ++j) {
          if (cont.equals(contestants[j])) {
            votes[voter][i] = j;
            break;
          }
        }
      }
    }

    Boolean[] bleh = new Boolean[nc];
    for (int i=0; i<nc; ++i) bleh[i] = true;
    int winner = election(bleh);

    System.out.println(winner);
    
    
  }

  public static int election(Boolean candidates[]) {
    int currentVotes[] = new int[nc];
    Boolean[] nextCandidates;
    
    // Figure out how each voter is voting
    for (int voter=0; voter<nv; ++voter) {
      for (int choice=0; choice<nc; ++choice) {
        if (candidates[votes[voter][choice]]) {
          currentVotes[votes[voter][choice]]++;
          break;
        }
      }
    }

    
    int minVotes=100000;
    for (int i=0; i<nc; ++i) {
      if (currentVotes[i] < minVotes && currentVotes[i] != 0) {
        minVotes = currentVotes[i];
      }
      if (currentVotes[i] > nv/2.0) { //absolute majority. this guy wins. figure out the rest...
        return i;
      }
    }

    nextCandidates = candidates.clone();
    int minCount = 0;
    int loser = 0;
    for (int i=0; i<nc; ++i) {
      if (currentVotes[i] == minVotes) {
        minCount++;
        loser = i;
        nextCandidates[i] = true;
      } else nextCandidates[i] = false;
    }

    if (minCount > 1) {
      loser = election(nextCandidates).getLast();
    }
    
    nextCandidates = candidates.clone();

    nextCandidates[loser] = false;
    return election(nextCandidates);

  }
  
}
