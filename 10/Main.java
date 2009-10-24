import java.util.*;

public class Main
{

  private static class Word implements Comparable
  {
    public String word;
    public int val;

    public Word(String w, int v)
    {
      word = w;
      val = v;
    }

    //returns pt value of word created
    //-1 if cannot create
    public int[] canCreate(ArrayList<Character> alchars, HashMap<Character,Integer> pts)
    {
      char[] tiles = new char[alchars.length()];
      boolean[] mask = new boolean[chars.length()];
      int[] rtn = {0,0};
      for(int i = 0; i < alchars.length(); i++)
      {
        tiles[i] = alchars.get(i).charValue();
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
        innerbreaker = false;
        for(int j = 0; !innerbreaker && j < tiles.length; j++)
        {
          if(tiles[j] == chToCheck && !mask[j])
          {
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
            if(tiles[J] == 'J' && !mask[j])
            {
              mask[j] = true;
              rtn[1] = 1
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
        rtn = {-1,0};
        return rtn;
      }
      else
        return rtn;
    }

    //check if we can use blanks
    for(int i = 0; i < wordchars.length 
          }
  }
  // this.compareTo(other) < 0 iff this < other

  //this < other => (other - this) > 0
  //this < other => (this - other) < 0
  public int compareTo(Object other)
  {
    return val - ((Word)other).val;
  }
}

  public static int score(String a, HashMap<Character,Intetger> pts)
  {
    int rtn = 0;

    for(int i = 0; i < a.length(); i++)
      rtn += pts.get(new Character(a.charAt(i))).intValue();

    return rtn;
  }

  public static void main(String args[])
  {
    Scanner in = new Scanner(System.in);
    String instr = "";
    HashMap<Character,Integer> pts = new HashMap<Character,Integer>();
    ArrayList<ArrayList<Character>>> tiles =
      new ArrayList<ArrayList<Character>>();
    TreeSet<Word> dict = new TreeSet<Word>();
    
    //Get characters
    in.nextLine(); //first line contains alphabet

    //get scores;
    instr = in.nextLine();
    String[] tmparr = instr.split("\\s+");

    for(int i = 0; i < tmparr.length; i++)
    {
      pts.put(new Character('a'+i), new Integer(tmparr[i]));
    }

    //Get tiles
    instr = in.nextLine();
    int numplayers = Integer.parseInt(instr);
    for(int i = 0; i < numplayers; i++)
    {
      instr = in.nextLine();
      char[] chars = instr.toCharArray();

      ArrayList listptr = tiles.get(i);
      listptr = new ArrayList<Character>();
      
      for(int j = 0; j < chars.length; j++)
      {
        listptr.add(new Character(chars[j]));
      }
    }

    //Get dictionary
    instr = in.nextLine();
    int numwords = Integer.parseInt(instr);
    for(int i = 0; i < numwords; i++)
    {
      instr = in.nextLine();
      dict.add(new Word(instr, score(instr, pts)));
    }


    
    for(int i = 0; i < tiles.size(); i++)
    {
      boolean found = false;
      int highscore[2];
      Word hiscorew;
      ArrayList listptr = tiles.get(i);
      TreeSet<Word> answers = new TreeSet<Word>();

      
      for(int j = 0; !found && j < listptr.size(); j++) )
      {
        hiscorew = listptr.get(j);
        highscore = hiscorew.canCreate(listptr, pts);
        if (highscore[1] == 0)
        {
          found = true;
          answers.add(hiscorew);
        }
        else
        {
          answers.add(new Word(hiscorew.word, highscore[0]));
        }
      }

      
      if (found)
      {
        hiscorew = answers.last();
        System.out.println(hiscorew.val + " " hiscorew.word);
      }
      else
        System.out.println("pass");
      
    }
  }
}



