/**
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package se3860_secondgui;

/**
 
 @author mccolganb
 */
public class Sample 
{
   private final int NUM_COLUMNS = 2;
   private String id;
   private char data[][];
   
   
   
   Sample( int startYear, int lastYear, int idName )
   {
      int count = startYear;
      data = new char[lastYear - startYear][NUM_COLUMNS];
      for( int i = 0; i < lastYear - startYear; i++)
      {
         data[i][1] = (char)count;
         count++;
      }
      id = new Integer(idName).toString();
   }
   
   public String GetId()
   {
      return id;
   }
   
   public void EditId( String newId)
   {
      id = newId;
   }
}
