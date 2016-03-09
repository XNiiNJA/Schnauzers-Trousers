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
   private static final int NUMBEROFLINESSITEINFO = 30;
   private static final int MAXCOMMENTLENGTH = 50;
   
   private int startYear = 0;
   private int endYear = 0;
   private int sampleIDLength = 0;
   
   private static char sampleIDInfo[][];
   private static char collectionData[][];
   private static String siteInfo[];
   private static String comments[];
   
   private FileReader fr;
   private BufferedReader br;
   
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
         PrintComments();
         prntW.println();
         prntW.println(FHX2FORMAT);
         prntW.println(startYear + " " + numberOfSamples 
               + " " + sampleIDLength);
         printSampleIDs();
         prntW.println();
         prntW.flush();
         printCollectedData();
         prntW.close();
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
      counter++;
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
      for (int i = 0; i <= ((endYear - startYear)); i++)
      {
         for (int j = 0; j < numberOfSamples; j++)
         {
            prntW.print(collectionData[i][j]);
         }
         prntW.println(" " + counterYear++);
         prntW.flush();
      }
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
      prntW.println("State          : " + state);
      prntW.println("County         : " + county);
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
   
   private void ReadFileSiteInformation(BufferedReader textReader) throws Exception
   {
      siteInfo = new String[NUMBEROFLINESSITEINFO];
      for( int i = 0; i < NUMBEROFLINESSITEINFO; i++)
      {
         String wholeString = textReader.readLine();
         String[] parts = wholeString.split(": ");
         String info;
         if( parts.length > 1)
         {
            info = parts[1];
            if( i != 5)
               siteInfo[i] = info;
            else
            {
               numberOfSamples = Integer.parseInt(info);
            }
         }
         else
            siteInfo[i] = "";
      }
   }
   
   private void ReadFromFHX2(String FilePath, BufferedReader textReader, FileReader fileRead) throws Exception
   {
      textReader.close();
      fileRead.close();
      fr = new FileReader(FilePath);
      br = new BufferedReader(fr);
      String testString = br.readLine();
      while (!testString.equalsIgnoreCase(FHX2FORMAT))
      {
         testString = br.readLine();
      }
      ReadSampleInformation(br);
      setSiteInformation(siteInfo);
      ReadSampleIDs(br);
      br.readLine();
      ReadCollectionData(br);
      br.close();
      fr.close();
   }
   
   private void ReadSampleInformation(BufferedReader textReader) throws Exception
   {
      String multiValue = textReader.readLine();
      String []parts = multiValue.split(" ");
      startYear = Integer.parseInt(parts[0]);
      numberOfSamples = Integer.parseInt(parts[1]);
      sampleIDLength = Integer.parseInt(parts[2]);
   }
   
   private void ReadSampleIDs(BufferedReader textReader) throws Exception
   {
      sampleIDInfo = new char[sampleIDLength][numberOfSamples];
      for (int i = 0; i < sampleIDLength; i++)
      {
         String charValue = textReader.readLine();
         for (int j = 0; j < numberOfSamples; j++)
         {
            sampleIDInfo[i][j] = charValue.charAt(j);
         }
      }
   }
   
   private void ReadCollectionData(BufferedReader textReader) throws Exception
   {
      collectionData = new char[(endYear - startYear) + 1][numberOfSamples];
      for (int i = 0; i <= (endYear - startYear); i++)
      {
         String charValue = textReader.readLine();
         for (int j = 0; j < numberOfSamples; j++)
         {
            collectionData[i][j] = charValue.charAt(j);
         }
      }
   }
   
   private void FindEndYear(BufferedReader textReader) throws Exception
   {
      String lineRead = textReader.readLine();
      String testString = textReader.readLine();
      while (testString != null)
      {
         lineRead = testString;
         testString = textReader.readLine();
      }
      String parts[] = lineRead.split(" ");
      endYear = Integer.parseInt(parts[1]);
   }
   
   private void ReadComments(BufferedReader textReader) throws Exception
   {
      int counter = 0;
      comments = new String[MAXCOMMENTLENGTH];
      String testString = textReader.readLine();
      comments[counter++] = testString;
      while (!testString.equalsIgnoreCase("End Comments   :"))
      {
         testString = textReader.readLine();
         comments[counter++] = testString;
      }
      comments[counter] = "End Comments   :";
   }
   
   private void PrintComments()
   {
      int counter = 0;
      String testString = comments[counter];
      while (!testString.equalsIgnoreCase("End Comments   :"))
      {
         prntW.println(comments[counter++]);
         testString = comments[counter];
      }
      prntW.println(comments[counter]);
   }
   
   public void readFromFile(String filePath)
   {
      try
      {
         FileReader fr = new FileReader(filePath);
         BufferedReader br = new BufferedReader(fr);
         ReadFileSiteInformation(br);
         //createSiteInfoArray();
         ReadComments(br);
         FindEndYear(br);
         ReadFromFHX2(filePath, br, fr);
      }
      catch (Exception e)
      {
         System.out.println("Error: " + e);
      }
   }
   
   public static void main(String[] args)
   {
      DataHelper d = new DataHelper();
      d.readFromFile("C:\\Users\\Nathan\\Documents\\SE\\SE3860\\Schnauzers-Trousers\\trunk\\SE3860_SecondGUI\\uslcf001.fhx");
      d.printFile(1150, 2001, 89, 5, "Newfile.fhx", siteInfo, sampleIDInfo, collectionData);
      
   }
}
