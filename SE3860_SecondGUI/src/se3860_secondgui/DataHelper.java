/**
   Handles all operations done on tree sample data, including reading and
   writing to files.
 */
package se3860_secondgui;

/**
 *
 * @author Nathan
 */

import java.io.*;

public class DataHelper
{
   private String nameOfSite = "";
   private String siteCode = "";
   private String collectionDate = "";
   private String collectors = "";
   private String crossdaters = "";
   private int numberOfSamples = 0;
   private String speciesName = "";
   private String commonName = "";
   private String habitatType = "";
   private String country = "";
   private String state = "";
   private String county = "";
   private String parkMonument = "";
   private String nationalForest = "";
   private String rangerDistrict = "";
   private String township = "";
   private String range = "";
   private String section = "";
   private String quarterSection = "";
   private String utmEasting = "";
   private String utmNorthing = "";
   private String latitude = "";
   private String longitude = "";
   private String topographicMap = "";
   private String lowestElev = "";
   private String highestElev = "";
   private String slope = "";
   private String aspect = "";
   private String areaSampled = "";
   private String substrateType = "";
   private String outputFileName = "";
   private String inputFileName = "";
   
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
   
   public SampleHandler sHandle;
   
   public DataLocations dataLocs = new DataLocations(); 
   
   /**
    * Uses the file's current data to print to the file specified
    */
   public void printFile()
   {
      try
      {
         prntW = new PrintWriter(outputFileName);
         printSiteData();
         PrintComments();
         prntW.println();
         prntW.println(FHX2FORMAT);
         prntW.println(startYear + " " + numberOfSamples 
               + " " + sampleIDLength);
         sampleIDInfo = this.sHandle.getArrayOfSampleIDs();
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
   
   /**
      sets the outFileName to a given string
    * @param fileName
   */
   public void setOutputFileName (String fileName)
   {
      outputFileName = fileName;
   }
   
   /**
   sets necessary site/year info, along with printing out all of
   the site information and sample information in the FHX2 format 
   to the specified file.
    * @param startYear
    * @param endYear
    * @param numberOfSamp
    * @param sampleIDLengths
    * @param fileName
    * @param siteInfo
    * @param sampleIDInfoGui
    * @param collectionDataInfo
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
         sampleIDInfo = this.sHandle.getArrayOfSampleIDs();
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
   
   /**
   returns the current collection data as a 2d char array
   @return 
   */
   public char[][] getInfo()
   {
      return collectionData;
   }
   
   /**
   sets all of the site information from an array of strings
    * @param siteInfo
   */
   public void setSiteInformation( String[] siteInfo )
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
   
   /**
      return the number of samples for a given dataset
   @return 
   */
   public int getNumberOfSamples()
   {
       return numberOfSamples;
   }
   
   /**
      returns the current start year for a data set
   @return 
   */
   public int getStartYear()
   {
       return startYear;
   }
   
   /**
      returns the current end year of a data set
   @return 
   */
   public int getEndYear()
   {
       return endYear;
   }
   
   /**
      returns the string array of the current site information.
   @return 
   */
   public String[] getSiteInfo()
   {
      return siteInfo;
   }
   
   /**
      returns the length of the current sample size
   @return 
   */
   public int getSampleIDLength()
   {
       return sampleIDLength;
   }
   
   /**
      sets the number of samples
    * @param numOfSamples
   */
   public void setNumberOfSamples( int numOfSamples )
   {
      numberOfSamples = numOfSamples;
   }
   
   /**
   sets the start year
    * @param startYearGui
   */
   public void setStartYear( int startYearGui )
   {
      startYear = startYearGui;
   }
   
   /**
      sets the end year
    * @param endYearGui
   */
   public void setEndYear ( int endYearGui )
   {
      endYear = endYearGui;
   }
   
   /**
      sets the sample's length
    * @param sampleIDLengthGui
   */
   public void setSampleIDLength ( int sampleIDLengthGui )
   {
      sampleIDLength = sampleIDLengthGui;
   }
   
   /**
      returns the info for a given sample at 2d array location (i,j)
   @param i
   @param j
   @return 
   */
   public char getSampleIDInfoAt(int i, int j)
   {
      return sampleIDInfo[i][j];
   }
   
   /**
      returns the whole 2d array of char that represents our sample info
   @return 
   */
   public char[][] getSampleIDInfo()
   {
      return sampleIDInfo;
   }
   
   /**
      sets the sampleIDInfo to new IDs
   @param newIDs 
   */
   public void setSampleNamesFromArray(char[][] newIDs)
   {
      sampleIDInfo = newIDs;
   }
   
   /**
      prints to file all of the sample IDs in FHX2 format
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
   
   /**
      Prints to file all of the current sample data 
      (the 2d array with year numbers) in FHX format
   */
   private void printCollectedData()
   {
      int counterYear = startYear;
      if (sHandle != null) 
      {
         for(int i = 0; i < sHandle.getSample(0).getSize(); i++)
         {
            for(int j = 0; j < numberOfSamples ; j++)
               prntW.print(sHandle.getSample(j).getInfo()[i]);
            prntW.println(" " + counterYear++);
            prntW.flush();
         }       
      } 
      else 
      {
         for (int i = 0; i < ((endYear - startYear)); i++) 
         {
             for (int j = 0; j < numberOfSamples; j++) 
               prntW.print(collectionData[i][j]);
             prntW.println(" " + counterYear++);
             prntW.flush();
         }
         for (int k = 0; k < numberOfSamples; k++) 
            prntW.print(collectionData[(endYear - startYear)][k]);
         prntW.print(" " + counterYear++);
      }
    }
   
   /**
      Prints to file all of the site info data in FHX format
   */
   private void printSiteData()
   {
      prntW.println("Name of site   : " + nameOfSite);
      prntW.println("Site code      : " + siteCode);
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
   
   /**
      Populates the siteInfo array with the corresponding values
      from a certain FHX file
   */
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
         {
            siteInfo[i] = "";
         }
      }
   }
   
   /**
      Reads from the FHX file to set the sample info and the site info. 
   @param FilePath
   @param textReader
   @param fileRead
   @throws Exception 
   */
   private void ReadFromFHX2(String FilePath, BufferedReader textReader, 
                             FileReader fileRead) throws Exception
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
      
      sHandle = new SampleHandler(numberOfSamples, startYear, endYear, 
                                  sampleIDLength, false, collectionData);
      
      br.close();
      fr.close();
   }
   
   /**
      Reads from file after the site info, the start year, the number of
      samples, and the sample ID length.
   @param textReader
   @throws Exception 
   */
   private void ReadSampleInformation(BufferedReader textReader) throws Exception
   {
      String multiValue = textReader.readLine();
      String []parts = multiValue.split(" ");
      startYear = Integer.parseInt(parts[0]);
      numberOfSamples = Integer.parseInt(parts[1]);
      sampleIDLength = Integer.parseInt(parts[2]);
   }
   
   /**
      Reads from FHX file all of the sample IDs into a 2d array
   @param textReader
   @throws Exception 
   */
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
   
   /**
      Reads from FHX file all of the sample info into a 2d array
   @param textReader
   @throws Exception 
   */
   private void ReadCollectionData(BufferedReader textReader) throws Exception
   {
      collectionData = new char[numberOfSamples][(endYear - startYear) + 1];
      for (int i = 0; i <= (endYear - startYear); i++)
      {
         String charValue = textReader.readLine();
         for (int j = 0; j < numberOfSamples; j++)
         {
            collectionData[j][i] = charValue.charAt(j);
         }
      }
   }
   
   /**
      In a FHX file this method will find and set the endYear
   @param textReader
   @throws Exception 
   */
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
   
   /**
      Reads the comment section of a FHX and sets the comments[] to what 
      it read in.
   @param textReader
   @throws Exception 
   */
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
   
   /**
      Prints to file the current comments[], if there are no comments
      to be printed it prints to file an empty comments block.
   */
   private void PrintComments()
   {
      int counter = 0;
      String testString;
      if (comments != null)
      {
         testString = comments[counter];
         while (!testString.equalsIgnoreCase("End Comments   :"))
         {
            prntW.println(comments[counter++]);
            testString = comments[counter];
         }
         prntW.println(comments[counter]);
      }
      else
      {
         prntW.println("Begin comments :");
         prntW.println("End comments   :");
      }
   }
   
   /**
      Sets the current comments[] to null
   */
   public void eraseComments()
   {
      comments = null;
   }
   
   /**
      Sets the inputFileName to a given String
   @param filePath 
   */
   private void setInputFileName(String filePath)
   {
       String [] parts = filePath.split("\\\\");
       inputFileName = parts[parts.length - 1];
   }
   
   /**
      returns the current inputFileName
   @return 
   */
   public String getInputFileName()
   {
       return inputFileName;
   }
   
   /**
      sets the input file name, then reads in the site information for the 
      given file, then reads in the comments block, then reads in sample IDs,
      then reads in sample data.
   @param filePath 
   */
   public void readFromFile(String filePath)
   {
      try
      {
         FileReader fr = new FileReader(filePath);
         BufferedReader br = new BufferedReader(fr);
         setInputFileName(filePath);
         ReadFileSiteInformation(br);
         ReadComments(br);
         FindEndYear(br);
         ReadFromFHX2(filePath, br, fr);
         
      }
      catch (Exception e)
      {
         System.out.println("Error: " + e);
      }
   }
   
   
   /**
      sets each site info field to its corresponding array placement in
      siteInfo[]
   */
   public class DataLocations
   {
      int nameOfSite = 0;
      int siteCode = 1;
      int collectionDate = 2;
      int collectors = 3;
      int crossdaters = 4;
      int numberofSamples = 5;
      int speciesName = 6;
      int commonName = 7;
      int habitatType = 8;
      int country = 9;
      int state = 10;
      int county = 11;
      int parkMonument = 12;
      int nationalForest = 13;
      int rangerDistrict = 14;
      int township = 15;
      int range = 16;
      int section = 17;
      int quarterSection = 18;
      int utmEasting = 19;
      int utmNorthing = 20;
      int latitude = 21;
      int longitude = 22;
      int topographicMap = 23;
      int lowestElev = 24;
      int highestElev = 25;
      int slope = 26;
      int aspect = 27;
      int areaSampled = 28;
      int substrateType = 29 ;
   }
   
   public static void main(String[] args)
   {
      DataHelper d = new DataHelper();
      d.readFromFile("J:\\SE\\SE3860\\Schnauzers-Trousers\\trunk\\SE3860_SecondGUI\\uslcf001.fhx");
      d.printFile(1150, 2001, 89, 5, "Newfile.fhx", siteInfo, sampleIDInfo, collectionData);
   }
}