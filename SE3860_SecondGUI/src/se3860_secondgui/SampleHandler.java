/**
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package se3860_secondgui;

import java.awt.List;

/**
 
 @author mccolganb
 */
public class SampleHandler 
{
   private final Sample sampleList[];
   private final int idLength;
   private final int numSamples;
   
   SampleHandler( int numOfSamples, int startYear, int lastYear, int idLen )
   {
      idLength = idLen;
      numSamples = numOfSamples;
      sampleList = new Sample[numOfSamples];
      for( int i = 0; i < numOfSamples; i++)
      {
         Sample s = new Sample(startYear, lastYear, i + 1);
         sampleList[i] = s;
      }
   }
   
   private int Find( String idName )
   {
      for( int i = 0; i < numSamples; i++)
      {
         if( sampleList[i].GetId().equals(idName))
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
                sampleList[index].EditId(newID);
                return 1;
            }
            return 0; 
            
        }
        return -1;
   }
   
   public String [] refreshIdnames()
   {
       String [] names =  new String[numSamples];
           for( int i = 0; i < numSamples; i++)
      {
         names[i] = sampleList[i].GetId();
      }
      return names;
       
       
   }
}
