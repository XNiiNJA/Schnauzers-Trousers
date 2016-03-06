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

import java.io.*;

public class DataHelper
{
   private String nameOfSite;
   private String siteCode;
   private String collectionDate;
   private String collectors;
   private String crossdaters;
   private int numberOfSamples = 0;
   private String speciesName;
   private String commonName;
   private String habitatType;
   private String country;
   private String state;
   private String county;
   private String parkMonument;
   private String nationalForest;
   private String rangerDistrict;
   private String township;
   private String range;
   private String section;
   private String quarterSection;
   private String utmEasting;
   private String utmNorthing;
   private String latitude;
   private String longitude;
   private String topographicMap;
   private String lowestElev;
   private String highestElev;
   private String slope;
   private String aspect;
   private String areaSampled;
   private String substrateType;
   private String outputFileName;
   
   private PrintWriter prntW;
   
   private static final String FHX2FORMAT = "FHX2 FORMAT";
   
   
   private int startYear = 0;
   private int endYear = 0;
   private int sampleIDLength = 0;
   
   private char sampleIDInfo[][];
   private char collectionData[][];
   private String siteInfo[];
   
   /*
   sets necessary site/year info, along with printing out all of
   the site information and sample information in the FHX2 format 
   to the specified file.
   */
   public void printFile(int startYear, int endYear, int numberOfSamp, 
         int sampleIDLengths, String fileName, String siteInfo[], 
         char sampleIDInfoGui[][], char collectionDataInfo[][])
   {
      setOutputFileName(fileName);
      setSiteInformation(siteInfo);
      setStartYear(startYear);
      setEndYear(endYear);
      setNumberOfSamples(numberOfSamp);
      setSampleIDLength(sampleIDLengths);
      sampleIDInfo = sampleIDInfoGui;
      collectionData = collectionDataInfo;
      try
      {
         prntW = new PrintWriter(outputFileName);
         printSiteData();
         prntW.println("Begin comments : ");
         prntW.println("End comments   : ");
         prntW.println();
         prntW.println(FHX2FORMAT);
         prntW.println(startYear + " " + numberOfSamples 
               + " " + sampleIDLength);
         printSampleIDs();
         prntW.println();
         printCollectedData();
      }
      catch (Exception e)
      {
         System.out.println("Error: " + e);
      }
   }
   
   /*
   sets all of the site information from an array of strings
   */
   private void setSiteInformation( String[] siteInfo )
   {
      int counter = 0;
      nameOfSite = siteInfo[counter++];
      siteCode = siteInfo[counter++];
      collectionDate = siteInfo[counter++];
      collectors = siteInfo[counter++];
      crossdaters = siteInfo[counter++];
      speciesName = siteInfo[counter++];
      commonName = siteInfo[counter++];
      habitatType = siteInfo[counter++];
      country = siteInfo[counter++];
      state = siteInfo[counter++];
      county = siteInfo[counter++];
      parkMonument = siteInfo[counter++];
      nationalForest = siteInfo[counter++];
      rangerDistrict = siteInfo[counter++];
      township = siteInfo[counter++];
      range = siteInfo[counter++];
      section = siteInfo[counter++];
      quarterSection = siteInfo[counter++];
      utmEasting = siteInfo[counter++];
      utmNorthing = siteInfo[counter++];
      latitude = siteInfo[counter++];
      longitude = siteInfo[counter++];
      topographicMap = siteInfo[counter++];
      lowestElev = siteInfo[counter++];
      highestElev = siteInfo[counter++];
      slope = siteInfo[counter++];
      aspect = siteInfo[counter++];
      areaSampled = siteInfo[counter++];
      substrateType = siteInfo[counter++];
   }
   
   private void createSiteInfoArray()
   {
      int counter = 0;
      siteInfo[counter++] = nameOfSite;
      siteInfo[counter++] = siteCode;
      siteInfo[counter++] = collectionDate;
      siteInfo[counter++] = collectors;
      siteInfo[counter++] = crossdaters;
      siteInfo[counter++] = speciesName;
      siteInfo[counter++] = commonName;
      siteInfo[counter++] = habitatType;
      siteInfo[counter++] = country;
      siteInfo[counter++] = state;
      siteInfo[counter++] = county;
      siteInfo[counter++] = parkMonument;
      siteInfo[counter++] = nationalForest;
      siteInfo[counter++] = rangerDistrict;
      siteInfo[counter++] = township;
      siteInfo[counter++] = range;
      siteInfo[counter++] = section;
      siteInfo[counter++] = quarterSection;
      siteInfo[counter++] = utmEasting;
      siteInfo[counter++] = utmNorthing;
      siteInfo[counter++] = latitude;
      siteInfo[counter++] = longitude;
      siteInfo[counter++] = topographicMap;
      siteInfo[counter++] = lowestElev;
      siteInfo[counter++] = highestElev;
      siteInfo[counter++] = slope;
      siteInfo[counter++] = aspect;
      siteInfo[counter++] = areaSampled;
      siteInfo[counter++] = substrateType;
   }
   
   public String[] getSiteInfo()
   {
      return siteInfo;
   }
   
   /*
   sets the number of samples
   */
   private void setNumberOfSamples( int numOfSamples )
   {
      numberOfSamples = numOfSamples;
   }
   
   /*
   sets the start year
   */
   private void setStartYear( int startYearGui )
   {
      startYear = startYearGui;
   }
   
   /*
   sets the end year
   */
   private void setEndYear ( int endYearGui )
   {
      endYear = endYearGui;
   }
   
   /*
   sets the sample's length
   */
   private void setSampleIDLength ( int sampleIDLengthGui )
   {
      sampleIDLength = sampleIDLengthGui;
   }
   
   /*
   prints ot file all of the sample IDs in FHX2 format
   */
   private void printSampleIDs()
   {
      for (int i = 0; i < sampleIDLength; i++)
      {
         for (int j = 0; j < numberOfSamples; j++)
         {
            prntW.print(sampleIDInfo[i][j]);
         }
         prntW.println();
      }
   }
   
   /*
   sets the output file name
   */
   private void setOutputFileName( String fileName )
   {
      outputFileName = fileName;
   }
   
   private void printCollectedData()
   {
      int counterYear = startYear;
      for (int i = 0; i < (endYear - startYear); i++)
         for (int j = 0; j < numberOfSamples; j++)
         {
            prntW.print(collectionData[i][j]);
         }
      prntW.println(" " + counterYear++);
   }
   
   private void printSiteData()
   {
      prntW.println("Name of site   : " + nameOfSite);
      prntW.println("Site Code      : " + siteCode);
      prntW.println("Collection date: " + collectionDate);
      prntW.println("Collectors     : " + collectors);
      prntW.println("Crossdaters    : " + crossdaters);
      prntW.println("Number samples : " + numberOfSamples);
      prntW.println("Species name   : " + speciesName);
      prntW.println("Common name    : " + commonName);
      prntW.println("Habitat type   : " + habitatType);
      prntW.println("Country        : " + country);
      prntW.println("Park/Monument  : " + parkMonument);
      prntW.println("National Forest: " + nationalForest);
      prntW.println("Ranger district: " + rangerDistrict);
      prntW.println("Township       : " + township);
      prntW.println("Range          : " + range);
      prntW.println("Section        : " + section);
      prntW.println("Quarter section: " + quarterSection);
      prntW.println("UTM easting    : " + utmEasting);
      prntW.println("UTM northing   : " + utmNorthing);
      prntW.println("Latitude       : " + latitude);
      prntW.println("Longitude      : " + longitude);
      prntW.println("Topographic map: " + topographicMap);
      prntW.println("Lowest elev    : " + lowestElev);
      prntW.println("Highest elev   : " + highestElev);
      prntW.println("Slope          : " + slope);
      prntW.println("Aspect         : " + aspect);
      prntW.println("Area sampled   : " + areaSampled);
      prntW.println("Substrate type : " + substrateType);
   }
}
