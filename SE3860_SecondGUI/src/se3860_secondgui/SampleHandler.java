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
   private Sample sampleList[];
   private int idLength;
   private int numSamples;
   
   SampleHandler( int numOfSamples, int startYear, int lastYear, int idLen )
   {
      idLength = idLen;
      numSamples = numOfSamples;
      sampleList = new Sample[numOfSamples];
      for( int i = 0; i < numOfSamples; i++)
      {
         Sample s = new Sample(startYear, lastYear, i + 1);
      }
   }
   
   private int Find( String idName )
   {
      for( int i = 0; i < numSamples; i++)
      {
         if( sampleList[i].GetId() == idName  )
            return i;
      }
      return -1;
   }
   
}
