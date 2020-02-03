package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;



public class Game {
	
	//Initialization Game
	public void InitGame() throws IOException
	{
		int n = 25;
        String[] arr = new String[n];
        // Initialization
        arr[0]="0"; arr[1]="|"; arr[2]="1"; arr[3]="|"; arr[4]="2";
        for(int i=5;i<10;i++) {
        	arr[i]="-";
        }
        arr[10]="3"; arr[11]="|"; arr[12]="4"; arr[13]="|"; arr[14]="5";
        for(int i=15;i<20;i++) {
        	arr[i]="-";
        }
        arr[20]="6"; arr[21]="|"; arr[22]="7"; arr[23]="|"; arr[24]="8";
        WriteData(arr);
        System.out.print("Initialization Game!\n");  
        ReadData();
	}

	//Write Data to text
	public void WriteData(String[] arr) throws IOException{
		int n = 25;
		File file = new File("Gameinformation.txt");  //存放数组数据的文件
        FileWriter out = new FileWriter(file);  //文件写入流
        for(int i=0;i<n;i++){
        	out.write(arr[i]);
        }
        out.close();
	}
	//Read Data to text
	public void ReadData() {
		String fileName = "Gameinformation.txt";
        // This will reference one line at a time
        String line = null;
        String[] arr1 = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
               // System.out.println(line);
                arr1 = line.split("");
            }   
            for(int i=0;i<25;i++)
            {
            	if(i%5==0)
            	{
            		System.out.println();
            	}
            	System.out.print(arr1[i]);
            }
            System.out.println("\n");
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	
}
