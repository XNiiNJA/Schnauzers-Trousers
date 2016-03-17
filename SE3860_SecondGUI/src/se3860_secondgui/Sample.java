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
   private String id;
   private char data[];
   
   
   
   Sample(char [] info, String idName )
   {
      data = new char[info.length];
      for(int i = 0; i < info.length; i ++)
      {
         data[i] = info[i];
      }
      id = idName;
   }
   
   public String GetId()
   {
      return id;
   }
   
   public void EditId( String newId)
   {
      id = newId;
   } 
   
   public char [] getInfo()
   {
      return data;
   }
   
   public int getSize()
   {
       return data.length;
   }
   
   @Override
   public String toString()
   {
       return "Sample: " + id;
   }
   
}
