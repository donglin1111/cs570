// Name: Donglin Yang          Date: 09/25/2019
package hw2;

public class Complexity {
	// a method that has time complexity O(n).
	public static  void method0 (int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			System . out . println (" Operation "+ counter );
			counter++;
		}
	}
	
	// a method that has time complexity O(n^2).
	public static  void method1 (int n) {
		int counter =0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System . out . println (" Operation "+ counter );
				counter++;
			}
		}
	}
	
	// a method that has time complexity O(n^3).
	public static void method2(int n) {
		int counter=0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				for (int k=0; k<n; k++) {
					System.out.println(" Operation "+ counter );
					counter++;
				}
			}
		}
	}
	
	// a method that has time complexity O(logn).
	public static  void method3 (int n) {
		int counter = 0;
		for (int i = 1; i < n; i*=2) {
			System . out . println (" Operation "+ counter );
			counter++;
		}
	}
	//a method that has time complexity O(nlogn).
	public static  void method4 (int n) {
		int counter = 0;
		for (int i = 1; i < n; i*=2) {
			System . out . println (" Operation "+ counter );
			counter++;
		}
	}
	// a method that has time complexity O(log log n).  2^16 → 2^8 → 2^4 → 2^2 → 2^1. 
	// On each iteration, the algorithm cuts the exponent of the power of two in half.
	public static void method5 (int n) {
		int counter =0;
		for (int i = 2; i <n; i=i*i) {
				System . out . println (" Operation "+ counter );
				counter++;
		}
	}
	
	static int counter =0;
	public static int method6 (int n) {
		System . out . println (" Operation "+ counter );
		counter++;
		if (n <=1) 
			{
				return n;
			}
		return method6(n - 1) + method6(n - 1);
	}
	
	public static void main(String[] args) {
		
		//method0(5);
		//method1(5);
		//method2(5);
		//method3(8);
		method6(10);
	}

}
