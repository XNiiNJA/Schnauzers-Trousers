//This class is the main Data Handler for the Program and handles all
//interaction with data files. It holds all sample data and is responsible
//for importing new data and printing out user entered data.
package se3860_secondgui;

import java.io.*;
import java.util.ArrayList;

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
    * This method Prints all sample Data out to a formated file
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
   
   //This Function sets the name of the file to be writen to
   public void setOutputFileName (String fileName)
   {
      this.outputFileName = fileName;
   }
   
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
   
   //returns the array of fire history data
   public char[][] getInfo()
   {
      return collectionData;
   }
   
   /*
   sets all of the site information from an array of strings
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
   
   //This Function creates an array of the information to be stored in the 
   //sample sets
   private void createSiteInfoArray()
   {
      int counter = 0;
      
      dataLocs.nameOfSite = counter;
      siteInfo[counter++] = nameOfSite;
      
      dataLocs.siteCode = counter;
      siteInfo[counter++] = siteCode;
      
      dataLocs.collectionDate = counter;
      siteInfo[counter++] = collectionDate;
      
      dataLocs.collectors = counter;
      siteInfo[counter++] = collectors;
      
      dataLocs.crossdaters = counter;
      siteInfo[counter++] = crossdaters;
      
      dataLocs.speciesName = counter;
      siteInfo[counter++] = speciesName;
      
      dataLocs.commonName = counter;
      siteInfo[counter++] = commonName;
      
      
      dataLocs.habitatType = counter;
      siteInfo[counter++] = habitatType;
      
      
      dataLocs.country = counter;
      siteInfo[counter++] = country;
      
      dataLocs.state = counter;
      siteInfo[counter++] = state;
      
      dataLocs.county = counter;
      siteInfo[counter++] = county;
      
      dataLocs.parkMonument = counter;
      siteInfo[counter++] = parkMonument;
      
      dataLocs.nationalForest = counter;
      siteInfo[counter++] = nationalForest;
      
      dataLocs.rangerDistrict = counter;
      siteInfo[counter++] = rangerDistrict;
      
      dataLocs.township = counter;
      siteInfo[counter++] = township;
      
      dataLocs.range = counter;
      siteInfo[counter++] = range;
      
      dataLocs.section = counter;
      siteInfo[counter++] = section;
      
      dataLocs.quarterSection = counter;
      siteInfo[counter++] = quarterSection;
      
      dataLocs.utmEasting = counter;
      siteInfo[counter++] = utmEasting;
      
      dataLocs.utmNorthing = counter;
      siteInfo[counter++] = utmNorthing;
      
      dataLocs.latitude = counter;
      siteInfo[counter++] = latitude;
      
      dataLocs.longitude = counter;
      siteInfo[counter++] = longitude;
      
      dataLocs.topographicMap = counter;
      siteInfo[counter++] = topographicMap;
      
      dataLocs.lowestElev = counter;
      siteInfo[counter++] = lowestElev;
      
      dataLocs.highestElev = counter;
      siteInfo[counter++] = highestElev;
      
      dataLocs.slope = counter;
      siteInfo[counter++] = slope;
      siteInfo[counter++] = aspect;
      
      dataLocs.aspect = counter;
      siteInfo[counter++] = areaSampled;
      
      dataLocs.substrateType = counter;
      siteInfo[counter++] = substrateType;
   }
   
   
   //returns the current number of samples
   public int getNumberOfSamples()
   {
       return numberOfSamples;
   }
   
   //returns the start year of a sample set
   public int getStartYear()
   {
       return startYear;
   }
   
   //Gets the last year recorded in the sample set
   public int getEndYear()
   {
       return endYear;
   }
   
   //returns the array of site information
   public String[] getSiteInfo()
   {
      return siteInfo;
   }
   
   //returns the max length of a sample ID
   public int getSampleIDLength()
   {
       return sampleIDLength;
   }
   
   /*
   sets the number of samples
   */
   public void setNumberOfSamples( int numOfSamples )
   {
      numberOfSamples = numOfSamples;
   }
   
   /*
   sets the start year
   */
   public void setStartYear( int startYearGui )
   {
      startYear = startYearGui;
   }
   
   /*
   sets the end year
   */
   public void setEndYear ( int endYearGui )
   {
      endYear = endYearGui;
   }
   
   /*
   sets the sample's length
   */
   public void setSampleIDLength ( int sampleIDLengthGui )
   {
      sampleIDLength = sampleIDLengthGui;
   }
   
   //Returns the specified sample ID
   public char getSampleIDInfoAt(int i, int j)
   {
      return sampleIDInfo[i][j];
   }
   
   //returns all the sample ids info
   public char[][] getSampleIDInfo()
   {
      return sampleIDInfo;
   }
   
   //This function recieves and array of the new sample ids and writes over the
   //old set
   public void setSampleNamesFromArray(char[][] newIDs)
   {
      sampleIDInfo = newIDs;
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
   
   
   //sets the output file name
   private void printCollectedData()
   {
       int counterYear = startYear;
        if (sHandle != null) {

            for(int i = 0; i < sHandle.getSample(0).getSize(); i++)
            {
                for(int j = 0; j < numberOfSamples ; j++)
                {
                    prntW.print(sHandle.getSample(j).getInfo()[i]);
                }
                prntW.println(" " + counterYear++);
                prntW.flush();
            }       
        } else {
            
            for (int i = 0; i < ((endYear - startYear)); i++) {
                for (int j = 0; j < numberOfSamples; j++) {
                    prntW.print(collectionData[i][j]);
                }
                prntW.println(" " + counterYear++);
                prntW.flush();
            }
            for (int k = 0; k < numberOfSamples; k++) {
                prntW.print(collectionData[(endYear - startYear)][k]);
            }
            prntW.print(" " + counterYear++);
        }
    }
   
   //this function prints the header info to a file
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
   
   //This function reads in the fire history data from a file
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
   
   //This function is responsible for reading in all data from a current file
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
      
      sHandle = new SampleHandler(numberOfSamples, startYear, endYear, sampleIDLength, false, collectionData);
      
      br.close();
      fr.close();
   }
   
   //reads in the primary information for a sample set
   private void ReadSampleInformation(BufferedReader textReader) throws Exception
   {
      String multiValue = textReader.readLine();
      String []parts = multiValue.split(" ");
      startYear = Integer.parseInt(parts[0]);
      numberOfSamples = Integer.parseInt(parts[1]);
      sampleIDLength = Integer.parseInt(parts[2]);
   }
   
   //reads in all sample Ids from file
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
   
   //Reads all fire history data into an array
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
   
   
   //finds the last year recorded in an imported file
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
   
   //reads in comments from an imported file
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
   
   //prints comments out to a specified data file
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
   
   //removes the comment block from a data file
   public void eraseComments()
   {
      comments = null;
   }
   
   private void setInputFileName(String filePath)
   {
       String [] parts = filePath.split("\\\\");
       inputFileName = parts[parts.length - 1];
   }
   
   //returns the name of the file to import
   public String getInputFileName()
   {
       return inputFileName;
   }
   
   //this function starts the process of importing a sample set
   public void readFromFile(String filePath)
   {
       
      try
      {
         FileReader fr = new FileReader(filePath);
         BufferedReader br = new BufferedReader(fr);
         setInputFileName(filePath);
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
   
   //this class contains all constants used in DataHelp and acts like a 
   //container
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