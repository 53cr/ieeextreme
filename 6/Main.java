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

    for (int i=0; i<nc; ++i) {
      int winner = findTop(bleh);
      System.out.println(contestants[winner]);
      bleh[winner] = false;
    }

    
  }

  public static int findTop(Boolean candidates[]) {
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

    int minVotes = 10000;
    // if Absolute Majority...
    for (int i=0; i<nc; ++i) {
      if (currentVotes[i] < minVotes && currentVotes[i] != 0) {
        minVotes = currentVotes[i];
      }
      
      if (currentVotes[i] > nv/2.0) { //absolute majority. this guy wins. figure out the rest...
        // return Winner
        return i;
      } 
    }

    //else

    //find all lowest scores.
    Boolean rejectionCandidates[] = new Boolean[candidates.length];
    for (int i=0; i<nc ; ++i) {
      if (currentVotes[i] == minVotes) {
        rejectionCandidates[i] = true;
      } else {
        rejectionCandidates[i] = false;
      }
    }

    //removal = ChooseRemoval (all lowest scores > 0)
    int removal = chooseRemoval(rejectionCandidates);

    Boolean newCandidates[] = candidates.clone();

    newCandidates[removal] = false;

    return findTop(newCandidates);      

  }


  public static int chooseRemoval(Boolean candidates[]) {
    int number = 0;
    int last = 0;
    for (int i=0; i<candidates.length; ++i) {
      if (candidates[i]) {
        number++;
        last = i;
      }
    }
    if (number == 1) {
      return last;
    } else {

      int currentVotes[] = new int[nc];
      for (int i=0; i<nc; ++i) {
        currentVotes[i] = 100000;
      }
      
      // Figure out how each voter is voting
      for (int voter=0; voter<nv; ++voter) {
        for (int choice=0; choice<nc; ++choice) {
          if (candidates[votes[voter][choice]]) {
            if (currentVotes[votes[voter][choice]] == 100000) {
              currentVotes[votes[voter][choice]] = 0;
            }
            currentVotes[votes[voter][choice]]++;
            break;
          }
        }
      }

      int minVotes = 10000;
      int count = 0;
      last = 0;

      for (int i=0; i<nc; ++i) {
        if (currentVotes[i] < minVotes) {
          count = 1;
          last = i;
          minVotes = currentVotes[i];
        } else if (currentVotes[i] == minVotes) {
          count++;
        }
      }
      if (count == 1) {
        return last;
      } else {
        
        //return president's last choice.
        
        int maxpos = -1;
        int lastChoice = 0;
        for (int j=nc-1; j>=0; --j) {
          if (candidates[j] && currentVotes[j] == minVotes) {
            return j;
          }
        }
        
      }
    }
    
    return -1;
    
  }
  

}
