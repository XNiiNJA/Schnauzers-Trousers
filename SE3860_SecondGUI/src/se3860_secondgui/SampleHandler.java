/**
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package se3860_secondgui;

import java.awt.List;
import java.util.ArrayList;

/**
 
 @author mccolganb
 */
public class SampleHandler 
{
   private final ArrayList<Sample> sampleList;
   private final int idLength;
   private final int numSamples;
   
   SampleHandler( int numOfSamples, int startYear, int lastYear, int idLen, 
         boolean newSet, char [][] infoSet)
   {
      idLength = idLen;
      numSamples = numOfSamples;
      sampleList = new ArrayList<Sample>();//new Sample[numOfSamples];
      char [] info;
      if(newSet)
      {
         info = new char[lastYear - startYear];
         for( int i = 0; i < numOfSamples; i++)
         {
            Sample s = new Sample(info, Integer.toString(i + 1));
            //sampleList[i] = s;
            sampleList.add(s);
         }
      }
      else
      {
         for( int i = 0; i < numOfSamples; i++)
         {
            Sample s = new Sample(infoSet[i], Integer.toString(i + 1));
            //sampleList[i] = s;
            sampleList.add(s);
         }
      }
   }
   
   private int Find( String idName )
   {
      for( int i = 0; i < numSamples; i++)
      {
         if(sampleList.get(i).GetId().equals(idName) )//sampleList[i].GetId().equals(idName))
            return i;
      }
      return -1;
   }
   
   public int changeId(String newID, String oldID)
   {
        if(Find(newID) == -1)
        {
            if(oldID != null)
            {
                int index = Find(oldID);
                //sampleList[index].EditId(newID);
                sampleList.get(index).EditId(newID);
                return 1;
            }
            return 0; 
            
        }
        return -1;
   }
   
   public void changeSampleNames(char[][] sampleIDNames)
   {
      char[] sampleID = new char[idLength];
      for (int i = 0; i < numSamples; i++)
      {
         for (int j = 0; j < idLength; j++)
         {
            sampleID[j] = sampleIDNames[j][i];
         }
         //sampleList[i].EditId(new String(sampleID));
         sampleList.get(i).EditId(new String(sampleID));
      }
   }
   
   public String [] refreshIdnames()
   {
       String [] names =  new String[numSamples];
           for( int i = 0; i < numSamples; i++)
      {
         //names[i] = sampleList[i].GetId();
          names[i] = sampleList.get(i).GetId();
      }
      return names;   
   }
   
   public char[] getSampleInfo(String Id)
   {
      int index = Find(Id);
      if(index != -1)
         //return sampleList[index].getInfo();
          return sampleList.get(index).getInfo();
      return null;
   }

   public Sample getSample(int i)
   {
       return sampleList.get(i);
   }
   
   public Sample getSampleById(String id)
   {
        for(int i = 0; i < sampleList.size(); i++)
            if(sampleList.get(i).GetId().equals(id))
                return sampleList.get(i);
       
        return null;
   }

}
