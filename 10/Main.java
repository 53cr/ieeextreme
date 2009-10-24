import java.util.*;

public class Main
{
  private static void p(String s)
  {
    System.out.println(s);
  }

  
  private static class Word implements Comparable
  {
    public String word;
    public int val;

    public String toString()
    {
      String rtn = "";
      rtn += val;
      rtn += " ";
      rtn += word;
      return rtn;
    }
    
    public Word(String w, int v)
    {
      word = w;
      val = v;
    }

    //returns pt value of word created
    //-1 if cannot create
    public int[] canCreate(ArrayList<Character> alchars, HashMap<Character,Integer> pts)
    {
      //DEBUG
      // p("Charset passed to canCreate:");
      //for(int i = 0; i < alchars.size(); i++)
      //   System.out.print(alchars.get(i).charValue() + " ");
      //p("");

      char[] tiles = new char[alchars.size()];
      boolean[] mask = new boolean[tiles.length];
      int[] rtn = {0,0};
      for(int i = 0; i < alchars.size(); i++)
      {
        tiles[i] = alchars.get(i).charValue();
        //    System.out.println(tiles[i]);
      }

      //check my masking characters
      char[] wordchars = word.toCharArray();

      boolean breaker = false;
      boolean innerbreaker = false;

      //check for characters
      int i;
      for(i = 0; !breaker && i < wordchars.length; i++)
      {
        char chToCheck = wordchars[i];
        //System.out.println("Checking char: " + chToCheck);
        innerbreaker = false;
        for(int j = 0; !innerbreaker && j < tiles.length; j++)
        {
          //System.out.println("Comparing " + chToCheck + " with " + tiles[j]);
          //System.out.println("Is masked? " + mask[j]);
          if(tiles[j] == chToCheck && !mask[j])
          {
            //p("matches!");
            mask[j] = true;
            rtn[0] += pts.get(new Character(tiles[j])).intValue();
            innerbreaker = true;
          }
          else if(j == tiles.length-1)
          {
            breaker = true;
          }
        }
      }
      breaker = false;
      if(i != wordchars.length)
      {
        for(;!breaker && i < wordchars.length; i++)
        {
          innerbreaker = false;
          char chToCheck = wordchars[i];
          for(int j = 0; !innerbreaker && j < tiles.length; j++)
          {
            if(tiles[j] == 'J' && !mask[j])
            {
              mask[j] = true;
              rtn[1] = 1;
              innerbreaker = true;
            }
            else if (j == tiles.length-1)
            {
              breaker = true;
            }
          }
        }
      }

      if (i != wordchars.length)
      {
        rtn[0] = -1;
        rtn[1] =  0;
        return rtn;
      }
      else
        return rtn;
    }
    // this.compareTo(other) < 0 iff this < other

    //this < other => (other - this) > 0
    //this < other => (this - other) < 0
    public int compareTo(Object other)
    {
      int rtn = val - ((Word)other).val;
      if(rtn == 0)
      {
        return ((Word)other).word.compareTo(word);
      }
      return rtn;
    }
  }

  public static int score(String a, HashMap<Character,Integer> pts)
  {
//     p("Scoring " + a);
    int rtn = 0;

    
    for(int i = 0; i < a.length(); i++)
      rtn += pts.get(new Character(a.charAt(i))).intValue();

//     p("Score: " + rtn);
    return rtn;
  }

  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    String instr = "";
    HashMap<Character,Integer> pts = new HashMap<Character,Integer>();
    ArrayList<ArrayList<Character>> tiles =
      new ArrayList<ArrayList<Character>>();
    TreeSet<Word> dict = new TreeSet<Word>();

//     p("Created data structures");
    //Get characters
    in.nextLine(); //first line contains alphabet

    
    //get scores;
    instr = in.nextLine();
//     p("Scores: " + instr);
    String[] tmparr = instr.split("\\s+");

    for(int i = 0; i < 26; i++)
    {
      char tmp = (char) ('a' + i);
      pts.put(new Character(tmp), new Integer(tmparr[i]));
    }

    //Get tiles
    instr = in.nextLine();
//     p("Num tiles: " + instr);
    int numplayers = Integer.parseInt(instr);
    for(int i = 0; i < numplayers; i++)
    {
      instr = in.nextLine();
//       p("Tiles for player " + i + ": " + instr);
      char[] chars = instr.toCharArray();
      ArrayList<Character> listptr = new ArrayList<Character>();
      
      for(int j = 0; j < chars.length; j++)
      {
        listptr.add(new Character(chars[j]));
      }
      tiles.add(listptr);
    }

    //Get dictionary
    instr = in.nextLine();
//     p("Lines in dictionary: " + instr);
    int numwords = Integer.parseInt(instr);
    for(int i = 0; i < numwords; i++)
    {
      instr = in.nextLine();
//       p("Word to be inserted: " + instr);
      dict.add(new Word(instr, score(instr, pts)));
    }



    
    for(int i = 0; i < tiles.size(); i++)
    {
      boolean found = false;
      int highscore[] = new int[2];
      Word hiscorew;
      ArrayList<Character> listptr = tiles.get(i);

      //DEBUG
      //   p("Printing char set: " + i);
      //for(int z = 0; z < listptr.size(); z++)
      //{
      //  System.out.print(listptr.get(z).charValue() + " ");
      //}
      //p("");
      TreeSet<Word> answers = new TreeSet<Word>();

       //DEBUG
      // for(int z = 0; z < dict.size(); z++)
      //     p(dict.get(z).toString());


//       p("Checking dictionary");

      Iterator<Word> iter = dict.iterator();
//      for(int j = 0; !found && j < dict.size(); j++) 
      while(!found && iter.hasNext())
      {
        hiscorew = iter.next();

//        System.out.println("can we create " + hiscorew.word + "?");

        highscore = hiscorew.canCreate(listptr, pts);

        //DEBUGGER
        //       System.out.println(highscore[0]);
        //END DEBUGGER
        
        if(highscore[0] == -1)
        {}
        else if (highscore[1] == 0)
        {

          //  p("No blanks used");

          //System.out.println("adding " + hiscorew.word);
          found = true;
          answers.add(hiscorew);

          
        }
        else
        {
          //p("Blanks used");
          //System.out.println("adding " + hiscorew.word + " to list");
          //System.out.println("Recursing to next word");
          answers.add(new Word(hiscorew.word, highscore[0]));
        }
      }

      
      if (!answers.isEmpty())
      {

//         Iterator debugiter = answers.iterator();
//         while(debugiter.hasNext())
//           System.out.println("Debug: " + debugiter.next());
        

        hiscorew = answers.last();
        System.out.println(hiscorew);
      }
      else
        System.out.println("pass");
      
    }
  }
}



