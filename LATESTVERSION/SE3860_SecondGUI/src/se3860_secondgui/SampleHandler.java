/**
   The SampleHandler is used to do operations on all of the Samples.
*/

package se3860_secondgui;

import java.util.ArrayList;

/**
 
 @author mccolganb
 */
public class SampleHandler 
{
   private final ArrayList<Sample> sampleList;
   private final int idLength;
   private final int numSamples;
   
   /*
      Constructor for a SampleHandler. Takes in the number of Samples, the
      start year and end year of the recording, the length of the Sample id's,
      a boolean to see if it is a new set or not, and the char[][] which holds
      all of the recorded information (data for each Sample for each year).
   */
   SampleHandler( int numOfSamples, int startYear, int lastYear, int idLen, boolean newSet, char [][] infoSet)
   {
      idLength = idLen;
      numSamples = numOfSamples;
      sampleList = new ArrayList<>();
      char [] info;
      if(newSet)
      {
         info = new char[lastYear - startYear + 1];
         for( int i = 0; i < numOfSamples; i++)
         {
            Sample s = new Sample(info, Integer.toString(i + 1));
            sampleList.add(s);
         }
      }
      else
      {
         for( int i = 0; i < numOfSamples; i++)
         {
            Sample s = new Sample(infoSet[i], Integer.toString(i + 1));
            sampleList.add(s);
         }
      }
   }
   
   /*
      Given a Sample id, checks the sampleList indices for the id. Returns
      that specific index or return -1 if not found.
   */
   private int Find( String idName )
   {
      for( int i = 0; i < numSamples; i++)
      {
         if(sampleList.get(i).GetId().equals(idName) )//sampleList[i].GetId().equals(idName))
            return i;
      }
      return -1;
   }
   
   /*
      Given a specific id to find, change that id to a new name. Check if the
      new id is found in the sampleList, if not found then checks if an old
      id was passed, if one was then find the index of the oldId and edit it
      with the new id.
      return 1 if able to edit
      return 0 if no oldID was passed
      return -1 if newID was already in use
   */
   public int changeId(String newID, String oldID)
   {
        if(Find(newID) == -1) 
        {
            if(oldID != null) 
            {
                int index = Find(oldID);
                sampleList.get(index).EditId(newID);
                return 1;
            }
            return 0;         
        }
        return -1;
   }
   
   /*
      Changes Sample names from the default (0-SampleListSize) to their
      actual names.
   */
   public void changeSampleNames(char[][] sampleIDNames)
   {
      char[] sampleID = new char[idLength];
      for (int i = 0; i < numSamples; i++)
      {
         for (int j = 0; j < idLength; j++)
         {
            sampleID[j] = sampleIDNames[j][i];
         }
         sampleList.get(i).EditId(new String(sampleID));
      }
   }
   
   /*
      Returns a String[] with all of the current names in sampleList.
   */
   public String [] refreshIdnames()
   {
      String [] names =  new String[numSamples];
      for( int i = 0; i < numSamples; i++)
      {
         names[i] = sampleList.get(i).GetId();
      }
      return names;   
   }
   
   /*
      Checks if the given id is in the sampleList, if so then returns
      the char[] for the Sample at the index of the given id.
   */
   public char[] getSampleInfo(String Id)
   {
      int index = Find(Id);
      if(index != -1)
      {
         return sampleList.get(index).getInfo();
      }
      return null;
   }

   /*
      Returns the sample at a given index of sampleList.
   */
   public Sample getSample(int i)
   {
       return sampleList.get(i);
   }
   
   /*
      Iterates through the sampleList and checks each index's Sample to see
      if the Sample's id is equal to the given id.
   */
   public Sample getSampleById(String id)
   {
        for(int i = 0; i < sampleList.size(); i++)
        {
            if(sampleList.get(i).GetId().equals(id))
            {
                return sampleList.get(i);
            }
        }
        return null;
   }
   
   /*
      changes the sample data to the new given data at a given index
   */
   public void changeSampleData( char data[], int index )
   {
      sampleList.get(index).setData(data);
   }
   
   /*
      returns the 2d char [] of sample IDs
   */
   public char[][] getArrayOfSampleIDs()
   {
      char[][] returnArray = new char[idLength][numSamples];
      for (int i = 0; i < numSamples; i++)
      {
         String s = this.sampleList.get(i).GetId();
         while(s.length() < idLength)
            s += ' ';
         for (int j = 0; j < idLength; j++)
         {
               returnArray[j][i] = s.charAt(j);
         }
      }
      return returnArray;
   }
}
