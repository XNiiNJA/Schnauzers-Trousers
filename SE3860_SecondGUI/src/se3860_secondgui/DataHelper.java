/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se3860_secondgui;

/**
 *
 * @author Nathan
 */
public class DataHelper
{
   private String nameOfSite;
   private String siteCode;
   private String collectionDate;
   private String collectors;
   private String crossdaters;
   private int numberOfSamples;
   private String speciesName;
   private String commonName;
   private String habitatType;
   private String country;
   private String state;
   private String county;
   private String parkMonument;
   private String nationalForest;
   private String rangerDistrict;
   private String townShip;
   private String range;
   private String section;
   private String quarterSection;
   private String utmEasting;
   private String utmNorthing;
   private String latitude;
   private String longtitue;
   private String topographicMap;
   private String lowestElev;
   private String highestElev;
   private String slope;
   private String aspect;
   private String areaSampled;
   private String substrateType;
   
   private static final String FHX2FORMAT = "FHX2 FORMAT";
   private static final int yearLength = 4;
   
   private int startDate;
   private int endDate;
   private int sampleIDLength;
   private char[][]arrayOfIDs;
   private char[][]arrayOfData;
   
   public void PopulateSampleIDArray(char[][] arrayOfIDsGui)
   {
      arrayOfIDs = new char[sampleIDLength][numberOfSamples];
      for (int i = 0; i < numberOfSamples; i++)
         for (int j = 0; j < sampleIDLength; j++)
         {
            arrayOfIDs[i][j] = arrayOfIDsGui[i][j];
         }
   }
   
   public void PopulateDataArray(char[][] arrayOfDataGui)
   {
      int yearIncrement = startDate;
      int yearCounter = 0;
      arrayOfDataGui = new char[endDate - startDate][numberOfSamples + yearLength];
      for (int i = 0; i < (endDate - startDate); i++)
      {
         for (int j = 0; j < (numberOfSamples); j++)
         {
            arrayOfData[i][j] = arrayOfDataGui[i][j];
         }
         for (int k = numberOfSamples; k < yearLength; k++)
         {
            arrayOfData[i][k] = //yearIncrement.charAt(yearCounter);
            //yearCounter++;        
         }
         yearIncrement++;
         yearCounter = 0;
      }
   }
   
}
