// Name: Donglin Yang          Date: 09/15/2019
// Homework 1
// The project based on [JavaSE-1.8]
// Define a class BinaryNumber that represents binary numbers and a few simple operations on
// them, as indicated below. 
package Homework1;
import java.util.Arrays;

public class BinaryNumber {
	int length;
	String str = null;
	private int data[];
	private boolean overflow;
	private boolean isequal;
    // Constructor::creating a binary number of length length and consisting only of zeros
	public BinaryNumber(int length)
	{
		//System.out.print("a=");
		this.length = length;
		data = new int[this.length];
        for(int i=0; i<this.length; i++)
        {
        	data[i]=0;
        }
	}
    //A constructor BinaryNumber(String str) for creating a binary number given a string
	public BinaryNumber(String str)
	{
        this.str = str;
        this.length = str.length();
        data = new int[this.length];
        for(int i=0; i<this.length; i++)
        {
        	char ch = this.str.charAt(i);
        	data[i] = Character.getNumericValue(ch);
        }
	}
	
	public int getLength()
	{	
		return data.length;
		
	}
	//  Obtaining a digit of a binary number given an index
	public int getDigit(int index)
	{
		try {
			return data[index];
		}catch (Exception e) {
			System.out.print("The index is out of bounds: ");
			return index;
		}
	}
	// Transforming a binary number to its decimal notation
	public int toDecimal()
	{
		int decimal=0;
		for(int i=0;i<data.length;i++)
		{
			if(data[i]==1) {
				decimal+=(int)Math.pow(2, i);
			}
		}
		return decimal;
	}
	
	public void shiftR(int amount)
	{
	    this.data = reverseArray(this.data);
		int[] newdata = Arrays.copyOf(this.data, this.data.length+amount); 
		newdata = reverseArray(newdata);
		this.data = newdata; 
	}
    // Adding two binary numbers, one is the binary number
	public void add(BinaryNumber aBinaryNumber)
	{
		int[] new_array = new int[data.length];  
		if(checkLength(aBinaryNumber))
		{
			for(int i=0;i<data.length;i++)
			{
				if((data[i]==1)&&(aBinaryNumber.data[i]==1))
				{
					if(overflow==true) {
						new_array[i]=1;
					}
					else {
						new_array[i]=0;
					}
					overflow = true;
				}
				if(((data[i]==0)&&(aBinaryNumber.data[i]==1))||((data[i]==1)&&(aBinaryNumber.data[i]==0))) {
					if(overflow==true) {
						new_array[i]=0;
						overflow = true;
					}
					else {
						new_array[i]=1;
						clearOverflow();
					}
				}
				if((data[i]==0)&&(aBinaryNumber.data[i]==0)) {
					if(overflow==true) {
						new_array[i]=1;	
					}
					else {
						new_array[i]=0;
					}
					clearOverflow();
				}
			}
			data = new_array;
			if(overflow == true) {
				int[] newdata = Arrays.copyOf(new_array, new_array.length+1);
				newdata[newdata.length-1]=1;
				System.out.print("(Overflow)");
			   data = newdata;
			}
		}
		else {
			System.out.println("The binary numbers do not coincide.");
		}
	}
	// reverse Array
	private static int[] reverseArray(int[] data2) {  
        int[] new_array = new int[data2.length];  
        for (int i = 0; i < data2.length; i++) {  
            new_array[i] = data2[data2.length - i - 1];  
        }  
        return new_array;  
    } 
	
	public String toString()
	{
		if(overflow == true)
		{
			return "(Overflow)"; 
		}
		else{
			String str1="";
			for(int i=0;i<this.data.length;i++){
			str1 += this.data[i]+"";
		}
		return str1;
		}
	}
	//clearOver flow flag
	public void clearOverflow(){
		overflow=false;
	}
	// Check length
	public boolean checkLength(BinaryNumber aBinaryNumber){
		if(data.length==aBinaryNumber.length)
			isequal = true;
		else
			isequal =false;
		return isequal;
	}
	// Main
    public static void main(String[] args) { 
    	int index = 2;
    	String str = "10110";
    	String str1 = "11100";
    	String str2 = "1011";
    	BinaryNumber number2 = new BinaryNumber(str); 
    	BinaryNumber number3 = new BinaryNumber(str1); 
    	BinaryNumber number4 = new BinaryNumber(str2); 
    	System.out.println("The length of Binary number: " + number2.toString() + " is " + number2.getLength() + ".") ;
    	System.out.println( number2.toString() + "(binary) = " + number2.toDecimal() + "(decimal) (Leftmost digit is the least significant one)");
    	System.out.println("After Shift:");
    	number4.shiftR(3);
    	System.out.println("The length of Binary number: " + number4.toString() + " is " + number4.getLength() + ".") ;
    	System.out.println( number4.toString() + "(binary) = " + number4.toDecimal() + "(decimal)");
    	System.out.println("A digit of a binary number of index " + 6 + " is "+ number4.getDigit(6) + ".");
    	System.out.println( number2.toString() + "(binary) = " + number2.toDecimal() + "(decimal) (Leftmost digit is the least significant one)");
    	System.out.print(str + " + " + str1 +" = ");
    	number2.add(number3);
    	System.out.println( number2.toString());
    	//System.out.println();
    	System.out.println(number2.toDecimal());   	
    }
}

