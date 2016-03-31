/**
   A Sample is a list of characters pertaining to a corresponding list of
   years. Each year has a corresponding character to represent what info was
   taken.
*/

package se3860_secondgui;

/**
 
 @author mccolganb
 */
public class Sample 
{
   private String id;
   private char data[];
   
   /*
      Constructor for a Sample, the info[] represents the list of characters
      representing the info that was recorded. idName is the name of the
      given Sample.
   */
   Sample(char [] info, String idName )
   {
      data = new char[info.length];
      System.arraycopy(info, 0, data, 0, info.length);
      if (data[0] == '\0')
      {
         for (int i = 0; i < info.length; i++)
            data[i] = '.';
      }
      id = idName;
   }
   
   /*
      Returns the id for a given Sample.
   */
   public String GetId()
   {
      return id;
   }
   
   /*
      Sets the id of a cetain Sample.
   */
   public void EditId( String newId)
   {
      id = newId;
   } 
   
   /*
      Returns the char[] for a certain Sample.
   */
   public char [] getInfo()
   {
      return data;
   }
   
   /*
      Returns the length of the char[] for a certain Sample.
      This is the same as the number of years records were taken.
   */
   public int getSize()
   {
       return data.length;
   }
   
   /*
      Overriden toString() method to return a formatted id.
   */
   @Override
   public String toString()
   {
       return "Sample: " + id;
   }
   
   /*
      set the data to a given char[]
   */
   public void setData(char info[])
   {
      data = info;
   }
   
}
