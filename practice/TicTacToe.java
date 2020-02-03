package practice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TicTacToe{
	public static void main(String [] args)throws IOException {
        // The name of the file to open.
        int n = 5;
        int c=0;
        String[][] arr = new String[n][n];
        // Initialization
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i%2 == 0)
                {
                	if(j%2==0) {
                    	arr[i][j] = c+"";
                    	c++;
                    	//System.out.print(arr[i][j]);
                	}
                	else {
                	arr[i][j] = "|";
                	//System.out.print(arr[i][j]);
                	}
                }
                else {
                	arr[i][j] = "-";
                	//System.out.print(arr[i][j]);
                }       	
            }
            //System.out.print("\n");     
        }
        WriteData(arr);
        ReadData();    
	}
	// Write Data
	public static void WriteData(String[][] arr) throws IOException {
		int n = 5;
        File file = new File("test.txt");  
        FileWriter out = new FileWriter(file);  
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                out.write(arr[i][j]);
            }
            out.write("\r\n");
        }
        out.close();
	}
	// Read Data
	public static void ReadData() {
		 //System.out.print("Initialization Game!\n\n");  
		String fileName = "test.txt";
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   

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
