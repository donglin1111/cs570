package hw6;
// Donglin Yang
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Anagrams {	
	final Integer[] primes = {2 , 3 , 5 , 7, 11 , 
							 13 , 17 , 19 , 23 , 
							 29 , 31 , 37 , 41 , 
							 43 , 47 , 53 , 59 , 
							 61 , 67 , 71 , 73 , 
							 79 , 83 , 89 , 97 , 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	/***
	 * the constructor
	 */
	public Anagrams () {
		letterTable = new Hashtable<Character,Integer>();
		this.buildLetterTable();
		anagramTable = new Hashtable<Long,ArrayList<String>>();
	}
	/***
	 * This method should be invoked by the constructor for the class Anagrams and should build
        the hash table letterTable
	 */
	private void buildLetterTable() {
		int key = 0;
		char value = 'a';
		while(value<='z') {
			letterTable.put(value, primes[key]);
			key++;
			value++;
		}
	}
	/***
	 * This method should compute the hash code of the string s passed as argument, and should
	   add this word to the hash table anagramTable.
	 * @param s
	 */
	private void addWord(String s) {
		Long code = myHashCode(s);
		ArrayList<String> list = null;
		if(anagramTable.containsKey(code) == true) {
			list = anagramTable.get(code);
		}
		else{
			list = new ArrayList<String>();
		}
		list.add(s);
		anagramTable.put(code, list);
	}
	/***
	 * This method, given a string s, should compute its hash code. 
	 * @param s
	 * @return hash code
	 */
	private Long myHashCode(String s) {
		Long value = (long) 1;
		if(s==null) {
			throw new NullPointerException();
		}
		else {
			for(int i = 0;i<s.length();++i) {
				int temp = letterTable.get(s.charAt(i));
				value *= temp;
			}
			return value;
		}
	}
	/***
	 * The main method is processFile which receives the name of a text file
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream (s );
		BufferedReader br = new BufferedReader ( new InputStreamReader ( fstream ));
		String strLine;
		while ((strLine = br.readLine ()) != null ) {
			this.addWord(strLine);
		}
		br.close ();
	}
	/***
	 * This method should return the entries in the anagramTable that have the largest number of anagrams. 
	 * @return ArrayList<Map.Entry<Long,ArrayList<String>>> max_value
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		long max = 0;
		int list_size;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Map.Entry<Long,ArrayList<String>>> max_value = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for(Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			list = entry.getValue();
			list_size = list.size();
			if(list_size == max) {
				max_value.add(entry);
			}
			else if(list_size > max)	
			{
				max = list_size;
				max_value.clear();
				max_value.add(entry);
			}
		}
		return max_value;
}
	/***
	 * The main method will read all the strings in a file
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double  seconds = ((double)estimatedTime/1000000000);
		System.out.println("Elapsed Time: " + seconds);
		for(int i = 0; i<maxEntries.size();i++) {
			System.out.println("Key of max anagrams: " + maxEntries.get(i).getKey());
			System.out.println("List of  max anagrams: " + maxEntries.get(i).getValue());
			System.out.println("List of  max anagrams: " + maxEntries.get(i).getValue().size());
		}
	}
}
