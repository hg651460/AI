/* ******************************************************************************
Author’s name(s): Mokshita Madan & Harshal Giradkar
Course Title: Artificial Intelligence
Semester: FALL 2017
Assignment Number : HW 5
Submission Date: 12/11/2017
Purpose: This program converts decimal number to binary number
Input: Decimal Number
Output: Binary Number
****************************************************************************** */
public class Numbers {

	 public static int baseBToDecimal(char input) {
	        if (input >= '0' && input <= '9') {
	            return Integer.parseInt(input + "");
	        } else {
	            return (int) (input - 'a') + 10;
	        }
	    }
	 
	 public static char decimalToBaseB(int input) {
	        if (input >= 0 && input <= 9) {
	            String str = String.valueOf(input);
	            return str.charAt(0);
	        } else {
	            return (char) ('a' + (input - 10));
	        }
	    }
	 
	  public static int toDecimal(String input, int base) {
	        int length = input.length();
	        int decimal = 0;
	        for (int placeValue = 0, index = length - 1; index >= 0; placeValue++, index--) {
	            decimal = decimal + baseBToDecimal(input.charAt(index)) * (int) (Math.pow(base, placeValue));
	        }
	        return decimal;
	    }
	 
	 
	 
	 public static String toBaseB(int input, int base) {
	        String result = "";
	        String[] arr=new String[5];
	        int n=arr.length;
	        while (input > 0) {
	            int remainder = input % base;
	            input = input / base;
	            result = decimalToBaseB(remainder) + result;
	           // System.out.println(result);
	           // String s=String.valueOf((double)decimalToBaseB(remainder)) ;
	        }
	        return result;
	    }
	 
	 
	 
	 
	 public static String Covert(String inputNumber) {
		 String inputNumber1 = inputNumber;
		 int outputBase = 2;
		 int inputBase = 10;
		 int decimal = toDecimal(inputNumber1, inputBase);
	        String output = toBaseB(decimal, outputBase);
	        return(to6Digit(output));
	 }
	 
	 public static String to6Digit(String output) {
		 int len=output.length() - 1;
	        String []ch={"0.1","0.1","0.1","0.1","0.1","0.1"};
	      
	        
	        for (int uniplaceValue = 0, index = len; index >= 0; uniplaceValue++, index--) {
	            //decimal = decimal + baseBToDecimal(input.charAt(index)) * (int) (Math.pow(base, placeValue));
	        	if(String.valueOf(output.charAt(index)).equals("0"))
	        	ch[uniplaceValue]= "0.1";
	        	else
	        		ch[uniplaceValue]= "0.9";

	        	len--;
	        	
	        }
	        
	        
	        String []chreverse={"0.1","0.1","0.1","0.1","0.1","0.1"};
	        for(int i=ch.length-1,j=0;i>=0;i--,j++) {
	        	chreverse[j]=ch[i];
	        } 
	        
	        String S="";
	        for(String s:chreverse)
	        {
	        	S+=s+",";
	        }
	       S.replaceAll(",\n","\n");
	 return S;
	 }
	 
}
