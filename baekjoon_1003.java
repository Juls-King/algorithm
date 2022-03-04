package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon_1003 {
	static int[] count = new int[2];
	static int[] map;
 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] ary = new int[N];
		
		for(int i=0 ; i<N ; i++) {
			ary[i] = Integer.parseInt(br.readLine());
		}
		
		
		for(int i=0 ; i<N ; i++) {
			map = new int[ary[i]];
			Arrays.fill(map, 0);
			
			System.out.println(fibonacci(ary[i]));
			
			System.out.print(count[0] + " ");
			System.out.println(count[1]);
			
			count[0] = count[1] = 0;
		}
		
	}
	
	public static int fibonacci(int n) {
	    if (n == 0) {
	        count[0]++;
	        return 0;
	    } else if (n == 1) {
	    	count[1]++;
	        return 1;
	    } else {
	    	map[n] = fibonacci(n-1) + fibonacci(n-2);
	        return fibonacci(n-1) + fibonacci(n-2);
	    }
	}

}
