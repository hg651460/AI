
/* ******************************************************************************
Author’s name(s): Mokshita Madan & Harshal Giradkar
Course Title: Artificial Intelligence
Semester: FALL 2017
Assignment Number : HW 5
Submission Date: 12/11/2017
Purpose: This program reads 8 bit grey scale bitmap file
Input: Name of the image file passed in command line argument
Output: input converted to integer array
****************************************************************************** */

/*
   ***************************************************************
   BitMapLoader.java reads in an 8-bit grey-scale bitmap file

   Input:  Name of the image file passed in command line argument

   Output: The integer array ndata8[0..nwidth*nheight] contrains
           the integer representations of the image pixels

   Note:   Feature extraction for neural learning must be
           performed on image data contained in ndata8[]

   Sample Usage:
          Case 1:
             java BitMapLoader white.bmp

             Image width and height: 16, 16
             Image padding:0
             Image length:256

             255 255 255 255 255 255 255 255 255 255 255 255 255 255 255 255
             ...
             255 255 255 255 255 255 255 255 255 255 255 255 255 255 255 255



          Case 3:
             java BitMapLoader black.bmp

             Image width and height: 16, 16
             Image padding:0
             Image length:256

             0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
             ...
             0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0



   Source: http://forums-beta.sun.com/thread.jspa?messageID=1383663
   ****************************************************************
*/

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class BitMapLoader {
	static ArrayList<vector> rawData = new ArrayList<vector>();
    static int numFolder=40;
    static int numFile=10;

   //*****************************************************
   public static void main(String[] args){

     try {
    	 String dir=args[0];
    	 double f1,f2,f3,f4,f5;
    	      
    	 //=========================================================
    	 // Reading data in Folder
    	//java BitMapLoader C:\Users\Mokshita\Desktop\Fall2017\A.I\Assignment\Assignment5\facesDB\
    	//C:\NNtest\src> java BitMapLoader C:\NNtest\facesDB\
    	 System.out.println(args[0]);
    	 //total of 360 input cases
    	 for(int i=1;i<=numFolder;i++) {
    		 dir+="S"+i;
    		 	for(int j=1;j<numFile;j++)//Use the first nine images from each subject for training
    		 	{
    		 		 dir+="\\"+j+".bmp";
    		 		//System.out.println(dir);
    		 		loadbitmap (new FileInputStream(dir));//loading data in rawData<arraylist> by reading files 1-9 for each S
    		 		dir=args[0];
    		 		dir+="S"+i;
    		 	}
    		 dir=args[0];
    	 }
    	          
 
      //initializing min and max=========================================================
         int minF1 = rawData.get(0).getF1(); int maxF1 = minF1;       
         int minF2 = rawData.get(0).getF2(); int maxF2 = minF2;
         int minF3 = rawData.get(0).getF3(); int maxF3 = minF3;
         int minF4 = rawData.get(0).getF4(); int maxF4 = minF4;
         int minF5 = rawData.get(0).getF5(); int maxF5 = minF5;

     //cal min and max    
     for(int i=0;i<rawData.size();i++)
     {
    	 minF1 = minF1 < rawData.get(i).getF1() ? minF1 : rawData.get(i).getF1();
    	 maxF1 = maxF1 > rawData.get(i).getF1() ? maxF1 : rawData.get(i).getF1();
    	 
    	 minF2 = minF2 < rawData.get(i).getF2() ? minF2 : rawData.get(i).getF2();
    	 maxF2 = maxF2 > rawData.get(i).getF2() ? maxF2 : rawData.get(i).getF2();
    	 
    	 minF3 = minF3 < rawData.get(i).getF3() ? minF3 : rawData.get(i).getF3();
    	 maxF3 = maxF3 > rawData.get(i).getF3() ? maxF3 : rawData.get(i).getF3();
    	 
    	 minF4 = minF4 < rawData.get(i).getF4() ? minF4 : rawData.get(i).getF4();
    	 maxF4 = maxF4 > rawData.get(i).getF4() ? maxF4 : rawData.get(i).getF4();
    	 
    	 minF5 = minF5 < rawData.get(i).getF5() ? minF5 : rawData.get(i).getF5();
    	 maxF5 = maxF5 > rawData.get(i).getF5() ? maxF5 : rawData.get(i).getF5();
    	 
     }


    //cal normalized vector using raw data======================================================= 
     ArrayList<normalizedVector> array_normalized=new ArrayList<normalizedVector>();

    for(int i=0;i<rawData.size();i++)
    {
    	f1=(((double)rawData.get(i).getF1()-minF1)/(maxF1-minF1));
    	f2=(((double)rawData.get(i).getF2()-minF2)/(maxF2-minF2));
    	f3=(((double)rawData.get(i).getF3()-minF3)/(maxF3-minF3));
    	f4=(((double)rawData.get(i).getF4()-minF4)/(maxF4-minF4));
    	f5=(((double)rawData.get(i).getF5()-minF5)/(maxF5-minF5));
    	
    	normalizedVector n=new normalizedVector(f1,f2,f3,f4,f5);
    	array_normalized.add(n);   	
    
    }
 
    
    //cal CompleteTrainingData in testing.dat======================================================= 
    //Covert- covert Decimal to 6 digit binary
    //create subject labels(use 0.1 and 0.9 instead of 0.0 and 1.0)
    String CompleteTrainingData="";
     
    int z=0; 
    FileWriter fileWriter = null;
    fileWriter = new FileWriter("input.dat");
    FileWriter fileWriter1 = null;
    fileWriter1 = new FileWriter("train.dat");
    
    for(int i=1;i<=numFolder;i++) {
    	for(int j=1;j<numFile;j++) {
    		
    		CompleteTrainingData=array_normalized.get(z).toString()+"\r\n";
    		System.out.println("i= "+i+"  "+CompleteTrainingData);
	    		fileWriter.append(CompleteTrainingData);

	    		String S=(new Numbers().Covert(String.valueOf(i))+"\r\n").replaceAll(",\\r\\n","\r\n");

	    		fileWriter1.append(S);
	    		z++;
    	}
    	
    	
    }
    fileWriter.flush();
	fileWriter.close();
	
    fileWriter1.flush();
	fileWriter1.close();
    
	System.out.println("Training.dat file is created");
     }
     
     
     catch (Exception e) {
         e.printStackTrace();
     }

   } //main



   //*****************************************************
   public static void loadbitmap (InputStream fs) {

     try {

         //*** 14 byte BITMAPFILEHEADER
         int bflen=14;
         byte bf[]=new byte[bflen];
         fs.read(bf,0,bflen);

         //*** 40 byte BITMAPFILEHEADER
         int bilen=40;
         byte bi[]=new byte[bilen];
         fs.read(bi,0,bilen);

         //*** Interperet data
         int nsize = (((int)bf[5]&0xff) << 24) | (((int)bf[4]&0xff) << 16) | (((int)bf[3]&0xff) << 8) | (int)bf[2]&0xff;
         int nbisize = (((int)bi[3]&0xff) << 24) | (((int)bi[2]&0xff) << 16) | (((int)bi[1]&0xff) << 8) | (int)bi[0]&0xff;
         int nwidth = (((int)bi[7]&0xff) << 24) | (((int)bi[6]&0xff) << 16) | (((int)bi[5]&0xff) << 8) | (int)bi[4]&0xff;
         int nheight = (((int)bi[11]&0xff) << 24) | (((int)bi[10]&0xff) << 16) | (((int)bi[9]&0xff) << 8) | (int)bi[8]&0xff;
         int nplanes = (((int)bi[13]&0xff) << 8) | (int)bi[12]&0xff;
         int nbitcount = (((int)bi[15]&0xff) << 8) | (int)bi[14]&0xff;


         //*** Look for non-zero values to indicate compression
         int ncompression = (((int)bi[19]) << 24) | (((int)bi[18]) << 16) | (((int)bi[17]) << 8) | (int)bi[16];
         int nsizeimage = (((int)bi[23]&0xff) << 24) | (((int)bi[22]&0xff) << 16) | (((int)bi[21]&0xff) << 8) | (int)bi[20]&0xff;
         int nxpm = (((int)bi[27]&0xff) << 24) | (((int)bi[26]&0xff) << 16) | (((int)bi[25]&0xff) << 8) | (int)bi[24]&0xff;
         int nypm = (((int)bi[31]&0xff) << 24) | (((int)bi[30]&0xff) << 16) | (((int)bi[29]&0xff) << 8) | (int)bi[28]&0xff;
         int nclrused = (((int)bi[35]&0xff) << 24) | (((int)bi[34]&0xff) << 16) | (((int)bi[33]&0xff) << 8) | (int)bi[32]&0xff;
         int nclrimp = (((int)bi[39]&0xff) << 24) | (((int)bi[38]&0xff) << 16) | (((int)bi[37]&0xff) << 8) | (int)bi[36]&0xff;

        //*** non 8-bit images
        if (nbitcount!=8) {
           System.out.println("Error: Can only handle 8-bit grey-scale bitmaps");
           System.exit(0);
        }


        //*** from here on nbitcount = 8
        int nNumColors = 0;
        if (nclrused > 0)
               nNumColors = nclrused;
        else
               nNumColors = (1&0xff) << nbitcount;

        if (nsizeimage == 0) {
              nsizeimage = ((((nwidth*nbitcount)+31) & ~31 ) >> 3);
              nsizeimage *= nheight;
        }

        //*** Read the palatte colors.
        int npalette[] = new int [nNumColors];
        byte bpalette[] = new byte [nNumColors*4];
        fs.read (bpalette, 0, nNumColors*4);

        int nindex8 = 0;
        for (int n = 0; n < nNumColors; n++) {
           npalette[n] = (255&0xff) << 24 | (((int)bpalette[nindex8+2]&0xff) << 16) | (((int)bpalette[nindex8+1]&0xff) << 8) | (int)bpalette[nindex8]&0xff;
           nindex8 += 4;
        }


       //*** Read the image data (actually indices into the palette)
       //*** Scan lines are still padded out to even 4-byte boundaries.
       int npad8 = (nsizeimage / nheight) - nwidth;

       //*** integer data
       int ndata8[] = new int [nwidth*nheight];

       //*** read in the binary image date
       byte bdata[] = new byte [(nwidth+npad8)*nheight];
       fs.read (bdata, 0, (nwidth+npad8)*nheight);

       nindex8 = 0;
       for (int j8 = 0; j8 < nheight; j8++) {
          for (int i8 = 0; i8 < nwidth; i8++) {
               ndata8 [nwidth*(nheight-j8-1)+i8] = npalette [((int)bdata[nindex8]&0xff)];
               nindex8++;
          }
       nindex8 += npad8;
      }


     //***********************************************************************************
     //*** Print image information
     //*** ndata8[0..nwidth*nheight] contains the image in 8bit grey scale
     //***********************************************************************************

     int[] list = new int[nwidth*nheight];
     int listPos = 0;
    
     
     //*** print the entire array of nwidth*nheight pixels
     int k = 0;
     for (int i=0; i<nheight; i++){
       for (int j=0; j<nwidth; j++){
             ndata8[k] = ndata8[k] & 0xff;

             list[listPos++] = ndata8[k];
             
             //*** HERE HERE HERE HERE HERE HERE HERE HERE HERE
             //*** perform feature extraction on ndata8[]
         
            // System.out.print("		i="+i+",		j="+j+ "		ndata["+k+"]="+ndata8[k] + " "+"\n");
             
             k++;
       }

     }
     


     //sum
     double sum=sum(list);
     
     int F1=(int)median(list);    

     //Median
     Arrays.sort(list);
     int F2=(int)sum/list.length;

     //variance
     int F3=(int)variance(list,sum);    


     //skewness
     int F4=(int)skewness(list,sum);    

    //peakedness
     int F5=(int)peakedness(list,sum);    

     vector v=new vector(F1,F2,F3,F4,F5);
    	     
    int rawDataPos = 0;
    rawData.add(v);

     //*** close the image file
     fs.close();
     }

     catch (Exception e) {
        System.out.println("Caught exception in loadbitmap!");
        System.err.println(e.getMessage());
        e.printStackTrace();
     }

   } //loadbitmap


   
   public static double sum(int[] list) {
	    double sum = 0;
	    for (int i = 0; i < list.length; i++) {
	        sum += list[i];
	    }
	    return sum;
   }
	    
// the array double[] m MUST BE SORTED
   public static double median(int[] list) {
	    int middle = list.length / 2;
	    if (list.length % 2 == 1) {
	      return list[middle];
	    } else {
	      return (list[middle - 1] + list[middle]) / 2.0;
	    }
	  }

public static double variance(int[] list,double sum){
    double average = sum;
    average /= list.length;

    double variance = 0.0;
    for(double p: list){
        variance += (p - average) * (p - average);
    }
    return variance / list.length;
}


public static double skewness(int[] list,double sum){
    double average = sum;
    average /= list.length;

    double up=0.0;
    double down=0.0;
    double skewness = 0.0;
    
    for(double p: list){
    	up += Math.pow(p - average, 3);
    	down+=Math.pow(p - average, 2);
    }
    skewness=((up/list.length)/(Math.pow((down/list.length),3/2)));
    return skewness;
}


public static double peakedness(int[] list,double sum){
    double average = sum;
    average /= list.length;

    double up=0.0;
    double down=0.0;
    double peakedness = 0.0;
    
    for(double p: list){
    	up += Math.pow(p - average, 4);
    	down+=Math.pow(p - average, 2);
    }
    peakedness=((up/list.length)/(Math.pow((down/list.length),2)))-3;
    return peakedness;
}


} //BitMapLoader